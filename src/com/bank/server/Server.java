package com.bank.server;


import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import com.bank.settings.Preferences;

public class Server {
	
	private InetAddress inetAddress;
	private static int count;
	Preferences preference;
	
	@SuppressWarnings("resource")
	public Server(){
		boolean connected = true;
		try{
			/* Retrieve properties file */
			preference = Preferences.getPreferences();	
			
	        ServerSocket serverSocket = new ServerSocket(preference.getLOCAL_PORT());
	        while(connected){
	        	Socket socket = serverSocket.accept();
	            inetAddress = socket.getInetAddress();
	            Service service = new Service(socket);
	            service.start();
	            countHost();       
	        }
	    }
	    catch(IOException ex){
	    	connected = false;
	        System.out.println(ex.getMessage());
	    }	
	}
	
	private int countHost(){
		return count++;
	}
	
	public String getInetAddress(){
		return this.inetAddress.toString();
	}

}
