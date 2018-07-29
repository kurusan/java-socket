package com.bank.dao;

import java.util.ArrayList;

public interface CommonDAOI {
	
	public <Type> ArrayList <Type> select (Class<Type> type, String tableName, String whereClause);
	public int insert (Object object, String tableName);
	public int update (Object object, String tableName, String whereClause);
	public int delete (Object object, String tableName, String whereClause);
	
}