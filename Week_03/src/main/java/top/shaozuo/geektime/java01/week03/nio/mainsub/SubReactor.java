package top.shaozuo.geektime.java01.week03.nio.mainsub;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;
import java.util.Set;

public class SubReactor implements Runnable {
	private Selector selector;

	public SubReactor(Selector selector) {
		this.selector = selector;
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				selector.select();
				System.out.println("selector : " + selector.toString() + " thread : "
				        + Thread.currentThread().getName());
				Set<SelectionKey> selectionKeys = selector.selectedKeys();
				Iterator<SelectionKey> iterator = selectionKeys.iterator();
				while (iterator.hasNext()) {
					SelectionKey selectionKey = iterator.next();
					dispatcher(selectionKey);
					iterator.remove();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void dispatcher(SelectionKey selectionKey) {
		Runnable runnable = (Runnable) selectionKey.attachment();
		runnable.run();
	}
}
