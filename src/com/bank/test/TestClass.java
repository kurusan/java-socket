package com.bank.test;

import com.bank.server.Server;


public class TestClass {

	public static void main(String[] args) {
		@SuppressWarnings("unused")
		Server server = new Server();
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
