package org.ds.practises.jdk7.features.weak;


import java.lang.ref.WeakReference;

public class TestWeakReference {

	public static void main(String[] args) throws InterruptedException {

		WeakReference<String> wr1 = new WeakReference<String>("abc");
		WeakReference<Integer> wr2 = new WeakReference<Integer>(123);

		long t = System.currentTimeMillis();
		synchronized (TestWeakReference.class) {

			String s = wr1.get();
			while (true) {
				System.out.println(" String: " + s);
				System.out.println(" String: " + wr1.get());
				System.out.println("Integer: " + wr2.get());
				System.out.println("----------------------- " + (System.currentTimeMillis() - t));
				TestWeakReference.class.wait(2000);
			}
		}
	}

}
