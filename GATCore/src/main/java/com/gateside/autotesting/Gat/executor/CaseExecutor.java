package com.gateside.autotesting.Gat.executor;

import com.gateside.autotesting.Gat.dataobject.EnumObjectManager;
import com.gateside.autotesting.Gat.dataobject.InvokedMethodInfo;
import com.gateside.autotesting.Gat.dataobject.TestObject;
import com.gateside.autotesting.Gat.manager.IManager;
import com.gateside.autotesting.Gat.manager.TestObjectManagerFactory;
import com.gateside.autotesting.Gat.util.GlobalConfig;
import com.gateside.autotesting.Gat.util.ParameterChecker;
import com.gateside.autotesting.Gat.util.StepValuePool;
import com.gateside.autotesting.Lib.common.ClassReflector;
import com.gateside.autotesting.Lib.common.SimpleLogger;

import java.lang.reflect.Method;

public abstract class CaseExecutor 
{

	private String caseID="";
	
	public final void execute() throws Exception
	{
		SimpleLogger.logInfo(this.getClass(),"*************************************************************");
	    SimpleLogger.logInfo(this.getClass(),"Start executing case :"+this.getCaseID());
		try 
		{
			setUp();
			executeCase();
		}
		finally
		{
			tearDown();
		}
	}
	
	protected abstract void setUp() throws Exception;
	
	protected abstract void executeCase() throws Exception;
	
	protected abstract void tearDown() throws Exception;
	
	
	protected TestObject getTestObject(String objectID,EnumObjectManager testObjectType) throws Exception
	{
		SimpleLogger.logInfo(this.getClass(),objectID+testObjectType.toString());
		ParameterChecker.StringParameterCheck(testObjectType.name()+"objectID",objectID);
		IManager testObjectManager=TestObjectManagerFactory.getTestObjectManager(testObjectType);
		return testObjectManager.getItem(objectID);
	}
	
	protected void preCleanup()
	{
		SimpleLogger.logInfo(this.getClass(),"preCleanup: run precleanup method");
		StepValuePool.createInstance().getValueDic().remove(GlobalConfig.getPreStepResult());
	}
	
	protected  Object invokeMethod(InvokedMethodInfo methodInfo) throws Exception
	{
		Object targetClassInstanceObject= ClassReflector.createInstance(methodInfo.jarFilePath,methodInfo.classFullName); //create target instance
		Method targetMethod= ClassReflector.getMethod(targetClassInstanceObject,methodInfo.methodName,methodInfo.parameters.toArray());
		SimpleLogger.logInfo(this.getClass(),targetMethod.getName());
		return targetMethod.invoke(targetClassInstanceObject,methodInfo.parameters.toArray());
	}
	
	protected void executeSql(String sqlContextID) throws Exception
	{
       //InterfaceStepParameter parameters=(InterfaceStepParameter)getTestObject(sqlContextID, EnumObjectManager.IStepParameterManager);
       //DBOperationService.executeNoneQuery(parameters.ConnectiongString,parameters.CommandText,DBProvider.Mysql);
		throw new Exception("not impliement!");
	}
	
	protected void invokeDBStep(String dbStepID) throws Exception
	{
		   throw new Exception("not impliement!");
	}
	
	protected InvokedMethodInfo parserPreStepMethodInfo(String methodInfo, Object[] extParameters) throws Exception 
	{
		ParameterChecker.StringParameterCheck("invokeMethod",methodInfo);
    	InvokedMethodInfo resultInfo=new InvokedMethodInfo();
    	if(methodInfo.contains(":"))//has parameter for preStepMethod
		{
			String preStepMethodInfo=methodInfo.split(":")[0];
			resultInfo.parameters.add(methodInfo.split(":")[1]);
		    resultInfo.methodName=preStepMethodInfo.substring(preStepMethodInfo.lastIndexOf(".")+1);
		    resultInfo.classFullName=preStepMethodInfo.replace("."+resultInfo.methodName,"");
		}
		else
		{
			resultInfo.parameters.add("");
			resultInfo.methodName=methodInfo.substring(methodInfo.lastIndexOf(".")+1);
			resultInfo.classFullName=methodInfo.replace("."+resultInfo.methodName,""); 		
		}
    	resultInfo.jarFilePath=GlobalConfig.getStepMethodJarPath();
    	if(extParameters!=null)
    	{
    		for(Object obj : extParameters)
        	{
        		resultInfo.parameters.add(obj);
        	}
    	}
    	return resultInfo;
    }
    
	protected String getCaseID()
	{
		return caseID;
	}
	
	protected void setCaseID(String caseID)
	{
	   this.caseID=caseID;	
	}
}