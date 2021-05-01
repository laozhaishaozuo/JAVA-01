package top.shaozuo.geektime.java01.week03.nio.single;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

final class Handler implements Runnable {

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
			String message = new String(byteBuffer.array(), StandardCharsets.UTF_8);
			System.out.println(socketChannel.getRemoteAddress() + "发来的消息是:" + message);
			socketChannel.write(ByteBuffer.wrap("你的消息我收到了".getBytes(StandardCharsets.UTF_8)));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}