package com.vc.service.system;

public interface IServiceHelper {
	
	public static String BEAN_NAME = "serviceHelper";
	
	public abstract IFSProvider loadFSProvider();

}
