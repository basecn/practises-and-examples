package org.ds.practises.jdk7.features.nio;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.Set;

public class TestServerSocketChannel implements Runnable {

	Selector select = null;

	volatile boolean connected = false;

	// private int ops = SelectionKey.OP_ACCEPT | SelectionKey.OP_CONNECT |
	// SelectionKey.OP_READ | SelectionKey.OP_WRITE;

	private static final int ECHOMAX = 255; // Maximum size of echo datagram


	public static void main(String[] args) throws IOException, InterruptedException {
		TestServerSocketChannel test = new TestServerSocketChannel();
		test.connect();
		test.listen();
	}


	public TestServerSocketChannel() throws IOException {

	}

	static class ClientRecord {

		public SocketAddress clientAddress;

		public ByteBuffer buffer = ByteBuffer.allocate(ECHOMAX);
	}


	public void connect() throws IOException, InterruptedException {
		// 创建Selector
		select = Selector.open();

		// 创建通道
		ServerSocketChannel server = ServerSocketChannel.open();
		server.configureBlocking(false);
		// server.register(select, SelectionKey.OP_ACCEPT);
		server.register(select, SelectionKey.OP_ACCEPT, new ClientRecord());

		ServerSocket socket = server.socket();
		InetSocketAddress address = new InetSocketAddress(18081);
		socket.bind(address);

		System.out.println("Start server");

		synchronized (this) {
			while (true) {
				this.wait();
			}
		}
	}


	private void process(SelectionKey key) {
		if (key.isAcceptable()) {
			System.out.println("isAcceptable");
		} //
		else if (key.isConnectable()) {
			System.out.println("isConnectable");
		} //
		else if (key.isReadable()) {
			System.out.println("isReadable");
		} //
		else if (key.isWritable()) {
			System.out.println("isWritable");
		} //
		else {
			System.out.println("");
		} // end if

	}


	public void listen() {

		Set<SelectionKey> selectedKeys = select.selectedKeys();
		Iterator<SelectionKey> itr = selectedKeys.iterator();
		while (itr.hasNext()) {
			SelectionKey key = itr.next();
			process(key);
			itr.remove();
		}

	}


	public void run() {
		// TODO Auto-generated method stub

	}

}
