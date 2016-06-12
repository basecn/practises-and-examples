package org.ds.practises.jdk7.features.nio;


import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

/**
 *
 */
public class TestFileChannel {

	/**
	 * @param args
	 * @throws IOException
	 */
	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {

		long t = System.currentTimeMillis();

		RandomAccessFile fromFile = new RandomAccessFile("./test.csv", "rw");
		FileChannel fc = fromFile.getChannel();

		RandomAccessFile toFile = new RandomAccessFile("./output.csv", "rw");
		FileChannel tc = toFile.getChannel();

		long pos = 0;
		long cnt = fc.size();

		tc.transferFrom(fc, pos, cnt);

		System.out.println("Size: " + cnt + ", used: " + (System.currentTimeMillis() - t));
	}

}
