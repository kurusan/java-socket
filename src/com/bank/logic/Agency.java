package com.bank.logic;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Agency implements Serializable {
	
	private int agID;
	private String agName;
	private String agAddress;
	
	public Agency(){};
	
	public Agency(String agName, String agAddress) {
		this.setAgName(agName);
		this.setAgAddress(agAddress);
	}
	
	public Agency(int agID, String agName, String agAddress) {
		this(agName, agAddress);
		this.setAgID(agID);
	}
	
	public Agency(Agency ag) {
		this(ag.agName, ag.agAddress);
	}

	public int getAgID() {return agID;}
	
	public void setAgID(int agID) {this.agID = agID;}
	
	public String getAgName() {return agName;}
	
	public void setAgName(String agName) {this.agName = agName;}
	
	public String getAgAddress() {return agAddress;}
	
	public void setAgAddress(String agAddress) {this.agAddress = agAddress;}

}
