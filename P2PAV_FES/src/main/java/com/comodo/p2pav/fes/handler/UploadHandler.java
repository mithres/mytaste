package com.comodo.p2pav.fes.handler;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UploadHandler implements IHandler {

	private final static Logger logger = LoggerFactory.getLogger(UploadHandler.class);

	private static final int BLOCK = 1024;

	private FileChannel fc = null;

	private ByteBuffer buffer = ByteBuffer.allocate(BLOCK * 4);

	// Test
	private int total = 0;

	private String command;

	public UploadHandler(String command) {
		this.command = command;
	}

	@Override
	public void process(Selector selector, SocketChannel channel) throws IOException {

		SelectionKey wKey = null;
		if (command.startsWith("Upload")) {
			command = "ReceiveFile";
			channel.write(ByteBuffer.wrap("OK".getBytes("UTF-8")));
			wKey = channel.register(selector, SelectionKey.OP_READ);
			wKey.attach(this);
		} else if (command.startsWith("ReceiveFile")) {

			if (fc == null) {
				File file = new File("/home/ammen/Test/test.zip");
				if (!file.exists()) {
					file.createNewFile();
				}
				fc = new FileOutputStream(file).getChannel(); 
			}

			int count = channel.read(buffer);
			if (count != -1) {
				buffer.flip();
				fc.write(buffer);
				total += count;
				buffer.clear();
			} else {
				logger.info("Receive file size:" + total);
				fc.close();
				channel.close();
			}
		}

	}

	@Override
	public void setCommand(String command) {
		this.command = command;
	}

}
