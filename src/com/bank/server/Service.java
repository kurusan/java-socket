package com.bank.server;

import java.io.IOException;
import java.net.Socket;

import com.bank.logic.Account;
import com.bank.logic.Agency;
import com.bank.logic.Customer;
import com.bank.logic.Operation;

/**
 * 
 * @author Kurusan
 *
 */
public class Service extends Thread{
	
	private Socket socket = null;
	
	public Service(Socket socket){
		this.socket = socket;
	}
	
	/**
	 * This method is client request controller. Its catch client request sent by using a call method.
	 * This will do test to define which service is needed and call the appropriate Provided Service Method.
	 */
	public void run(){
		/*
		 * This string "call" variable will contain client sent request String to define the method to call.
		 * Its a very important variable as we can read a stream only once. So we need this temporary variable to
		 * save the stream content.
		 */
		String call;
		do{
			try {
				// Deserialization to get string object which contain called method name.
				 call = (String)(SerializationClass.deSerialization(socket));
				 
				 /*
				  * Beginning of create method test
				  */
				if(call.equals("createAgency")){
					// Deserialization to get agency object.
					ProvidedServices.createAgency((Agency)SerializationClass.deSerialization(socket));
				}
				else if(call.equals("createCustomer")){
					// Deserialization to get customer object.
					ProvidedServices.createCustomer((Customer)SerializationClass.deSerialization(socket));
				}
				else if(call.equals("createAccount")){
					// Deserialization to get account object.
					ProvidedServices.createAccount((Account)SerializationClass.deSerialization(socket));
				}
				else if(call.equals("createOperation")){
					// Deserialization to get operation object.
					ProvidedServices.createOperation((Operation)SerializationClass.deSerialization(socket));
				}
				/*
				 * End of create method test
				 */
				
				/*
				 * Beginning of retrieve database content method test
				 */
				else if(call.equals("getAgencies")){
					// Serialization to send to the client ProviderServices.getAgencies() returned List.
					SerializationClass.serialization(socket, ProvidedServices.getAgencies());
				}
				else if(call.equals("getAccounts")){
					// Serialization to send to the client ProviderServices.getAccount() returned List.
					SerializationClass.serialization(socket, ProvidedServices.getAccounts());
				}
				else if(call.equals("getCustomers")){
					// Serialization to send to the client ProviderServices.getCustomer() returned List.
					SerializationClass.serialization(socket, ProvidedServices.getCustomers());
				}
				else if(call.equals("getOperations")){
					// Serialization to send to the client ProviderServices.getOperation() returned List.
					SerializationClass.serialization(socket, ProvidedServices.getOperations());
				}
				else if(call.equals("getCustomersByAgency")){
					SerializationClass.serialization(socket, ProvidedServices.getCustomersByAgency(
							(String)SerializationClass.deSerialization(socket)));
				}
				else if(call.equals("getAccountsByCustomer")){
					SerializationClass.serialization(socket, ProvidedServices.getCustomersByAgency(
							(String)SerializationClass.deSerialization(socket)));
				}
				else if(call.equals("getAccountsStat")){
					SerializationClass.serialization(socket, ProvidedServices.getAccountStat(
							(String)SerializationClass.deSerialization(socket)));
				}
				
				/*
				 * End of retrieve database content method test
				 */
				/*
				 * Beginning of delete method test
				 */
				else if(call.equals("deleteCustomer")){
					SerializationClass.serialization(socket, ProvidedServices.deleteCustomer(
							(Customer)SerializationClass.deSerialization(socket),
							(String)SerializationClass.deSerialization(socket)));
				}
				else if(call.equals("deleteAgency")){
					SerializationClass.serialization(socket, ProvidedServices.deleteAgency(
							(Agency)SerializationClass.deSerialization(socket),
							(String)SerializationClass.deSerialization(socket)));
				}
				else if(call.equals("deleteOperation")){
					SerializationClass.serialization(socket, ProvidedServices.deleteOperation(
							(Operation)SerializationClass.deSerialization(socket),
							(String)SerializationClass.deSerialization(socket)));
				}
				else if(call.equals("deleteAccount")){
					SerializationClass.serialization(socket, ProvidedServices.deleteAccount(
							(Account)SerializationClass.deSerialization(socket),
							(String)SerializationClass.deSerialization(socket)));
				}
				/*
				 * End of delete method test
				 */
				
				/*
				 * Beginning of update method test
				 */
				else if(call.equals("updateCustomer")){
					SerializationClass.serialization(socket, ProvidedServices.updateCustomer(
							(Customer)SerializationClass.deSerialization(socket),
							(String)SerializationClass.deSerialization(socket)));
				}
				else if(call.equals("updateAgency")){
					
					SerializationClass.serialization(socket, ProvidedServices.updateAgency(
							(Agency)SerializationClass.deSerialization(socket),
							(String)SerializationClass.deSerialization(socket)));	
					
				}
				else if(call.equals("updateOperation")){
					SerializationClass.serialization(socket, ProvidedServices.updateOperation(
							(Operation)SerializationClass.deSerialization(socket),
							(String)SerializationClass.deSerialization(socket)));
				}
				else if(call.equals("updateAccount")){
					SerializationClass.serialization(socket, ProvidedServices.updateAccount(
							(Account)SerializationClass.deSerialization(socket),
							(String)SerializationClass.deSerialization(socket)));
				}
				/*
				 * End of update method test
				 */
				
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}
		}while(true);
	}

}
