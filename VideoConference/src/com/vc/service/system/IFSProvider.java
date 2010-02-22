package com.vc.service.system;

import java.io.File;
import java.io.InputStream;

public interface IFSProvider {

	public abstract boolean checkFileExistence(String file);

	public abstract boolean createFile(String persistenceName, File file);

	public abstract InputStream readFile(File file);
	
	public abstract long getFileLength(File file);

}