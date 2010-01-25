package com.vc.util.photo;

import ij.ImagePlus;
import ij.io.FileSaver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.vc.core.constants.Constants;
import com.vc.core.spring.ApplicationContextUtil;

public class MultiProtocolFileSaver {

	private static Log log = LogFactory.getLog(MultiProtocolFileSaver.class);

	private static final String FILE_UPLOAD_BEAN_SUFFIX = "Uploader";

	private static final String FILE_PROTOCOL = "file";

	private static final Method METHOD_SAVE_JPEG;

	private static final Method METHOD_SAVE_PNG;

	private static final Method METHOD_SAVE_GIF;

	private final FileSaver fileSaver;

	static {
		try {
			METHOD_SAVE_JPEG = FileSaver.class.getMethod("saveAsJpeg", String.class);
			METHOD_SAVE_PNG = FileSaver.class.getMethod("saveAsPng", String.class);
			METHOD_SAVE_GIF = FileSaver.class.getMethod("saveAsGif", String.class);
		} catch (SecurityException e) {
			throw new IllegalStateException("Incompatible IJ library", e);
		} catch (NoSuchMethodException e) {
			throw new IllegalStateException("Incompatible IJ library", e);
		}
	}

	/**
	 * If compilation errors occurs in this method. Please verify method names
	 * and parameter type in static initializer.
	 */
	private static void dummyMethod() {
		if (false) {
			new FileSaver(null).saveAsGif("");
			new FileSaver(null).saveAsJpeg("");
			new FileSaver(null).saveAsPng("");
		}
	}

	public MultiProtocolFileSaver(ImagePlus imp) {
		fileSaver = new FileSaver(imp);
	}

	/**
	 * @param uri
	 * @return
	 * @see ij.io.FileSaver#saveAsPgm(java.lang.String)
	 */
	public boolean saveAsPng(String uri) {
		return saveAs(uri, METHOD_SAVE_PNG);
	}

	/**
	 * @param uri
	 * @return
	 * @see ij.io.FileSaver#saveAsJpeg(java.lang.String)
	 */
	public boolean saveAsJpeg(String uri) {
		return saveAs(uri, METHOD_SAVE_JPEG);
	}

	protected boolean saveAs(String uri, Method saveMethod) {
		File tmpFile = null;
		FileInputStream is = null;
		try {
			tmpFile = File.createTempFile("img", null);
			tmpFile.deleteOnExit();

			IFileUploader uploader = lookupUploader(uri);

			if (!uploader.preparePath(uri, Constants.PHOTO_PATH_AUTH)) {
				log.warn("Folder preparation failed for:" + uri + ". Saving terminated.");
				return false;
			}

			String path = tmpFile.getPath();

			if (!convertTo(path, saveMethod)) {
				log.warn("Image convertation impossible for :" + uri + ". Saving terminated.");
				return false;
			}

			is = new FileInputStream(tmpFile);
			uploader.upload(is, (int) tmpFile.length(), uri, Constants.PHOTO_PATH_AUTH);

			IOUtils.closeQuietly(is);
			is = null;
			return true;

		} catch (IOException e) {
			log.warn("I/O error in upload " + uri, e);
			return false;
		} finally {
			if (is != null) {
				IOUtils.closeQuietly(is);
			}

			if (tmpFile != null) {
				tmpFile.delete();
			}
		}
	}

	/**
	 * @param path
	 * @param saveMethod
	 * @return
	 */
	private boolean convertTo(String path, Method saveMethod) {
		try {

			return (Boolean) saveMethod.invoke(fileSaver, path);

		} catch (Exception e) {
			log.warn("Image transformation failed", e);
			return false;
		}

	}

	/**
	 * Looking for {@link IFileUploader} which supports <code>uri</code>
	 * protocol(default,file://, http://, https://, scp://)
	 * 
	 * @param uri
	 * @return
	 */
	public static IFileUploader lookupUploader(String uri) {
		URI u;
		String protocol;
		try {
			u = new URI(uri);
			protocol = u.getScheme();

			if (protocol == null || protocol.length() == 0) {
				protocol = FILE_PROTOCOL;
			}
		} catch (URISyntaxException e) {
			protocol = FILE_PROTOCOL;
		}

		String beanName = protocol.toLowerCase() + FILE_UPLOAD_BEAN_SUFFIX;

		IFileUploader uploader = (IFileUploader) ApplicationContextUtil.getApplicationContext().getBean(beanName);

		if (uploader == null) {
			throw new IllegalStateException("Not found bean with name " + beanName + " for upload via protocol " + protocol);
		}
		return uploader;
	}

	/**
	 * @param uri
	 * @return
	 * @see ij.io.FileSaver#saveAsGif(java.lang.String)
	 */
	public boolean saveAsGif(String uri) {
		return saveAs(uri, METHOD_SAVE_GIF);
	}
}
