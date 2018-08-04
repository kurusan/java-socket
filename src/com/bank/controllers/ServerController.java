package com.bank.controllers;

import com.bank.server.Server;

public class ServerController {
	
private static Server server = null;
	
	public static Server getClient() {
		if(server == null) {
			return server = new Server();
		}
		else return server;
	}
}
