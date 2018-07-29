package com.bank.controllers;
import java.net.URL;
import java.util.ResourceBundle;

import com.bank.settings.Preferences;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SettingsController implements Initializable {
	
	@FXML
	private TextField driverTextField;
	@FXML
	private TextField dbUrlTextField;
	@FXML
	private TextField dbUserTextField;
	@FXML
	private TextField dbPassTextField;
	@FXML
	private TextField addressTextField;
	@FXML
	private TextField portTextField;
	@FXML
	private Button saveButton;
	@FXML
	private Button cancelButton;
	
	
	public SettingsController() {}
		
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initFields();
	}
	
	@FXML
	private void saveListener(ActionEvent event){
		Preferences preferences = Preferences.getPreferences();
		preferences.setDRIVER(driverTextField.getText());
		preferences.setDB_URL(dbUrlTextField.getText());
		preferences.setDB_USER(dbUserTextField.getText());
		preferences.setDB_PASSWORD(dbPassTextField.getText());
		preferences.setLOCAL_ADDRESS(addressTextField.getText());
		preferences.setLOCAL_PORT(Integer.parseInt(portTextField.getText()));
		
		Preferences.setPreferences(preferences);
	}
	
	@FXML
	private void cancelListener(ActionEvent event){
		((Stage)driverTextField.getScene().getWindow()).close();
	}
	
	private void initFields() {
		Preferences preferences = Preferences.getPreferences();
		driverTextField.setText(preferences.getDRIVER());
		dbUrlTextField.setText(preferences.getDB_URL());
		dbUserTextField.setText(preferences.getDB_USER());
		dbPassTextField.setText(preferences.getDB_PASSWORD());
		addressTextField.setText(preferences.getLOCAL_ADDRESS());
		portTextField.setText(String.valueOf(preferences.getLOCAL_PORT()));
	}

}
