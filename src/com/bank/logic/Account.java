package com.bank.logic;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Account implements Serializable{
	
	private String actID;
	private String actLib;
	private String sens;
	private double balance;
	private int cusID;
	
	public Account(){}
	
	public Account(String actID, String actLib, String sens, double balance, int cusID) {
		this.setActID(actID);
		this.setActLib(actLib);
		this.setSens(sens);
		this.setBalance(balance) ;
		this.setCusID(cusID);
	}
	
	public Account(Account act) {
		this(act.actID, act.actLib, act.sens, act.balance, act.cusID);
	}

	public String getActID() {return actID;}
	
	public void setActID(String actID) {this.actID = actID;}
	
	public String getActLib() {return actLib;}
	
	public void setActLib(String actLib) {this.actLib = actLib;}
	
	public String getSens() {return sens;}
	
	public void setSens(String sens) {this.sens = sens;}
	
	public double getBalance() {return balance;}
	
	public void setBalance(double balance) {this.balance = balance;}
	
	public int getCusID() {return cusID;}
	
	public void setCusID(int cusID) {this.cusID = cusID;}
	
}
