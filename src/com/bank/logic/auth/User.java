package com.bank.logic.auth;

import java.io.Serializable;

@SuppressWarnings("serial")
public class User implements Serializable {

	private int ID;
	private String login;
	private String password;
	private boolean isAdmin;
	
	public User() {}
	
	public User(int id, String login, String password, boolean isAdmin) {
		this.setID(id);
		this.setLogin(login);
		this.setPassword(password);
		this.hasAdmin(isAdmin);
	}
	
	public int getID() {
		return this.ID;
	}
	
	public void setID(int id) {
		this.ID = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public boolean isAdmin() {
		return this.isAdmin;
	}
	
	public void hasAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	
}
