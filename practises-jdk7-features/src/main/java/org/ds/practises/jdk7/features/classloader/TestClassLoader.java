package org.ds.practises.jdk7.features.classloader;


import java.net.URL;
import java.net.URLClassLoader;

/**
 * 验证双亲委派模型
 * 
 */
public class TestClassLoader extends ClassLoader {

	public TestClassLoader() {
	}


	public TestClassLoader(ClassLoader parent) {
		super(parent);
	}


	public static void main(String[] args) {
		try {
			System.out.println("Object classloader: " + Object.class.getClassLoader());
			System.out.println("Thread classloader: " + Thread.class.getClassLoader());

			System.out.println(ClassLoader.getSystemClassLoader());
			System.out.println(ClassLoader.getSystemClassLoader().getParent());
			System.out.println(ClassLoader.getSystemClassLoader().getParent().getParent());

			// System.out.println(TeatClassLoader.class.get);
			URL[] urls = ((URLClassLoader) TestClassLoader.class.getClassLoader()).getURLs();
			for (URL u : urls) {
				System.out.println("url: " + u);
			}

			urls = ((URLClassLoader) TestClassLoader.class.getClassLoader().getParent()).getURLs();
			for (URL u : urls) {
				System.out.println("url: " + u);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
