package com.vc.red5;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.red5.io.IStreamableFileFactory;
import org.red5.io.IStreamableFileService;
import org.red5.logging.Red5LoggerFactory;
import org.red5.server.api.IBasicScope;
import org.red5.server.api.IScope;
import org.red5.server.api.ScopeUtils;
import org.red5.server.api.stream.IBroadcastStream;
import org.red5.server.api.stream.IStreamFilenameGenerator;
import org.red5.server.api.stream.IStreamFilenameGenerator.GenerationType;
import org.red5.server.messaging.IMessageInput;
import org.red5.server.messaging.IPipe;
import org.red5.server.messaging.InMemoryPullPullPipe;
import org.red5.server.stream.BroadcastScope;
import org.red5.server.stream.DefaultStreamFilenameGenerator;
import org.red5.server.stream.IBroadcastScope;
import org.red5.server.stream.IProviderService;
import org.red5.server.stream.provider.FileProvider;
import org.slf4j.Logger;

import com.vc.core.spring.ApplicationContextUtil;
import com.vc.service.system.IFSProvider;
import com.vc.service.system.IServiceHelper;

public class ProviderService implements IProviderService {

	private static final Logger log = Red5LoggerFactory.getLogger(ProviderService.class);

	/** {@inheritDoc} */
	public INPUT_TYPE lookupProviderInput(IScope scope, String name) {
		INPUT_TYPE result = INPUT_TYPE.NOT_FOUND;
		if (scope.getBasicScope(IBroadcastScope.TYPE, name) != null) {
			// we have live input
			result = INPUT_TYPE.LIVE;
		} else {
			try {
				File file = getStreamFile(scope, name);
				if (file != null) {
					// we have vod input
					result = INPUT_TYPE.VOD;
					// null it to prevent leak or file locking
					file = null;
				}
			} catch (IOException e) {
				log.warn("Exception attempting to lookup file: {}", name, e);
				e.printStackTrace();
			}
		}
		return result;
	}

	/** {@inheritDoc} */
	public IMessageInput getProviderInput(IScope scope, String name) {
		IMessageInput msgIn = getLiveProviderInput(scope, name, false);
		if (msgIn == null) {
			return getVODProviderInput(scope, name);
		}
		return msgIn;
	}

	/** {@inheritDoc} */
	public IMessageInput getLiveProviderInput(IScope scope, String name, boolean needCreate) {
		IBasicScope basicScope;
		basicScope = scope.getBasicScope(IBroadcastScope.TYPE, name);
		if (basicScope == null) {
			if (needCreate) {
				synchronized (scope) {
					// Re-check if another thread already created the scope
					basicScope = scope.getBasicScope(IBroadcastScope.TYPE, name);
					if (basicScope == null) {
						basicScope = new BroadcastScope(scope, name);
						scope.addChildScope(basicScope);
					}
				}
			} else {
				return null;
			}
		}
		if (!(basicScope instanceof IBroadcastScope)) {
			return null;
		}
		return (IBroadcastScope) basicScope;
	}

	/** {@inheritDoc} */
	public IMessageInput getVODProviderInput(IScope scope, String name) {
		log.debug("getVODProviderInput - scope: {} name: {}", scope, name);
		File file = getVODProviderFile(scope, name);
		if (file == null) {
			return null;
		}
		IPipe pipe = new InMemoryPullPullPipe();
		pipe.subscribe(new FileProvider(scope, file), null);
		return pipe;
	}

	/** {@inheritDoc} */
	public File getVODProviderFile(IScope scope, String name) {
		log.debug("getVODProviderFile - scope: {} name: {}", scope, name);
		File file = null;
		try {
			log.debug("getVODProviderFile scope path: {} name: {}", scope.getContextPath(), name);
			file = getStreamFile(scope, name);
		} catch (IOException e) {
			log.error("Problem getting file: {}", name, e);
		}

		if (file == null || !file.exists()) {
			// if there is no file extension this is most likely a live stream
			if (name.indexOf('.') > 0) {
				log.info("File was null or did not exist: {}", name);
			} else {
				log.debug("VOD file {} was not found, may be live stream", name);
			}
			return null;
		}
		return file;
	}

	/** {@inheritDoc} */
	public boolean registerBroadcastStream(IScope scope, String name, IBroadcastStream bs) {
		boolean status = false;
		synchronized (scope) {
			IBasicScope basicScope = scope.getBasicScope(IBroadcastScope.TYPE, name);
			if (basicScope == null) {
				basicScope = new BroadcastScope(scope, name);
				scope.addChildScope(basicScope);
			}
			if (basicScope instanceof IBroadcastScope) {
				((IBroadcastScope) basicScope).subscribe(bs.getProvider(), null);
				status = true;
			}
		}
		return status;
	}

	/** {@inheritDoc} */
	public List<String> getBroadcastStreamNames(IScope scope) {
		// TODO: return result of "getBasicScopeNames" when the api has
		// changed to not return iterators
		List<String> result = new ArrayList<String>();
		Iterator<String> it = scope.getBasicScopeNames(IBroadcastScope.TYPE);
		while (it.hasNext()) {
			result.add(it.next());
		}
		it = null;
		return result;
	}

	/** {@inheritDoc} */
	public boolean unregisterBroadcastStream(IScope scope, String name) {
		boolean status = false;
		synchronized (scope) {
			IBasicScope basicScope = scope.getBasicScope(IBroadcastScope.TYPE, name);
			if (basicScope instanceof IBroadcastScope) {
				scope.removeChildScope(basicScope);
				status = true;
			}
		}
		return status;
	}

	private File getStreamFile(IScope scope, String name) throws IOException {
		IStreamableFileFactory factory = (IStreamableFileFactory) ScopeUtils.getScopeService(scope, IStreamableFileFactory.class);
		if (name.indexOf(':') == -1 && name.indexOf('.') == -1) {
			// Default to .flv files if no prefix and no extension is given.
			name = "flv:" + name;
		}
		log.debug("getStreamFile null check - factory: {} name: {}", factory, name);
		for (IStreamableFileService service : factory.getServices()) {
			if (name.startsWith(service.getPrefix() + ':')) {
				name = service.prepareFilename(name);
				break;
			}
		}

		IStreamFilenameGenerator filenameGenerator = (IStreamFilenameGenerator) ScopeUtils.getScopeService(scope,
				IStreamFilenameGenerator.class, DefaultStreamFilenameGenerator.class);

		String filename = filenameGenerator.generateFilename(scope, name, GenerationType.PLAYBACK);
		File file;
		if (filenameGenerator.resolvesToAbsolutePath()) {
			IServiceHelper serviceHelper = (IServiceHelper) ApplicationContextUtil.getApplicationContext().getBean("serviceHelper");
			IFSProvider fsProvider = serviceHelper.loadFSProvider();
			file = fsProvider.getFile(filename);
		} else {
			file = scope.getContext().getResource(filename).getFile();
		}
		// check files existence
		if (file != null && !file.exists()) {
			// if it does not exist then null it out
			file = null;
		}
		return file;

	}

}
