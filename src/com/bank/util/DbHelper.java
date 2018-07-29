package com.bank.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.bank.settings.Preferences;


public final class DbHelper {
	
	/* Volatile avoid the usage of an existing Connection not really instantiated (equivalent of ThreadLocal on Java 1.2 to 1.4)*/
	public static volatile Connection connection = null;
	private static Preferences preference;
	
	/* Only the class can instantiate himself. This delete the default public constructor*/
	private DbHelper(){super();}
	
	public final static Connection getConnection() 
			throws FileNotFoundException, IOException, ClassNotFoundException, SQLException{
		
		if (DbHelper.connection == null){
			
			/* Guarantee single connection to the database even with multiple thread */
			synchronized(DbHelper.class){	
				if(DbHelper.connection == null){
					
					/* Retrieve properties file */
					preference = Preferences.getPreferences();
					
					/* Get data from properties file */
					/* Instantiate a new connection with the database*/
					Class.forName(preference.getDRIVER());
					
					DbHelper.connection = DriverManager.getConnection(
							preference.getDB_URL(),
							preference.getDB_USER(), 
							preference.getDB_PASSWORD()
					);
				}
			}
		}
		return DbHelper.connection;
	}
}
