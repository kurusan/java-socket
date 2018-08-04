package com.bank.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.bank.logic.auth.User;
import com.bank.server.ProvidedServices;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

public class UserController implements Initializable{
	
	@FXML
	private JFXTextField loginTextField;
	@FXML
	private JFXPasswordField passwordField;
	@FXML
	private JFXPasswordField confirmPasswordField;
	@FXML
	private JFXCheckBox checkBox;
	@FXML
	private JFXButton saveButton;
	@FXML
	private JFXButton cancelButton;
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	}
	
	@FXML
	public void saveListener(ActionEvent event){
		String login = loginTextField.getText();
		boolean check = false;
		if(checkBox.isSelected()) { check = true;}
		
		String password = null;
		
		if(passwordField.getText().equals(confirmPasswordField.getText())) {
			password = passwordField.getText();
			User user = new User(login, password, check);
			ProvidedServices.createUser(user);
		}
		else {
			passwordField.getStyleClass().add("wrong-credentials");
			confirmPasswordField.getStyleClass().add("wrong-credentials");
		}
	}
	
	@FXML
	public void cancelListener(ActionEvent event){
		((Stage)loginTextField.getScene().getWindow()).close();
	}
}
