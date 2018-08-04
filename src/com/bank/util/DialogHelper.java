package com.bank.util;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;

import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

public class DialogHelper {
	
	public static void showSimpleDialog(StackPane panel, String message) {
		JFXDialogLayout dialogLayout = new JFXDialogLayout();
		JFXButton jfxButton = new JFXButton("OK"); 
		JFXDialog dialog = new JFXDialog(panel, dialogLayout, JFXDialog.DialogTransition.TOP);
		jfxButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent mouseEvent) ->{
			dialog.close();
		});
		
		dialogLayout.setHeading(new Label(message));
		dialogLayout.setActions(jfxButton);
		dialog.show();
	}

}
