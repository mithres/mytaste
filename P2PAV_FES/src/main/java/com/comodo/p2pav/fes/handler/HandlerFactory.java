package com.comodo.p2pav.fes.handler;

public class HandlerFactory {
	
	private static HandlerFactory factory = null;
	
	private HandlerFactory(){
		
	}
	
	public static HandlerFactory getInstance(){
		
		if(factory == null){
			factory = new HandlerFactory();
		}
		return factory;
		
	}
	
	public IHandler getHandler(String command){
		
		if (command.startsWith("Download")) {
			return new DownloadHandler(command);
		} else if (command.startsWith("Upload")) {
			return new UploadHandler(command);
		}
		return null;
	}
	
}
