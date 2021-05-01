package top.shaozuo.geektime.java01.week03.nio.multi;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 使用线程池执行任务
 * 
 * @author shaozuo
 *
 */
final class Handler implements Runnable {

	static ExecutorService pool = Executors.newFixedThreadPool(3);

	static final int MAXIN = 1024;
	static final int MAXOUT = 1024;

	final SocketChannel socketChannel;
	final SelectionKey sk;
	ByteBuffer input = ByteBuffer.allocate(MAXIN);
	ByteBuffer output = ByteBuffer.allocate(MAXOUT);

	Handler(Selector sel, SocketChannel c) throws IOException {
		socketChannel = c;
		c.configureBlocking(false);
		// Optionally try first read now
		sk = socketChannel.register(sel, 0);//注册事件
		sk.attach(this);//注册处理器
		sk.interestOps(SelectionKey.OP_READ);
		sel.wakeup();
	}

	@Override
	public void run() {
		try {
			System.out.println("Handler thread : " + Thread.currentThread().getName());
			ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
			socketChannel.read(byteBuffer);
			pool.submit(new Process(socketChannel, byteBuffer));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}