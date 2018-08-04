package com.bank.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.bank.logic.Agency;
import com.bank.logic.auth.User;
import com.bank.server.ProvidedServices;
import com.bank.server.Server;

import com.bank.util.WindowLoader;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class MainController implements Initializable{
	
	@FXML
	private ListView<String> logsList;
	@FXML
	private TableView<User> userListTable;
	
	@FXML
	private TableColumn<Agency, String> loginColumn;
	
	@FXML
	private Button startButton;
	@FXML
	private Button addUserButton;
	@FXML
	private Button logsButton;
	@FXML
	private Button settingsButton;
	@FXML
	private FontAwesomeIconView startIcon;
	
	private Server server;
	
	private ObservableList<User> data;
	
	public MainController() {}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		loginColumn.setCellValueFactory(new PropertyValueFactory<Agency, String>("login"));
		data = getInitialTableData();
		userListTable.setItems(data);
	  
		userListTable.setVisible(true);
		
		initServer();
	}
	
	@FXML
	private void logsListSeeder(ActionEvent event){
		ListView<String> logsList = new ListView<String>();
		ObservableList<String> items =FXCollections.observableArrayList (server.getInetAddress());
		logsList.setItems(items);
	}
	
	@FXML
	private void userListSeederListener(ActionEvent event){	
	}
	
	@FXML
	private void startListener(ActionEvent event){
		
		if(server == null) {
			new Thread(new Runnable() {
			    public void run() {
			        server = new Server();
			    }
			
			}).start();
			
			startButton.setText("Arrêter");
			startIcon.getStyleClass().add("stop-server");
		}
		else {
			startButton.setText("Démarrer");
			startIcon.getStyleClass().remove("stop-server");
			initServer();
		}
	}
	
	@FXML
	private void addUserListener(ActionEvent event){
		WindowLoader.getWindow().loadUser();
	}
	
	@FXML
	private void logsListener(ActionEvent event){
		WindowLoader.getWindow().loadLogs();
	}
	
	@FXML
	private void settingsListener(ActionEvent event){
		WindowLoader.getWindow().loadSettings();
	}
	
	private void initServer() {
		server = null;
		this.startButton.setText("Démarrer");
	}
	
	private ObservableList<User> getInitialTableData() {
		ArrayList<User> list = ProvidedServices.getUsers();
		return (ObservableList<User>) FXCollections.observableList(list);
	}
}
