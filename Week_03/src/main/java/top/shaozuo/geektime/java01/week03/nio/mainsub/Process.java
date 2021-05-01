package top.shaozuo.geektime.java01.week03.nio.mainsub;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

final class Process implements Runnable {

	final SocketChannel socketChannel;
	final ByteBuffer byteBuffer;

	Process(SocketChannel c, ByteBuffer byteBuffer) throws IOException {
		socketChannel = c;
		this.byteBuffer = byteBuffer;
	}

	@Override
	public void run() {
		try {
			System.out.println("Process thread:" + Thread.currentThread().getName());
			String message = new String(byteBuffer.array(), StandardCharsets.UTF_8);
			System.out.println(socketChannel.getRemoteAddress() + "发来的消息是:" + message);
			socketChannel.write(ByteBuffer.wrap("你的消息我收到了".getBytes(StandardCharsets.UTF_8)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}