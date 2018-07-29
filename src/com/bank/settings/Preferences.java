package com.bank.settings;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.logging.Level;
import java.util.logging.Logger;

//import org.apache.commons.codec.digest.DigestUtils;

import com.bank.util.AlertHelper;
import com.google.gson.Gson;

public class Preferences {
	
	public static final String CONFIG_FILE = "config.properties";
	
	String DRIVER;
	String DB_URL;
	String DB_USER;
	String DB_PASSWORD;
	String LOCAL_ADDRESS;
	int LOCAL_PORT;
	
	
	public Preferences() {
		DRIVER = "com.mysql.jdbc.Driver";
		DB_URL = "jdbc:mysql://127.0.0.1/bank";
		DB_USER = "root";
		DB_PASSWORD = "root";
		LOCAL_ADDRESS = "localhost";
		LOCAL_PORT = 55000;
	}


	public String getDRIVER() {
		return DRIVER;
	}


	public void setDRIVER(String dRIVER) {
		DRIVER = dRIVER;
	}


	public String getDB_URL() {
		return DB_URL;
	}


	public void setDB_URL(String dB_URL) {
		DB_URL = dB_URL;
	}


	public String getDB_USER() {
		return DB_USER;
	}


	public void setDB_USER(String dB_USER) {
		DB_USER = dB_USER;
	}


	public String getDB_PASSWORD() {
		return DB_PASSWORD;
	}


	public void setDB_PASSWORD(String dB_PASSWORD) {
		//DB_PASSWORD = DigestUtils.shaHex(dB_PASSWORD);
		DB_PASSWORD = dB_PASSWORD;
	}


	public String getLOCAL_ADDRESS() {
		return LOCAL_ADDRESS;
	}


	public void setLOCAL_ADDRESS(String lOCAL_ADDRESS) {
		LOCAL_ADDRESS = lOCAL_ADDRESS;
	}


	public int getLOCAL_PORT() {
		return LOCAL_PORT;
	}


	public void setLOCAL_PORT(int lOCAL_PORT) {
		LOCAL_PORT = lOCAL_PORT;
	}
	
	public static void initConfig() {
		Writer writer = null;
		try {
			Preferences preference = new Preferences();
			Gson gson = new Gson();
			writer = new FileWriter(CONFIG_FILE);
			gson.toJson(preference, writer);
		} catch (IOException e) {
			e.printStackTrace();
			Logger.getLogger(Preferences.class.getName()).log(Level.SEVERE, null, e);
		} finally {
			try {
				writer.close();
			}catch(IOException e) {
				Logger.getLogger(Preferences.class.getName()).log(Level.SEVERE, null, e);
			}
		}
	}
	
	public static Preferences getPreferences() {
		Gson gson = new Gson();
		Preferences preferences = null;
		try {
			preferences = gson.fromJson(new FileReader(CONFIG_FILE), Preferences.class);
		}catch(IOException e) {
			initConfig();
			Logger.getLogger(Preferences.class.getName()).log(Level.SEVERE, null, e);
		}
		return preferences;
	}
	
	public static void setPreferences(Preferences preferences) {
		Writer writer = null;
		try {
			Gson gson = new Gson();
			writer = new FileWriter(CONFIG_FILE);
			gson.toJson(preferences, writer);
			
			AlertHelper.showSimpleAlerte("Effectué", "Paramètre mis à jour!");
		} catch (IOException e) {
			e.printStackTrace();
			Logger.getLogger(Preferences.class.getName()).log(Level.SEVERE, null, e);
			AlertHelper.showErrorMessage("Erreur", "Impossible de sauvegarder le fichier !");
		} finally {
			try {
				writer.close();
			}catch(IOException e) {
				Logger.getLogger(Preferences.class.getName()).log(Level.SEVERE, null, e);
			}
		}
	}
	
	
}
