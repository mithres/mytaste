package com.vc.service.system;

import org.springframework.stereotype.Service;

import com.vc.core.spring.ApplicationContextUtil;
import com.vc.util.configuration.ServerConfiguration;

@Service
public class ServiceHelper implements IServiceHelper {

	@Override
	public IFSProvider loadFSProvider() {
		String fsType = ServerConfiguration.getFsType();
		return (IFSProvider) ApplicationContextUtil.getApplicationContext().getBean(fsType);
	}

}
