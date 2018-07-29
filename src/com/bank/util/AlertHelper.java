package com.bank.util;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class AlertHelper {
	
	public static void showSimpleAlerte(String title, String content) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(content);
		alert.showAndWait();
	}
	
	public static void showErrorMessage(String title, String content) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error");
		alert.setHeaderText(title);
		alert.setContentText(content);
		alert.showAndWait();
	}
	
	public static void showConfirmationAlerte(String title, String content) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Réussi");
		alert.setHeaderText(title);
		alert.setContentText(content);
		alert.showAndWait();
	}

}
