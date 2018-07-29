package com.bank.test;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MenuTest extends Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub*
		Application.launch(MenuTest.class, args);
	}
	
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/com/bank/views/auth/signin.fxml"));
	    
	    Scene scene = new Scene(root);
	       
	    stage.setTitle("FXML Welcome");
	    stage.setScene(scene);
	    stage.setResizable(false);
	    stage.show();
	 }

}
