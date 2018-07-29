package com.bank.util;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class RequestBuilder {
	/**
	 * 
	 * @param type Specify class type to use introspection tricks.
	 * @param tableName - Name of the table to query on.
	 * @param where - Select option to query tables and get data from it. 
	 * 	Example of use : "id=1" (do not need to add "WHERE").
	 * @return List of object which type is injected on the method when its called.
	 * @throws SQLException
	 */
    public static <Type> ArrayList <Type> getObjectList(Class<Type> type, String tableName, String where) 
    		throws SQLException{
    	
    	ArrayList<Type> list = new ArrayList<Type>(); 
        String sqlRequest = "SELECT * FROM " +  DbHelper.connection.getCatalog() + "." + tableName;
          
        if(!(where == null || where.isEmpty()))
            sqlRequest += " WHERE " + where;
         
        try(Statement stmt = DbHelper.connection.createStatement()){
        	ResultSet resultSet = stmt.executeQuery(sqlRequest);
            while(resultSet.next()){
            	Type typeObject = type.newInstance();
                RequestBuilder.buildObject(resultSet, typeObject);
                 
                list.add(typeObject);
             }
        }
        catch(InstantiationException | IllegalAccessException e){
        	e.printStackTrace();
        }
        return list;
    }
	
    /**
     * 
     * @param zClass
     * @param tableName
     * @return
     * @throws SQLException
     */
	public static String buildInsertRequest(Class<?> zClass, String tableName) 
			throws SQLException{
	
	   StringBuilder fields = new StringBuilder();
	   StringBuilder vars = new StringBuilder();
	
	   for(Field field : zClass.getDeclaredFields()){
		   String fieldName = field.getName();
		   if(fields.length() > 1){
			  fields.append(",");
			  vars.append(",");
		   }
		   fields.append(fieldName);
		   vars.append("?");
	   } 
	   return "INSERT INTO " + DbHelper.connection.getCatalog() + "." + tableName + "(" + fields.toString() + ") "+ 
	   			"VALUES (" + vars.toString() + ")"; 
    }
	
	/**
	 * 
	 * @param zClass
	 * @param tableName
	 * @param primaryKey
	 * @return
	 * @throws SQLException
	 */
    public static String buildUpdateRequest(Class<?> zClass, String tableName, String primaryKey) 
    		throws SQLException{
    	
    	StringBuilder sets = new StringBuilder();
	    String where = null;
	      
	    for(Field field : zClass.getDeclaredFields()){
	    	if(field.getName().equals(primaryKey))
	        	where = " WHERE " + field.getName() + " = ?";
	        else{
	        	if(sets.length() > 1)
	        		sets.append(", ");
	        	
	        	sets.append(field.getName() + " = ?");
	        }
	    }
	    
	    if(where == null)
	        throw new IllegalArgumentException("Primary key not found in '" + zClass.getName() + "'");
	    
	    return "UPDATE " + DbHelper.connection.getCatalog() + "." + tableName + 
	    		" SET " + sets.toString() + where;
	}
    
    
    /**
     * 
     * @param zClass
     * @param tableName
     * @param primaryKey
     * @return
     * @throws SQLException
     */
    public static String buildDeleteRequest(Class<?> zClass, String tableName, String primaryKey) 
    		throws SQLException{
    	
		String where = null;
	    for(Field field : zClass.getDeclaredFields()){
	    	if(field.getName().equals(primaryKey))
	        	where = " WHERE " + field.getName() + " = ?";
		}
	    if(where == null)
	        throw new IllegalArgumentException("Primary key not found in '" + zClass.getName() + "'");
	    
	    return  "DELETE FROM " + DbHelper.connection.getCatalog() + "." + tableName + where;
	}
    
    /**
     * 
     * @param object
     * @param tableName
     * @return
     * @throws SQLException
     */
    public static PreparedStatement buildInsertStatement(Object object, String tableName)
    	throws SQLException{
			
   		PreparedStatement stmtRequest = null;
		
   		try{
			Class<?> zClass = object.getClass(); 
			
			String sqlRequest = RequestBuilder.buildInsertRequest(zClass, tableName);
			
			stmtRequest = DbHelper.connection.prepareStatement(sqlRequest);
			
			Field[] fields = zClass.getDeclaredFields();
			for(int i = 0; i < fields.length; i++){		
				fields[i].setAccessible(true); 
				stmtRequest.setObject((i+1), (Object)fields[i].get(object));
			}
		}
		catch(SecurityException | IllegalArgumentException | IllegalAccessException e){
			throw new RuntimeException("Unable to create prepared statement: " + e.getMessage(), e);
		} 
		return stmtRequest;
	}
	
    
    /**
     * 
     * @param object
     * @param tableName
     * @param primaryKey
     * @return
     * @throws SQLException
     */
    public static PreparedStatement buildUpdateStatement(Object object, String tableName, String primaryKey) 
    		throws SQLException{
	   
	   PreparedStatement stmt = null;
	   try{
			Class<?> zClass = object.getClass();
			String sqlRequest = RequestBuilder.buildUpdateRequest(zClass, tableName, primaryKey);
			
			stmt =  DbHelper.connection.prepareStatement(sqlRequest);
			Field [] fields = zClass.getDeclaredFields();
			int pkSequence = fields.length;
			
			for(int i = 0; i < fields.length; i++){
				fields[i].setAccessible(true);
			
				if(fields[i].getName().equals(primaryKey))
	               stmt.setObject(pkSequence, (Object)fields[i].get(object));
				else
	               stmt.setObject(i, (Object) fields[i].get(object));
			}
	   }
	   catch(SecurityException | IllegalArgumentException | IllegalAccessException e){
		   throw new RuntimeException("Unable to create PreparedStatement: " + e.getMessage(),e);
	   }
       return stmt;
	}
	
    
    /**
     * 
     * @param object
     * @param tableName
     * @param primaryKey
     * @return
     * @throws SQLException
     */
	public static PreparedStatement buildDeleteStatement(Object object, String tableName, String primaryKey) 
    		throws SQLException{
	   
	   PreparedStatement stmt = null;
	   try{
			Class<?> zClass = object.getClass();
			String sqlRequest = RequestBuilder.buildDeleteRequest(zClass, tableName, primaryKey);
			
			stmt =  DbHelper.connection.prepareStatement(sqlRequest);
			Field [] fields = zClass.getDeclaredFields();
			
			for(int i = 0; i < fields.length; i++){
				fields[i].setAccessible(true);		
				if(fields[i].getName().equals(primaryKey))
	               stmt.setObject(1, (Object)fields[i].get(object));
			}
	   }
	   catch(SecurityException | IllegalArgumentException | IllegalAccessException e){
		   throw new RuntimeException("Unable to create PreparedStatement: " + e.getMessage(),e);
	   }
       return stmt;
	}
	
	
	/*
	 * Helper to retrieve database content as the needed model object.
	 */
	private static void buildObject(ResultSet resultSet, Object object) 
			throws IllegalArgumentException, IllegalAccessException, SQLException{
	   
	   Class<?> zclass = object.getClass();
		
	   for(Field field : zclass.getDeclaredFields()){
		   String name = field.getName();
		   field.setAccessible(true);
			
		   Object value = resultSet.getObject(name);
		   Class<?> type = field.getType();
			
		   if(RequestBuilder.isPrimitive(type))
			   value = RequestBuilder.getPrimitiveClass(type).cast(value);
			
		   field.set(object, value);
	   }
    }

	/*
	 * Check if the class given in parameter is primitive or not. Its a helper to build object.
	 */
    private static boolean isPrimitive(Class<?> type){
	   return (type == int.class || type == long.class || type == double.class || type == float.class
	   || type == boolean.class || type == byte.class || type == char.class || type == short.class);
    }
    
    /*
     * Retrieve the primitive class. Its helper to build object.
     */
    private static Class<?> getPrimitiveClass(Class<?> type){
	   if (type == int.class) {return Integer.class; }
	   else if (type == long.class) {return Long.class;}
	   else if (type == double.class) {return Double.class;}
	   else if (type == float.class) {return Float.class;}
	   else if (type == boolean.class) {return Boolean.class;}
	   else if (type == byte.class) {return Byte.class;}
	   else if (type == char.class) {return Character.class;}
	   else if (type == short.class) {return Short.class;}
	   else {throw new IllegalArgumentException("class '" + type.getName() + "' is not a primitive");}
    }
}
