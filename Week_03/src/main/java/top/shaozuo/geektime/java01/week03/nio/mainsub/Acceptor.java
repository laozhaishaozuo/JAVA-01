package top.shaozuo.geektime.java01.week03.nio.mainsub;

import java.io.IOException;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Acceptor implements Runnable {
	private ServerSocketChannel serverSocketChannel;
	private static final int CORE = 8;
	static ExecutorService pool = Executors.newFixedThreadPool(CORE);

	private int index;

	private SubReactor[] subReactors = new SubReactor[CORE];
	private Thread[] threads = new Thread[CORE];
	private final Selector[] selectors = new Selector[CORE];

	public Acceptor(ServerSocketChannel serverSocketChannel) {
		this.serverSocketChannel = serverSocketChannel;
		for (int i = 0; i < CORE; i++) {
			try {
				selectors[i] = Selector.open();
			} catch (IOException e) {
				e.printStackTrace();
			}
			//pool.execute(new SubReactor(selectors[i]));
			subReactors[i] = new SubReactor(selectors[i]);
			threads[i] = new Thread(subReactors[i]);
			threads[i].start();
		}
	}

	@Override
	public void run() {
		try {
			System.out.println("Acceptor thread : " + Thread.currentThread().getName());
			SocketChannel socketChannel = serverSocketChannel.accept();
			System.out.println("有客户端连接上来了," + socketChannel.getRemoteAddress());
			new Handler(selectors[index], socketChannel);
			System.out.println(index);
			index = (index + 1) % CORE;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
