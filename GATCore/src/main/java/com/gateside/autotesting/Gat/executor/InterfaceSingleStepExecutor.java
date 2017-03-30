package com.gateside.autotesting.Gat.executor;

import java.awt.print.Printable;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import com.gateside.autotesting.Gat.executor.CaseExecutor;
import com.gateside.autotesting.Gat.dataobject.EnumObjectManager;
import com.gateside.autotesting.Gat.dataobject.InvokedMethodInfo;
import com.gateside.autotesting.Gat.dataobject.testcase.EnumAssertType;
import com.gateside.autotesting.Gat.dataobject.testcase.EnumSetupType;
import com.gateside.autotesting.Gat.dataobject.testcase.InterfaceSingleStepCase;
import com.gateside.autotesting.Gat.dataobject.testcase.TestStep;
import com.gateside.autotesting.Gat.util.GlobalConfig;
import com.gateside.autotesting.Gat.util.ParameterChecker;
import com.gateside.autotesting.Gat.util.StepValuePool;
import com.gateside.autotesting.Lib.common.SimpleLogger;
import com.gateside.autotesting.Lib.httpunitService.HttpUnitHelper;
import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebRequest;
import com.meterware.httpunit.WebResponse;
import com.sun.org.apache.xerces.internal.util.URI;

import org.junit.Assert;

public class InterfaceSingleStepExecutor extends CaseExecutor
{
	private String caseFilePath="";	
	private String assertParametersFilePath="";
    private InterfaceSingleStepCase targetCase=null;
	
	public InterfaceSingleStepExecutor(String caseID) throws Exception 
	{
		
		String[] caseIDs=splitCaseID(caseID);
		this.caseFilePath=GlobalConfig.getAutoProjectName()+"DataFiles"+GlobalConfig.getSlash()+"Excels"+GlobalConfig.getSlash()+caseIDs[0];
		this.setCaseID(caseIDs[1]+"."+caseIDs[2]);
		this.assertParametersFilePath=GlobalConfig.getAutoProjectName()+"DataFiles"+GlobalConfig.getSlash()+"Excels"+GlobalConfig.getSlash()+caseIDs[0]+".xml";
	}

	@Override
	public void setUp() throws Exception 
	{
	   preCleanup();
	   setGlobalConfig();
	   exectuePreStep();
	}

	@Override
	public void executeCase() throws Exception
	{
		SimpleLogger.logInfo(this.getClass(),"Start get response and assert result");
		WebResponse currentResponse=getResponse(getUrl(targetCase),targetCase.getUrlParameters(),targetCase.getHttpMethod());
//		assertResult(targetCase.getExpectResult(), StringDecompressor.decompress(currentResponse.getInputStream()),targetCase.getAssertType());
		assertResult(targetCase.getExpectResult(), currentResponse.getText(),targetCase.getAssertType());
	}
	
	
	@Override
	public void tearDown() throws Exception {
		try 
		{
			SimpleLogger.logInfo(this.getClass(),"set teaddown parameters file path");
			GlobalConfig.setStepsParameterFilePath(GlobalConfig.getAutoProjectName()+"DataFiles"+GlobalConfig.getSlash()+"Excels"+GlobalConfig.getSlash()+"TearDownParameters.xml"); //set glocal config for pre step parameters
			if(!targetCase.getTearDownType().equals("$NULL"))
			{
				Integer enumValue=Enum.valueOf(EnumSetupType.class,targetCase.getTearDownType()).ordinal();
				switch (enumValue)
				{
				case 0:
					executeSql(targetCase.getTearDown());
					break;
				case 1:
					invokeDBStep(targetCase.getTearDown());
				case 2:
					InvokedMethodInfo methodInfo= parserPreStepMethodInfo(targetCase.getTearDown(),null);
					invokeMethod(methodInfo);
				};
			}
		} 
		catch (Exception e) 
		{
			SimpleLogger.logError(this.getClass(),e);
		}
		finally
		{
            HttpUnitHelper.cleanConversation();
            StepValuePool.cleanValuePool();
		}
		
	}
	
	private String[] splitCaseID(String caseID) throws Exception
	{
		ParameterChecker.StringParameterCheck("caseID",caseID);
		return caseID.split("\\.");
	}
    
	private void setGlobalConfig()
	{
		GlobalConfig.setTestCaseFilePath(this.caseFilePath+".xlsx"); //set global config for testcase file path
		SimpleLogger.logInfo(this.getClass(),"set testcase file path as "+GlobalConfig.getTestCaseFilePath());
	}
	
	private void exectuePreStep() throws Exception
	{
		GlobalConfig.setStepsParameterFilePath(GlobalConfig.getAutoProjectName()+"DataFiles"+GlobalConfig.getSlash()+"Excels"+GlobalConfig.getSlash()+"SetupParameters.xml"); //set glocal config for pre step parameters
		SimpleLogger.logInfo(this.getClass(),"set setupparameters file as "+GlobalConfig.getStepsParameterFilePath());
		targetCase=(InterfaceSingleStepCase)getTestObject(this.getCaseID(),EnumObjectManager.ISingleStepCaseManager);
		if(!targetCase.getSetupType().equals("$NULL"))
		{
			Integer enumValue=Enum.valueOf(EnumSetupType.class,targetCase.getSetupType()).ordinal();
			switch (enumValue)
			{
			case 0:
				executeSql(targetCase.getSetup());
				break;
			case 1:
				invokeDBStep(targetCase.getSetup());
			case 2:
				SimpleLogger.logInfo(this.getClass(),"Execute setup method: "+targetCase.getSetup());
				InvokedMethodInfo methodInfo= parserPreStepMethodInfo(targetCase.getSetup(),null);
				Object returnValue=invokeMethod(methodInfo);
				StepValuePool.createInstance().getValueDic().put(GlobalConfig.getPreStepResult(),returnValue);
			}
		}
	}

    private String getUrl(InterfaceSingleStepCase testCase)
    {
    	String targetUrl="";
         if(testCase.getDomainName().trim()!="")
         {
        	 targetUrl=testCase.getDomainName()+"/";
         }
         else 
         {
			targetUrl=GlobalConfig.getDomainName()+"/";
		 }
         targetUrl=targetUrl+testCase.getPath();
         return targetUrl;
    }
    
    private WebResponse getResponse(String url,LinkedHashMap<String,String> parameters,String httpMethod) throws Exception
    {
    	SimpleLogger.logInfo(this.getClass(),"get response for url:"+url+getParametesString(parameters));
    	WebConversation currentConversation=HttpUnitHelper.createConversation();
    	WebRequest currentRequest=HttpUnitHelper.createWebRequest(url,httpMethod);
         if(parameters!=null)
         {
        	 AddDynamicParameters(parameters);
        	 HttpUnitHelper.setParameters(currentRequest, parameters);
         }
        SimpleLogger.logInfo(this.getClass(),"get response for url:"+currentRequest.getURL());
        WebResponse response=currentConversation.getResponse(currentRequest);
        return response;
    }
    
    private void AddDynamicParameters(HashMap<String, String> parameters) throws Exception 
    {
    	SimpleLogger.logInfo(this.getClass(),"start add dycamicParameters");
		for(Object key : parameters.keySet().toArray())
		{
			String value=parameters.get(key);
			if(value.startsWith("$DP"))
			{
	    	     Object[] exteranlparameters={parameters};
	    		 InvokedMethodInfo methodInfo= parserPreStepMethodInfo(value.substring(3),exteranlparameters);
	    		 String dynamicValue= (String)invokeMethod(methodInfo);
	    		 parameters.put((String) key, dynamicValue);
			}
		}
	}

	private void assertResult(String expectResult,String actualResult,String assertType ) throws Exception
    {
    	SimpleLogger.logInfo(this.getClass(),"assert result, expectResult:"+expectResult+" actualResult:"+actualResult+" AssertType:"+assertType);
    	GlobalConfig.setStepsParameterFilePath(this.assertParametersFilePath);
    	SimpleLogger.logInfo(this.getClass(),"set assert parameters  file path:"+GlobalConfig.getStepsParameterFilePath());
    	if(assertType.equals(EnumAssertType.Equal.toString()))
    	   {
    		  Assert.assertArrayEquals("Are equal exception ", expectResult.toCharArray(), actualResult.toCharArray());
    	   }
    	if(assertType.equals(EnumAssertType.Contains.toString()))
    	   {
    		  Assert.assertTrue("Assert true exception", actualResult.contains(expectResult));
    	   }
    	if(assertType.equals(EnumAssertType.Custom.toString()))
    	   {
    		  SimpleLogger.logInfo(this.getClass(),targetCase.getAssertMethod());
    	      Object[] parameters={expectResult,actualResult};
    		  InvokedMethodInfo methodInfo= parserPreStepMethodInfo(targetCase.getAssertMethod(),parameters);
    		  invokeMethod(methodInfo);
    	   }
    }
    
    private String getParametesString(HashMap<String,String> parameters) throws UnsupportedEncodingException
    {
    	Iterator<Entry<String, String>> it =  parameters.entrySet().iterator();
    	String result="";
        while(it.hasNext())
        {          
           Entry  parameter = (Entry) it.next();
           String parameterValue=parameter.getValue().toString();
           if(parameterValue.equals("$NULL"))
           {
        	   parameterValue="";
           }
           result=result+parameter.getKey().toString()+"="+parameterValue+"&";
        }
        return result;
    }
    
    private void  EncodeParameters(LinkedHashMap<String,String> parameters) throws UnsupportedEncodingException
    {
    	Iterator<Entry<String, String>> it =  parameters.entrySet().iterator();
        while(it.hasNext())
        {          
           Entry  parameter = (Entry) it.next();
           String parameterValue=parameter.getValue().toString();
           if(parameterValue!="")
           {
        	   String tempValue="";
        	   tempValue=URLEncoder.encode(parameterValue,"UTF8");
        	   parameter.setValue(tempValue);
           }
        }
    }
    
    
}

