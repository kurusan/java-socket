package com.bank.logic;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Customer implements Serializable{
	
	private int cusID;
	private String cusName;
	private String cusFirstname;
	private int agID;
	
	public Customer(){}
	
	public Customer(String cusName, String cusFirstname, int agID){
		this.setCusName(cusName);
		this.setCusFirstname(cusFirstname);
		this.setAgID(agID);
	}

	public Customer(int cusID, String cusName, String cusFirstname, int agID){
		this(cusName, cusFirstname, agID);
		this.setCusID(cusID);
	}
	
	public Customer(Customer cus){
		this(cus.cusName, cus.cusFirstname, cus.agID);
	}

	public int getCusID() {return cusID;}

	public void setCusID(int cusID) {this.cusID = cusID;}

	public String getCusName() {return cusName;}

	public void setCusName(String cusName) {this.cusName = cusName;}

	public String getCusFirstname() {return cusFirstname;}

	public void setCusFirstname(String cusFirstname) {this.cusFirstname = cusFirstname;}

	public int getAgID() {return agID;}

	public void setAgID(int agID) {this.agID = agID;}
}
