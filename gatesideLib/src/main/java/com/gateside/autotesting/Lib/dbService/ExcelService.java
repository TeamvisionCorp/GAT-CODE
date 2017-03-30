package com.gateside.autotesting.Lib.dbService;




import java.util.List;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.gateside.autotesting.Lib.common.SimpleLogger;

public class ExcelService
{
	private String connectionString="jdbc:odbc:driver={Microsoft Excel Driver (*.xls)};DBQ=";
	private final String EXCELDRIVERNAME="sun.jdbc.odbc.JdbcOdbcDriver";
	
	public ExcelService(){}
	
	/**
	 * 
	 * @param filePath  excel file path
	 */
	public ExcelService(String filePath)
	{
        this.setConnectionString(this.connectionString+filePath);		
	}
	
	/**
	 * 
	 * @return  String connectionString
	 */
	public String getConnectionString() {
		return connectionString;
	}

	/**
	 * 
	 * @param connectionString  excel db connection string
	 */
	public void setConnectionString(String connectionString) {
		this.connectionString = connectionString;
	}

	/**
	 * 
	 * @param sqlCommandText sql text
	 * @return ResultSet
	 */
	public ResultSet executeQuery(String sqlCommandText)
	{
		ResultSet resultSet=null;
		Statement statement=null;
		try 
		{
			Connection connection =getConnection(this.getConnectionString());
			statement=connection.createStatement();
			resultSet= statement.executeQuery(sqlCommandText);
		} 
		catch (Exception e)
		{
			SimpleLogger.logError(this.getClass(),e);
			
		}
		return resultSet;
	}

	
	/**
	 * 
	 * @param commandText sql command text
	 */
	public void executeNoneQuery(String commandText) 
	{
		try
		{
			Connection connection =getConnection(this.getConnectionString());
	    	Statement statement=connection.createStatement();
			statement.execute(commandText);
		}
		catch (Exception e)
		{
			SimpleLogger.logError(this.getClass(),e);
		}
    	
	}
	
	/**
	 * 
	 * @param connectionString  excel connection string
	 * @return Connection
	 * @throws Exception exception
	 */
	private Connection getConnection(String connectionString) throws Exception
	{
		Class.forName(this.EXCELDRIVERNAME);
		Connection connection = DriverManager.getConnection(connectionString);
		return connection;
	}




}
