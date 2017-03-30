package com.gateside.autotesting.Gat.executor;

import java.util.List;

import com.gateside.autotesting.Gat.executor.StepsExecutor;
import com.gateside.autotesting.Gat.dataobject.EnumObjectManager;
import com.gateside.autotesting.Gat.dataobject.InvokedMethodInfo;
import com.gateside.autotesting.Gat.dataobject.testcase.InterfaceStepsCase;
import com.gateside.autotesting.Gat.dataobject.testcase.InterfaceTestStep;
import com.gateside.autotesting.Gat.dataobject.testcase.TestStep;
import com.gateside.autotesting.Gat.util.GlobalConfig;
import com.gateside.autotesting.Gat.util.StepValuePool;
import com.gateside.autotesting.Lib.common.SimpleLogger;
import com.gateside.autotesting.Lib.httpunitService.HttpUnitHelper;


public class InterfaceStepsExecutor extends StepsExecutor
{

	public InterfaceStepsExecutor(String caseFilePath,String caseID)
	{
		super(caseID, caseFilePath);
	}
	
	@Override
	public void setUp() throws Exception
    {
		 preCleanup();
		 setGlobalConfig();
		 exectuePreStep();
	}
	
	@Override
	public void tearDown() throws Exception 
	{
		try 
		{
//			GlobalConfig.setStepsParameterFilePath(GlobalConfig.getAutoProjectName()+"DataFiles"+GlobalConfig.getSlash()+"Xmls"+GlobalConfig.getSlash()+"TearDownParameters.xml"); //set glocal config for pre step parameters
			for(InterfaceTestStep step : ((InterfaceStepsCase)targetCase).Steps)
			{
				if(step.TearDown)
				{
				  GlobalConfig.setStepsParameterFilePath(GlobalConfig.getAutoProjectName()+"DataFiles"+GlobalConfig.getSlash()+"Xmls"+GlobalConfig.getSlash()+step.StepParametersFilePath); //set glocal config for pre step parameters
				  SimpleLogger.logInfo(this.getClass(),"executeCase: set teardown  parameter path as "+GlobalConfig.getStepsParameterFilePath());
				  InvokedMethodInfo resultInfo=getStepMethodInfo(step);
				  SimpleLogger.logInfo(this.getClass(),"executeCase: execute tear down step:"+resultInfo.classFullName+resultInfo.methodName+step.StepParameterID);
				  invokeMethod(resultInfo);
				}
			}
		} catch (Exception e) 
		{
		  SimpleLogger.logError(this.getClass(),e);
		}
		finally
		{
			HttpUnitHelper.cleanConversation();
			StepValuePool.cleanValuePool();
		}
		
	}
	
	protected void exectuePreStep() throws Exception
	{
		SimpleLogger.logInfo(this.getClass(),"execute Setup Step:");
		targetCase=getTestCase();
//		GlobalConfig.setStepsParameterFilePath(GlobalConfig.getAutoProjectName()+"DataFiles"+GlobalConfig.getSlash()+"Xmls"+GlobalConfig.getSlash()+"SetupParameters.xml"); //set glocal config for pre step parameters
		for(InterfaceTestStep step : ((InterfaceStepsCase)targetCase).Steps)
		{
			if(step.SetUp)
			{
			  GlobalConfig.setStepsParameterFilePath(GlobalConfig.getAutoProjectName()+"DataFiles"+GlobalConfig.getSlash()+"Xmls"+GlobalConfig.getSlash()+step.StepParametersFilePath); //set glocal config for pre step parameters
			  SimpleLogger.logInfo(this.getClass(),"executeCase: set setup parameter path as "+GlobalConfig.getStepsParameterFilePath());
			  InvokedMethodInfo resultInfo=getStepMethodInfo(step);
			  SimpleLogger.logInfo(this.getClass(),"executeCase: execute setup step:"+resultInfo.classFullName+resultInfo.methodName+step.StepParameterID);
			  invokeMethod(resultInfo);
			}
		}
		
	}
	
	
	@Override
	public void executeCase() throws Exception 
	{
		this.setGlobalConfig();
		for(InterfaceTestStep step : ((InterfaceStepsCase)targetCase).Steps)
		{
			if(!step.SetUp && !step.TearDown)
			{
				GlobalConfig.setStepsParameterFilePath(GlobalConfig.getAutoProjectName()+"DataFiles"+GlobalConfig.getSlash()+"Xmls"+GlobalConfig.getSlash()+step.StepParametersFilePath); //set glocal config for pre step parameters
				SimpleLogger.logInfo(this.getClass(),"executeCase: set step parameter path as "+GlobalConfig.getStepsParameterFilePath());
				InvokedMethodInfo resultInfo=getStepMethodInfo(step);
				SimpleLogger.logInfo(this.getClass(),"executeCase: execute step:"+resultInfo.classFullName+resultInfo.methodName+step.StepParameterID);
				for(Integer i=0;i<step.For;i++)
				{
					SimpleLogger.logInfo(this.getClass(),"execute step method "+String.valueOf(i)+" times");
					if(step.StepModule)
					{
						InvokeStepModule(step);
					}
					else 
					{
						invokeMethod(resultInfo);	
					}
				}
				
			}
		}
		
	}
	
	@Override
	public void InvokeStepModule(TestStep stepModule) throws Exception 
	{
		if(stepModule.StepModuleFilePath!="")
		{
		  this.setStepModulePath(stepModule.StepModuleFilePath);
		}
		this.setCaseID(stepModule.StepModuleID);
		InterfaceStepsCase stepModuleCase=this.getTestCase();
		stepModuleCase.Steps=changeModuleStepParameter(stepModuleCase.Steps,stepModule.ModuleStepParameters);
		for(InterfaceTestStep step : stepModuleCase.Steps)
		{
			if(!step.SetUp && !step.TearDown)
			{
				GlobalConfig.setStepsParameterFilePath(GlobalConfig.getAutoProjectName()+"DataFiles"+GlobalConfig.getSlash()+"Xmls"+GlobalConfig.getSlash()+step.StepParametersFilePath); //set glocal config for pre step parameters
				SimpleLogger.logInfo(this.getClass(),"executeStep Module: set step parameter path as "+GlobalConfig.getStepsParameterFilePath());
				InvokedMethodInfo resultInfo=getStepMethodInfo(step);
				SimpleLogger.logInfo(this.getClass(),"executeStep Module: execute step:"+resultInfo.classFullName+resultInfo.methodName+step.StepParameterID);
				for(Integer i=0;i<step.For;i++)
				{
					SimpleLogger.logInfo(this.getClass(),"execute step method "+String.valueOf(i)+" times");
					invokeMethod(resultInfo);	
				}
				
			}
		}
	}

	
	protected List<InterfaceTestStep> changeModuleStepParameter(List<InterfaceTestStep> steps,String newParameters)
	{
		if(newParameters=="")
		{
			return steps;
		}
		if(!newParameters.contains(","))
		{
			for(TestStep step:steps)
			{
				step.StepParameterID=newParameters;
			}
			return steps;
		}
		else 
		{
			String[] parameterArray=newParameters.split(",");
			for(Integer i=0;i<parameterArray.length;i++)
			{
				if(parameterArray[i].trim()!="")
				{
				  steps.get(i).StepParameterID=parameterArray[i].trim();	
				}
			}
			return steps;
		}
	   
	}
    
	@Override
    protected InterfaceStepsCase getTestCase() throws Exception
	{
		return (InterfaceStepsCase)getTestObject(this.getCaseID(),EnumObjectManager.IStepsCaseManager);
	}
}
