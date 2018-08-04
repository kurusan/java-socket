package com.bank.index;

import com.bank.util.WindowLoader;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) {
		Application.launch(Main.class, args);
	}
	
	public void start(Stage stage) throws Exception {
		WindowLoader.getWindow().loadSignIn();
	}

}
