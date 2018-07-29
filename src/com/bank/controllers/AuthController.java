package com.bank.controllers;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;

import org.apache.commons.codec.digest.DigestUtils;

import com.bank.logic.auth.User;
import com.bank.server.Log;
import com.bank.server.ProvidedServices;
import com.bank.util.WindowLoader;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class AuthController implements Initializable {
	
	@FXML
	private TextField loginTextField;
	@FXML
	private TextField passwordTextField;
	@FXML
	private Button signinButton;
	@FXML
	private Button paramsButton;
	@FXML
	private Button cancelButton;
	
	
	public AuthController() {}
		
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {}	
	
	@FXML
	public void loginListener(ActionEvent event){
		String login = loginTextField.getText();
		String password = passwordTextField.getText();
		
		if(isAuth(login, password)) {
			((Stage)loginTextField.getScene().getWindow()).close();
			loadMain();
		}
		else {
			loginTextField.getStyleClass().add("wrong-credentials");
			passwordTextField.getStyleClass().add("wrong-credentials");
		}
	}
	
	@FXML
	public void cancelListener(ActionEvent event){
		System.exit(0);
	}
	
	@FXML
	public void settingsListener(MouseEvent event){
		loadSettings();
	}
	
	void loadMain() {
		try {
			WindowLoader.getWindow().load("/com/bank/views/settings.fxml", "Paramètre");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	void loadSettings(){
		try {
			WindowLoader.getWindow().load("/com/bank/views/settings.fxml", "Paramètre");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean isAuth(String login, String password) {
		ArrayList<User> admin = ProvidedServices.authRequest(login, password);
		if( admin.size() != 0) {
			Iterator<User> it = admin.iterator();
			User user;
			while(it.hasNext()) {
				user = it.next();
				if(user.getLogin().equals(login) && user.getPassword().equals(password)) {
					if(user.isAdmin()) {/*new Log(admin.get(0).getLogin(), true);*/}
					else {/*new Log(admin.get(0).getLogin(), false);*/}
					
					return true;
				}
			}
		}
		return false;
	}


}
