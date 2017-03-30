package com.gateside.autotesting.Gat.executor;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;

import com.gateside.autotesting.Gat.executor.StepsExecutor;
import com.gateside.autotesting.Gat.dataobject.EnumObjectManager;
import com.gateside.autotesting.Gat.dataobject.InvokedMethodInfo;
import com.gateside.autotesting.Gat.dataobject.testcase.InterfaceStepsCase;
import com.gateside.autotesting.Gat.dataobject.testcase.InterfaceTestStep;
import com.gateside.autotesting.Gat.dataobject.testcase.TestStep;
import com.gateside.autotesting.Gat.dataobject.testcase.WebUIStepsCase;
import com.gateside.autotesting.Gat.dataobject.testcase.WebUITestStep;
import com.gateside.autotesting.Gat.uia.webautomation.BrowserType;
import com.gateside.autotesting.Gat.uia.webautomation.WebBrowser;
import com.gateside.autotesting.Gat.uia.webautomation.WebPage;
import com.gateside.autotesting.Gat.util.FileHelper;
import com.gateside.autotesting.Gat.util.GlobalConfig;
import com.gateside.autotesting.Gat.util.ScreenCapture;
import com.gateside.autotesting.Gat.util.StepValuePool;
import com.gateside.autotesting.Lib.common.ClassReflector;
import com.gateside.autotesting.Lib.common.SimpleLogger;
import com.gateside.autotesting.Lib.httpunitService.HttpUnitHelper;

public class WebUIStepsExecutor extends StepsExecutor
{

	private WebBrowser browser=null;
	private WebPage webPage=null;
	
	public WebUIStepsExecutor(String caseFilePath,String caseID)
	{
		super(caseID, caseFilePath);
	    browser=new WebBrowser(Enum.valueOf(BrowserType.class,GlobalConfig.getBrowserType()));
	    webPage=new WebPage(browser);
	}
	
	@Override
	public void setUp() throws Exception
    {
		throw new Exception("function not impliment");
	}
	
	

	
    
	@Override
	public void executeCase() throws Exception 
	{
		this.setGlobalConfig();
		String  screenPictureName="";
		try 
		{
			for(WebUITestStep step : ((WebUIStepsCase)targetCase).Steps)
			{
				screenPictureName=step.StepGroup+"_"+step.StepName+"_"+step.StepParameterID;
				SimpleLogger.logInfo(this.getClass(),"executeCase: set step parameter path as "+step.StepParametersFilePath);
				GlobalConfig.setStepsParameterFilePath(GlobalConfig.getAutoProjectName()+"DataFiles"+GlobalConfig.getSlash()+"Xmls"+GlobalConfig.getSlash()+step.StepParametersFilePath); //set glocal config for pre step parameters
				GlobalConfig.setuIElementsFilePath(GlobalConfig.getAutoProjectName()+"DataFiles"+GlobalConfig.getSlash()+"Xmls"+GlobalConfig.getSlash()+step.UIElementsFilePath); //set glocal config for pre step parameters
				InvokedMethodInfo resultInfo=this.getStepMethodInfo(step);
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
				saveScreenPicture(screenPictureName,false);
			}
			
		} catch (Exception e) 
		{
			saveScreenPicture(screenPictureName,true);
		}		
	}
	
	
	protected List<WebUITestStep> changeModuleStepParameter(List<WebUITestStep> steps,String newParameters)
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
	public void InvokeStepModule(TestStep stepModule) throws Exception 
	{
		if(stepModule.StepModuleFilePath!="")
		{
		  this.setStepModulePath(stepModule.StepModuleFilePath);
		}
		this.setCaseID(stepModule.StepModuleID);
		WebUIStepsCase stepModuleCase=this.getTestCase();
		stepModuleCase.Steps=changeModuleStepParameter(stepModuleCase.Steps,stepModule.ModuleStepParameters);
		for(WebUITestStep step : stepModuleCase.Steps)
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
	
	@Override
    protected WebUIStepsCase getTestCase() throws Exception
	{
		return (WebUIStepsCase)getTestObject(this.getCaseID(),EnumObjectManager.WebUIStepCaseManager);
	}
	
	
	@Override
	protected InvokedMethodInfo getStepMethodInfo(TestStep step)
	    {
	    	InvokedMethodInfo resultInfo=new InvokedMethodInfo();
	    	resultInfo.classFullName=step.StepAssembly+step.StepGroup;
	    	resultInfo.methodName=step.StepName;
	    	resultInfo.parameters.add(browser);
	    	resultInfo.parameters.add(webPage);
	    	resultInfo.parameters.add(step.StepParameterID);
	    	resultInfo.jarFilePath=GlobalConfig.getStepMethodJarPath();
	    	return resultInfo;
	    }
	
	@Override
	protected  Object invokeMethod(InvokedMethodInfo methodInfo) throws Exception
	{
		Object targetClassInstanceObject= ClassReflector.createInstance(methodInfo.jarFilePath,methodInfo.classFullName); //create target instance
		Method targetMethod= ClassReflector.getMethod(targetClassInstanceObject,methodInfo.methodName,methodInfo.parameters.toArray());
		return targetMethod.invoke(targetClassInstanceObject,methodInfo.parameters.toArray());
	}
	
	@Override
	public void tearDown()
	{
		try 
		{
			SimpleLogger.logInfo(this.getClass(),"tear down now ");
			browser.quit();	
		} catch (Exception e)
		{
		    SimpleLogger.logError(this.getClass(),e);
		}
		finally
		{
			
		}
	}
    
    private void saveScreenPicture(String fileName,Boolean fail)
    {
    	Date nowDate=new Date();
    	fileName=fileName+"_"+nowDate.getTime();
    	if(!fail)
    	{
    	  FileHelper.createDir(GlobalConfig.screenPicuterPath()+"Pass");
   	      ScreenCapture.saveScreen(GlobalConfig.screenPicuterPath()+"Pass",fileName+".jpg");
    	}
    	else
    	{
    		FileHelper.createDir(GlobalConfig.screenPicuterPath()+"Fail");
      	    ScreenCapture.saveScreen(GlobalConfig.screenPicuterPath()+"Fail",fileName+".jpg");
		}
    }
}


