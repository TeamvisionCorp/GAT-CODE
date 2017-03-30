package com.gateside.autotesting.Lib.httpunitService;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import com.gateside.autotesting.Lib.common.SimpleLogger;
import com.meterware.httpunit.*;

public class HttpUnitHelper 
{

	private static WebConversation currentConversation=null;
	
	private HttpUnitHelper(){}
	
	/**
	 *  Create WebConversation 
	 * @return WebConversation
	 */
	public static WebConversation createConversation()
	{
		HttpUnitOptions.setDefaultCharacterSet("UTF-8");
		HttpUnitOptions.setExceptionsThrownOnScriptError( false );
		HttpUnitOptions.setExceptionsThrownOnErrorStatus(false);
		if(currentConversation!=null)
		{
			return currentConversation;
		}
		else
		{
		   currentConversation=new WebConversation();
		   return currentConversation;
		}
	}
    
	/**
	 * Create webrequest
	 * @param url http url
	 * @param httpMethod :POST,GET,DELETE,PUT
	 * @return WebRequest
	 */
	public static WebRequest createWebRequest(String url,String httpMethod)
	{
         WebRequest resultRequest=null;
         Integer idCode=1;
         if(httpMethod.equals("GET"))
         {
        	 idCode=1;
         }
         else if(httpMethod.equals("POST")) {
			idCode=2;
		}
         switch(idCode) 
         {
		    case 1:
		    	resultRequest=new GetMethodWebRequest(url);
			    break;
		    case 2:
		    	resultRequest=new PostMethodWebRequest(url);
			    break;
         }
         return resultRequest;
	}
	
	/**
	 * set request parameters
	 * @param request WebRequest
	 * @param parameters  request parameters
	 */
	public static void setParameters(WebRequest request,HashMap<String,String> parameters)
	{
		Iterator<Entry<String, String>> it =  parameters.entrySet().iterator();
        while(it.hasNext())
        {          
           Entry  parameter = (Entry) it.next();
           request.setParameter(parameter.getKey().toString(),parameter.getValue().toString());
        }
	}
	
	/**
	 * stop output js error in webpage
	 * @param invoker  current class
	 */
	public static void outputJsError(Class invoker)
	{
		for(String str:HttpUnitOptions.getScriptErrorMessages())
		{
			SimpleLogger.logError(invoker,str);
		}
	}
	
	/**
	 * reset conversion as Null
	 */
	public static void cleanConversation()
	{
		currentConversation=null;
	}
}

