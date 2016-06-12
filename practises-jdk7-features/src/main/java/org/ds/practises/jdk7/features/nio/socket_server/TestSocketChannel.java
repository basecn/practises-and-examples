package org.ds.practises.jdk7.features.nio.socket_server;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class TestSocketChannel {

	public TestSocketChannel() {
		// TODO Auto-generated constructor stub
	}

	private static final int TIMEOUT = 3000; // Wait timeout (milliseconds)

	private static final int MAXTRIES = 1024;


	public static void main(String[] args) throws IOException {
		// Convert input String to bytes using the default charset
		byte[] bytesToSend = "0123456789abcdefghijklmnopqrstuvwxyz".getBytes();

		// Create channel and set to nonblocking
		DatagramChannel datagramChannel = DatagramChannel.open();
		datagramChannel.configureBlocking(false);
		datagramChannel.socket().setSoTimeout(TIMEOUT);

		ByteBuffer writeBuf = ByteBuffer.wrap(bytesToSend);
		ByteBuffer readBuf = ByteBuffer.allocate(MAXTRIES);

		datagramChannel = datagramChannel.connect(new InetSocketAddress("127.0.0.1", 18081));

		int totalBytesRcvd = 0; // Total bytes received so far
		int bytesRcvd; // Bytes received in last read
		while (totalBytesRcvd < bytesToSend.length) {
			if (writeBuf.hasRemaining()) {
				datagramChannel.write(writeBuf);
			}
			if ((bytesRcvd = datagramChannel.read(readBuf)) == -1) {
				throw new SocketException("Connection closed prematurely");
			}
			totalBytesRcvd += bytesRcvd;
			System.out.print("."); // Do something else
		}

		System.out.println("Received: " + new String(readBuf.array(), 0, totalBytesRcvd));
		datagramChannel.close();
	}

}
