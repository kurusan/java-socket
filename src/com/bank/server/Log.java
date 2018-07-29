package com.bank.server;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Log{
	
	private String login;
	private boolean isAdmin;
	private String logTime;
	
	public Log(String login, boolean isAdmin) {
		this.setLogin(login);
		this.hasAdmin(isAdmin);
		this.logTime = "NOW()";
	}
	
	public String getLogin() {
		return this.login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	public boolean isAdmin() {
		return this.isAdmin;
	}
	
	public void hasAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	
	public String getLogTime() {
		return this.logTime;
	}
	
	public void setLogTime(Date date) {
		DateFormat df = new SimpleDateFormat("yyyyy-mm-dd hh:mm:ss");
		this.logTime =  df.format(date);
	}
	
}
