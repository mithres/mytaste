package com.comodo.p2pav.fes.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.comodo.p2pav.fes.handler.DownloadHandler;
import com.comodo.p2pav.fes.handler.HandlerFactory;
import com.comodo.p2pav.fes.handler.IHandler;
import com.comodo.p2pav.fes.handler.UploadHandler;
import com.comodo.p2pav.fes.util.ServerConfigration;

public class FESServer implements Runnable {

	private final static Logger logger = LoggerFactory.getLogger(FESServer.class);

	private static final int BLOCK = 4096;

	private static final Charset charSet = Charset.forName("UTF-8");

	private HandlerFactory handlerFactory;

	private Selector selector;
	private ByteBuffer clientBuffer = ByteBuffer.allocate(BLOCK);

	public FESServer() throws IOException {

		handlerFactory = HandlerFactory.getInstance();

		ServerSocketChannel server = ServerSocketChannel.open();
		selector = Selector.open();
		server.socket().bind(new InetSocketAddress(ServerConfigration.getServerPort()));
		server.configureBlocking(false);
		server.register(selector, SelectionKey.OP_ACCEPT);

	}

	@Override
	public void run() {
		try {
			logger.info("Listernint on " + ServerConfigration.getServerPort());
			while (true) {
				if (selector.select() > 0) {
					Iterator<SelectionKey> iter = selector.selectedKeys().iterator();
					while (iter.hasNext()) {
						SelectionKey key = iter.next();
						iter.remove();
						handleKey(key);
					}
				}
			}

		} catch (IOException e) {
			logger.error("Start server error.", e);
		}
	}

	private void handleKey(SelectionKey key) throws IOException {

		if (key.isAcceptable()) {

			// Receive connect request
			ServerSocketChannel server = (ServerSocketChannel) key.channel();
			SocketChannel channel = server.accept();
			channel.configureBlocking(false);
			channel.register(selector, SelectionKey.OP_READ);

		} else if (key.isReadable()) {

			// Read message from client
			SocketChannel channel = (SocketChannel) key.channel();

			if (key.attachment() != null) {

				IHandler handler = (IHandler) key.attachment();

				if (key.attachment() instanceof DownloadHandler) {

					if (channel.read(clientBuffer) > 0) {

						clientBuffer.flip();
						CharBuffer charBuffer = charSet.decode(clientBuffer);
						String request = charBuffer.toString();
						logger.info("Client >> " + request);

						handler = (IHandler) key.attachment();
						handler.setCommand(request);
						handler.process(selector, channel);

					} else {
						channel.close();
					}

				} else if (key.attachment() instanceof UploadHandler) {
					handler = (IHandler) key.attachment();
					handler.process(selector, channel);
				}

			} else {

				if (channel.read(clientBuffer) > 0) {
					clientBuffer.flip();
					CharBuffer charBuffer = charSet.decode(clientBuffer);
					String request = charBuffer.toString();
					logger.info("Client >> " + request);

					IHandler handler = handlerFactory.getHandler(request);
					SelectionKey wKey = channel.register(selector, SelectionKey.OP_WRITE);
					wKey.attach(handler);

				} else {
					channel.close();
				}

			}

			// Clean message buffer
			clientBuffer.clear();

		} else if (key.isWritable()) {

			// Write message to client
			SocketChannel channel = (SocketChannel) key.channel();
			IHandler handler = (IHandler) key.attachment();
			handler.process(selector, channel);
		}
	}

	public static void main(String[] args) {
		try {
			FESServer server = new FESServer();
			Thread t = new Thread(server);
			t.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
