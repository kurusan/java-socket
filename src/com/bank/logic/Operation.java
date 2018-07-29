package com.bank.logic;

import java.io.Serializable;


@SuppressWarnings("serial")
public class Operation implements Serializable{
	
	private int opID;
	private String opLib;
	private String opDate;
	private double opAmt;
	private String opSens;
	private String actID;
	
	public Operation(){}
	
	public Operation(String opLib, String opDate, double optAmt, String optSens, String actID) {
		this.setOpLib(opLib);
		this.setOpDate(opDate);
		this.setOptAmt(optAmt);
		this.setOptSens(optSens);
		this.setActID(actID);
	}
	
	public Operation(Operation op) {
		this(op.opLib, op.opDate, op.opAmt, op.opSens, op.actID);
	}
	
	public Operation(int opID, String opLib, String opDate, double optAmt, String optSens, String actID) {
		this(opLib, opDate, optAmt, optSens, actID);
		this.setOpID(opID);
	}

	public int getOpID() {return opID;}

	public void setOpID(int opID) {this.opID = opID;}

	public String getOpLib() {return opLib;}

	public void setOpLib(String opLib) {this.opLib = opLib;}

	public String getOpDate() {return opDate;}

	public void setOpDate(String opDate) {this.opDate = opDate;}

	public double getOptAmt() {return opAmt;}

	public void setOptAmt(double optAmt) {this.opAmt = optAmt;}

	public String getOptSens() {return opSens;}

	public void setOptSens(String optSens) {this.opSens = optSens;}

	public String getActID() {return actID;}

	public void setActID(String actID) {this.actID = actID;}
	
}
