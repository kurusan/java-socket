package com.bank.util;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class WindowLoader {
	
	private void load(String location, String title) throws IOException {
		Parent parent = FXMLLoader.load(getClass().getResource(location)); 
		Stage stage = new Stage(StageStyle.DECORATED);
	    Scene scene = new Scene(parent);
	    stage.setTitle(title);
	    stage.setScene(scene);
	    stage.setResizable(false);
	    stage.show();
	}
	
	public static WindowLoader getWindow() {
		return new WindowLoader();
	}
	
	public void loadMain() {
		try {
			load("/com/bank/views/main.fxml", "Serveur - Pannel d'administration");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void loadSettings(){
		try {
			load("/com/bank/views/settings.fxml", "Serveur - Settings");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void loadUser() {
		try {
			load("/com/bank/views/user.fxml", "Serveur - Ajouter utilisateur");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void loadSignIn() {
		try {
			load("/com/bank/views/auth/signin.fxml", "Serveur - Se connecter");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void loadHelp() {
		try {
			load("/com/bank/views/help.fxml", "Serveur - Aide");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void loadLogs() {
		try {
			load("/com/bank/views/help.fxml", "Serveur - Aide");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
