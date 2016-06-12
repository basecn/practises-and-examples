package org.ds.practises.jdk7.features.nio;


import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 *
 */
public class TestMappedByteBuffer {

	/**
	 * @param args
	 * @throws IOException
	 */
	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {

		long t = System.currentTimeMillis();

		RandomAccessFile fromFile = new RandomAccessFile("./test.csv", "rw");
		FileChannel fc = fromFile.getChannel();

		long cnt = fc.size();
		long len = 1024;

		// 映像
		MappedByteBuffer mbb = fc.map(FileChannel.MapMode.READ_ONLY, cnt >> 1, len);

		System.out.println("position: " + mbb.position());
		System.out.println("limit: " + mbb.limit());
		System.out.println("capacity: " + mbb.capacity());

		byte[] c = new byte[1024];
		mbb.get(c);
		System.out.println(new String(c));

		System.out.println("Size: " + cnt + ", used: " + (System.currentTimeMillis() - t));
	}

}
