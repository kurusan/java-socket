package com.bank.dao;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import com.bank.util.RequestBuilder;
import com.bank.util.DbHelper;

public final class CommonDAO implements CommonDAOI{
	
	public CommonDAO (){
		try {
			DbHelper.getConnection();
		} catch (ClassNotFoundException | IOException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public <Type> ArrayList<Type> select (Class<Type> type, String tableName, String whereClause) {
		ArrayList<Type> list = null;
		try {
			list =  RequestBuilder.<Type>getObjectList(type, tableName, whereClause);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	@Override
	public int insert (Object object, String tableName) {
		try {
			PreparedStatement prepState = RequestBuilder.buildInsertStatement(object, tableName);
			return prepState.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return -1;
	}
	
	@Override
	public int update (Object object, String tableName, String whereClause) {
		try {
			PreparedStatement prepState = RequestBuilder.buildUpdateStatement(object, tableName, whereClause);
			return prepState.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return -1;
	}

	@Override
	public int delete(Object object, String tableName, String whereClause) {
		try {
			PreparedStatement prepState = RequestBuilder.buildDeleteStatement(object, tableName, whereClause);
			return prepState.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return -1;
	}
}
