package top.shaozuo.geektime.java01.week03.nio.single;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * 单Reactor单线程模型
 * 
 * @author shaozuo
 *
 */
public class SingleWorkerThreadReactor implements Runnable {

	final Selector selector;
	final ServerSocketChannel serverSocket;

	public SingleWorkerThreadReactor(int port) throws IOException {
		selector = Selector.open();
		serverSocket = ServerSocketChannel.open();
		serverSocket.configureBlocking(false);
		serverSocket.socket().bind(new InetSocketAddress(port));
		serverSocket.register(selector, SelectionKey.OP_ACCEPT);
		SelectionKey sk = serverSocket.register(selector, SelectionKey.OP_ACCEPT);
		sk.attach(new Acceptor(serverSocket, selector));
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				selector.select();
				Set<SelectionKey> selected = selector.selectedKeys();
				Iterator<SelectionKey> iterator = selected.iterator();
				while (iterator.hasNext()) {
					SelectionKey selectionKey = iterator.next();
					dispatch(selectionKey);
					iterator.remove();
				}
				selected.clear();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	void dispatch(SelectionKey k) {
		Runnable r = (Runnable) (k.attachment());
		if (r != null) {
			r.run();//处理 accept 与 read 等事件
		}
	}

	public static void main(String[] args) throws IOException {
		new Thread(new SingleWorkerThreadReactor(1234)).start();
	}

}
