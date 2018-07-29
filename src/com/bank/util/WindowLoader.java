package com.bank.util;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class WindowLoader {
	
	public void load(String location, String title) throws IOException {
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

}
