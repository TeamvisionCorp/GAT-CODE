package com.gateside.autotesting.Lib.dbService;

import java.util.ArrayList;
import java.util.List;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import com.gateside.autotesting.Lib.common.SimpleLogger;
import com.gateside.autotesting.Lib.dbService.Content.MysqlString;


public class MysqlService
{
	private String connectionString="";
	private final String MYSQLDRIVERNAME="com.mysql.jdbc.Driver";
	
	/**
	 * 
	 * @param connectionString mysql Connection String
	 */
	public MysqlService(String connectionString)
	{
        this.setConnectionString(connectionString);		
	}
	
	/**
	 * 
	 * @return connectionString mysql Connection String
	 */
	public String getConnectionString() {
		return connectionString;
	}

	/**
	 * 
	 * @param connectionString mysql Connection String
	 */
	public void setConnectionString(String connectionString) {
		this.connectionString = connectionString;
	}
	
	/**
	 * 
	 * @param commandText sql command text
	 * @param user  db user
	 * @param password db user password
	 */
	public void executeNoneQuery(String commandText,String user,String password)
	{
      try
      {
    	 SimpleLogger.logInfo(this.getClass(),this.getConnectionString()+commandText);
    	 Connection connection =this.getConnection(this.getConnectionString(),user,password);
         Statement statement=connection.createStatement();
         statement.execute(commandText);
         statement.close();
         connection.close();
      }
      catch(Exception ex)
      {
           SimpleLogger.logError(this.getClass(),ex);
      }
		
	}
	
	/**
	 * 
	 * @param commandText sql command
	 * @param user db user
	 * @param password db user password
	 * @return  List(List(String))
	 * @throws Exception Exception
	 */
	public List<List<String>> executeQuery(String commandText,String user,String password) throws Exception
	{
	    SimpleLogger.logInfo(this.getClass(),this.getConnectionString()+commandText);
		List<List<String>> result=null;
		Connection connection=null;
		Statement statement=null;
		 try
	      {
	    	 connection =this.getConnection(this.getConnectionString(), user, password);
	         if(connection==null){throw new Exception("The connection is null,please check the connect url.");}
	         statement=connection.createStatement();
	         ResultSet resultSet= statement.executeQuery(commandText);
	         ResultSetMetaData resultMetaData = resultSet.getMetaData();
			 result=getData(resultSet, resultMetaData);
			 result.add(0,getColumnNames(resultSet, resultMetaData));
	      }
	      catch(Exception ex)
	      {
	    	  SimpleLogger.logError(this.getClass(),ex);
	      }
		  finally
		  {
			  statement.close();
		      connection.close();
		  }
		 return result;
	}


    private Connection getConnection(String connectionString,String user,String password) throws Exception
    {
    	Class.forName(this.MYSQLDRIVERNAME);
   	    Connection connection = DriverManager.getConnection(connectionString,user,password);
   	    return connection;
    }
    
    private  List<String> getColumnNames(ResultSet resultset,ResultSetMetaData resultMetaData) throws Exception
    {
   	 List<String> result=new ArrayList<String>(); 
   	 for (int i=1; i<=resultMetaData.getColumnCount(); i++)
   	 {   
   		 result.add(resultMetaData.getColumnName(i));
   	 }
   	 return result;
    }
    
    private static List<List<String>> getData(ResultSet resultset,ResultSetMetaData resultMetaData) throws Exception
    {
   	 List<List<String>> result=new ArrayList<List<String>>();
	    while (resultset.next())
	    {   
	    	List<String> tempResult=new ArrayList<String>();
	        for (int i=1; i<=resultMetaData.getColumnCount(); i++)
	        {   
	        	tempResult.add(resultset.getString(i));
	        }
	        result.add(tempResult);
	    }
	   return result;
    }   

	


}
