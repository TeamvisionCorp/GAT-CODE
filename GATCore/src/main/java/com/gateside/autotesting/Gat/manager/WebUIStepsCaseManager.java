package com.gateside.autotesting.Gat.manager;

import com.gateside.autotesting.Gat.manager.StepsCaseManager;
import com.gateside.autotesting.Gat.dataobject.testcase.StepsCase;
import com.gateside.autotesting.Gat.dataobject.testcase.TestStep;
import com.gateside.autotesting.Gat.dataobject.testcase.WebUIStepsCase;
import com.gateside.autotesting.Gat.dataobject.testcase.WebUITestStep;
import com.gateside.autotesting.Gat.util.GlobalConfig;
import com.gateside.autotesting.Lib.xmlService.XMLParser;
import com.gateside.autotesting.Lib.xmlService.XMLSerializer;



public class WebUIStepsCaseManager extends StepsCaseManager
{
	
	@Override
	public WebUIStepsCase geTestCase(String caseID) throws Exception
	{
		WebUIStepsCase caseResult=new WebUIStepsCase();
		String filePath=GlobalConfig.getTestCaseFilePath();
		String testCaseXPth="AllTestCases/TestCase";
		String caseXMLString=this.getTestObjectXML(caseID, filePath, testCaseXPth,"ID");
		caseResult=(WebUIStepsCase)XMLSerializer.XMLToObject(caseResult,caseXMLString);
		caseResult=formatTestCase(caseResult, filePath);
		return caseResult;
	}
	
	private WebUIStepsCase formatTestCase(WebUIStepsCase testCase,String filePath)
	{
	    resetAssembly(testCase,filePath);
	    resetGroup(testCase,filePath);
	    resetParametersFilePath(testCase,filePath);
	    resetUIElementsFilePath(testCase, filePath);
		return testCase;
	}
	
	private void resetAssembly(WebUIStepsCase testCase,String filePath)
	{
		String assemblyXpath="AllTestCases/StepAssembly";
		for(WebUITestStep step: testCase.Steps)
		{
			if(step.StepAssembly==null || step.StepAssembly=="")
			{
				step.StepAssembly=XMLParser.getElementsByXPath(filePath, assemblyXpath).get(0).getTextTrim();
			}
		}
	}
	
	private void resetGroup(WebUIStepsCase testCase,String filePath)
	{
		String groupXpath="AllTestCases/StepGroup";
		for(WebUITestStep step: testCase.Steps)
		{			
			if(step.StepGroup==null || step.StepGroup=="")
			{
				step.StepGroup=XMLParser.getElementsByXPath(filePath, groupXpath).get(0).getTextTrim();
			}
		}
	}
	
	private void resetParametersFilePath(WebUIStepsCase testCase,String filePath)
	{
		String parameterFilePath="AllTestCases/StepParametersFilePath";
		for(WebUITestStep step: testCase.Steps)
		{
			if(step.StepParametersFilePath==null || step.StepParametersFilePath=="")
			{
				if(testCase.StepParametersFilePath!=null)
				{
				   step.StepParametersFilePath=testCase.StepParametersFilePath;
				}
				else 
				{
					step.StepParametersFilePath=XMLParser.getElementsByXPath(filePath, parameterFilePath).get(0).getTextTrim();
				}
			}
		}
	}
	
	private void resetUIElementsFilePath(WebUIStepsCase testCase,String filePath)
	{
		String  elementsFilePath="AllTestCases/UIElementsFilePath";
		for(WebUITestStep step: testCase.Steps)
		{
			if(step.UIElementsFilePath==null || step.UIElementsFilePath=="")
			{
				if(testCase.UIElementsFilePath!=null)
				{
				   step.UIElementsFilePath=testCase.UIElementsFilePath;
				}
				else 
				{
					step.UIElementsFilePath=XMLParser.getElementsByXPath(filePath, elementsFilePath).get(0).getTextTrim();
				}
			}
		}
	}


}
