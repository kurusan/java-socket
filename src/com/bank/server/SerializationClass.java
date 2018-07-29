package com.bank.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * 
 * @author Kurusan
 *
 */
public class SerializationClass {
	
	static ObjectInputStream objectInputStream;
	static ObjectOutputStream objectOutputStream;
	
	/**
	 * 
	 * @param socket - Socket received from the client
	 * @return The object injected into the socket
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static Object deSerialization(Socket socket) throws IOException, ClassNotFoundException {
		objectInputStream = new ObjectInputStream(socket.getInputStream());		
        Object object = objectInputStream.readObject();
		return object;
	}
	
	/**
	 * 
	 * @param socket - Socket sent by the Server to the client 
	 * @param object  - Object to inject into the socket
	 * @throws IOException
	 */
	public static void serialization(Socket socket, Object object) throws IOException {
		System.out.println("3333");
		objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
		objectOutputStream.writeObject(object);
		objectOutputStream.flush();
	}

}
