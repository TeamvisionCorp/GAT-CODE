package com.gateside.autotesting.Gat.manager;

import com.gateside.autotesting.Gat.manager.StepsCaseManager;
import com.gateside.autotesting.Gat.dataobject.testcase.InterfaceStepsCase;
import com.gateside.autotesting.Gat.dataobject.testcase.InterfaceTestStep;
import com.gateside.autotesting.Gat.util.GlobalConfig;
import com.gateside.autotesting.Lib.xmlService.XMLParser;
import com.gateside.autotesting.Lib.xmlService.XMLSerializer;




public class InterfaceStepsCaseManager extends StepsCaseManager
{

	@Override
	public InterfaceStepsCase geTestCase(String caseID) throws Exception
	{
		InterfaceStepsCase caseResult=new InterfaceStepsCase();
		String filePath=GlobalConfig.getTestCaseFilePath();
		String testCaseXPth="AllTestCases/TestCase";
		String caseXMLString=this.getTestObjectXML(caseID, filePath, testCaseXPth,"ID");
		caseResult=(InterfaceStepsCase)XMLSerializer.XMLToObject(caseResult,caseXMLString);
		caseResult=formatTestCase(caseResult, filePath);
		return caseResult;
	}
	
	private InterfaceStepsCase formatTestCase(InterfaceStepsCase testCase,String filePath)
	{
	    resetAssembly(testCase,filePath);
	    resetGroup(testCase,filePath);
	    resetParametersFilePath(testCase,filePath);
		return testCase;
	}
	
	private void resetAssembly(InterfaceStepsCase testCase,String filePath)
	{
		String assemblyXpath="AllTestCases/StepAssembly";
		for(InterfaceTestStep step: testCase.Steps)
		{
			if(step.StepAssembly==null || step.StepAssembly=="")
			{
				step.StepAssembly=XMLParser.getElementsByXPath(filePath, assemblyXpath).get(0).getTextTrim();
			}
		}
	}
	
	private void resetGroup(InterfaceStepsCase testCase,String filePath)
	{
		String groupXpath="AllTestCases/StepGroup";
		for(InterfaceTestStep step: testCase.Steps)
		{			
			if(step.StepGroup==null || step.StepGroup=="")
			{
				step.StepGroup=XMLParser.getElementsByXPath(filePath, groupXpath).get(0).getTextTrim();
			}
		}
	}
	
	private void resetParametersFilePath(InterfaceStepsCase testCase,String filePath)
	{
		String parameterFilePath="AllTestCases/StepParametersFilePath";
		for(InterfaceTestStep step: testCase.Steps)
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
	
	

}
