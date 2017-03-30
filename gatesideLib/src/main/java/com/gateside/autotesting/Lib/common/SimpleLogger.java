package com.gateside.autotesting.Lib.common;

//this is a simple logger based on java.util.logging

import java.io.IOException;
import java.util.logging.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.PropertyConfigurator;

import bsh.This;




/**   
*    
* @author zhangtiande 
* @version 2.0.5
*    
*/
public class SimpleLogger
{
	
	
	@Deprecated
    public static void logInfo(String message)
    {
    	PropertyConfigurator.configure("logConfig.properties");
    	Log log=LogFactory.getLog(SimpleLogger.class);
		try 
		{
		    log.info(message);
		} 
		catch(Exception e) 
		{

		}	
    }
	
    public static void logInfo(Class classTypeInstance,String message)
    {
    	PropertyConfigurator.configure("logConfig.properties");
    	Log log=LogFactory.getLog(classTypeInstance);
		try 
		{
		    log.info(message);
		} 
		catch(Exception e) 
		{

		}	
    }
    
   
    @Deprecated
    public static void  logError(String message)
    {
    	PropertyConfigurator.configure("logConfig.properties");
    	Log log=LogFactory.getLog(SimpleLogger.class);
		try 
		{
		    log.error(message);
		} 
		catch(Exception e) 
		{

		}	
    	
    }
   
   public static void  logError(Class classTypeInstance,String message)
   {
   	PropertyConfigurator.configure("logConfig.properties");
   	Log log=LogFactory.getLog(classTypeInstance);
		try 
		{
		    log.error(message);
		} 
		catch(Exception e) 
		{

		}	
   	
   }
 
   public static void  logError(Class classTypeInstance,Exception exception)
   {
   	PropertyConfigurator.configure("logConfig.properties");
   	Log log=LogFactory.getLog(classTypeInstance);
		try 
		{
		    log.error(getMessage(exception));
		} 
		catch(Exception e) 
		{

		}	
   	
   }
    
   
   @Deprecated 
   public static void  logError(Exception exception)
    {
    	logError(getMessage(exception));
    }
    
     
    
    private static String getMessage(Exception exception)
    {
        String result="";
        if(exception.getMessage()!="")
        {
        	result=result+"MESSSAGE: "+exception.getMessage();
        }
        
        if(exception.getCause()!=null)
        {
        	result=result+"CAUSE: "+exception.getCause().getMessage();
        }
        
        if(exception.getStackTrace().length>0)
        {
           result=result+"STACKTRACE: ";
           for(StackTraceElement item : exception.getStackTrace() )
           {
             result=result+item.toString();
           }
        }
        return result;
    }
}
