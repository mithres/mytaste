package com.comodo.p2pav.fes.handler;

import java.io.IOException;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

public interface IHandler {

	public abstract void setCommand(String command);
	
	public abstract void process(Selector selector, SocketChannel channel) throws IOException;

}
