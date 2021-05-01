package top.shaozuo.geektime.java01.week03.nio.single;

import java.io.IOException;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

class Acceptor implements Runnable {
	private ServerSocketChannel serverSocketChannel;
	private Selector selector;

	public Acceptor(ServerSocketChannel serverSocketChannel, Selector selector) {
		this.serverSocketChannel = serverSocketChannel;
		this.selector = selector;
	}

	@Override
	public void run() {
		try {
			System.out.println("Acceptor thread : " + Thread.currentThread().getName());
			SocketChannel socketChannel = serverSocketChannel.accept();
			System.out.println("有客户端连接上来了," + socketChannel.getRemoteAddress());
			new Handler(selector, socketChannel);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
