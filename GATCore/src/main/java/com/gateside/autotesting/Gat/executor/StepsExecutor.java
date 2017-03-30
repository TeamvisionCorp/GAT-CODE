package com.gateside.autotesting.Gat.executor;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.firefox.internal.NewProfileExtensionConnection;

import com.gateside.autotesting.Gat.executor.CaseExecutor;
import com.gateside.autotesting.Gat.dataobject.InvokedMethodInfo;
import com.gateside.autotesting.Gat.dataobject.testcase.StepsCase;
import com.gateside.autotesting.Gat.dataobject.testcase.TestStep;
import com.gateside.autotesting.Gat.util.GlobalConfig;
import com.gateside.autotesting.Gat.util.StepValuePool;
import com.gateside.autotesting.Lib.common.SimpleLogger;
import com.gateside.autotesting.Lib.httpunitService.HttpUnitHelper;


public abstract class StepsExecutor extends CaseExecutor
{
	protected String caseFilePath="";	
    protected StepsCase targetCase=null;

	public StepsExecutor(String caseID,String caseFilePath)
	{
		this.caseFilePath=GlobalConfig.getAutoProjectName()+"DataFiles"+GlobalConfig.getSlash()+"Xmls"+GlobalConfig.getSlash()+caseFilePath;
		this.setCaseID(caseID);
	}
	
	protected abstract void InvokeStepModule(TestStep stepModule) throws Exception;
	

	protected  void setGlobalConfig()
	{
		SimpleLogger.logInfo(this.getClass(),"setGlobalConfig: set testcase file path as "+this.caseFilePath);
		GlobalConfig.setTestCaseFilePath(this.caseFilePath); //set global config for testcase file path	
	}
	
	protected StepsCase getTestCase() throws Exception
	{
		throw new Exception("The function doesn't implient.");
	}
	

    protected InvokedMethodInfo getStepMethodInfo(TestStep step)
    {
    	InvokedMethodInfo resultInfo=new InvokedMethodInfo();
    	resultInfo.classFullName=step.StepAssembly+step.StepGroup;
    	resultInfo.methodName=step.StepName;
    	resultInfo.parameters.add(step.StepParameterID);
    	resultInfo.jarFilePath=GlobalConfig.getStepMethodJarPath();
    	return resultInfo;
    }
    
   
    
    protected void setStepModulePath(String moduleFileName)
    {
    	String moduleFilePath=GlobalConfig.getAutoProjectName()+"DataFiles"+GlobalConfig.getSlash()+"Xmls"+GlobalConfig.getSlash()+moduleFileName;
    	SimpleLogger.logInfo(this.getClass(),"setGlobalConfig: set testmodule file path as "+moduleFilePath);
		GlobalConfig.setTestCaseFilePath(moduleFilePath); //set global config for testcase file path
    }

}
