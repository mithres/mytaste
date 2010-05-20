package com.comodo.p2pav.fes.handler;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

public class DownloadHandler implements IHandler{
	
	
	//Test
	protected String filename = "/home/ammen/Test/localsrv.zip"; // a big file
	
	
	private static final int BLOCK = 4096;
	
	protected FileChannel fc;
	private ByteBuffer buffer = ByteBuffer.allocate(BLOCK);

	public String command;

	public DownloadHandler(String command) {
		this.command = command;
	}

	public void process(Selector selector, SocketChannel channel) throws IOException {

		SelectionKey wKey = null;
		if (command.startsWith("Download")) {
			String[] str = command.split(",");
			filename = str[1];
			channel.write(ByteBuffer.wrap("OK".getBytes("UTF-8")));
			wKey = channel.register(selector, SelectionKey.OP_READ);
			wKey.attach(this);
		} else if (command.startsWith("Start download")) {
			command = "SendFile";
			wKey = channel.register(selector, SelectionKey.OP_WRITE);
			wKey.attach(this);
		} else if (command.startsWith("SendFile")) {

			if (fc == null) {
				fc = new FileInputStream(filename).getChannel();
			}

			ByteBuffer block = readBlock();
			if (block != null) {
				channel.write(block);
			} else {
				fc.close();
				channel.close();
			}
		}

	}

	private ByteBuffer readBlock() {
		try {
			buffer.clear();
			int count = fc.read(buffer);
			buffer.flip();
			if (count <= 0) {
				return null;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return buffer;
	}

	public void setCommand(String command) {
		this.command = command;
	}
}
