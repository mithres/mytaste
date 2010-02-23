package com.vc.service.system;

import java.io.File;

public interface IFSProvider {

	public abstract boolean createFile(String persistenceName, File file);
	
	public abstract File getFile(String fileName);
	
}
