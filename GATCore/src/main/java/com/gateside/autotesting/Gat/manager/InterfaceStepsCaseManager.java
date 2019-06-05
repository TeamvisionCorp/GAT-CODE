package com.gateside.autotesting.Gat.manager;

import com.gateside.autotesting.Gat.dataobject.testcase.InterfaceStepsCase;
import com.gateside.autotesting.Gat.dataobject.testcase.InterfaceTestStep;
import com.gateside.autotesting.Gat.dataobject.testcase.StepsCase;
import com.gateside.autotesting.Gat.util.GlobalConfig;
import com.gateside.autotesting.Lib.xmlService.XMLParser;
import com.gateside.autotesting.Lib.xmlService.XMLSerializer;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.List;




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
	
	@Override
	public List<StepsCase> getAllTestCase(String filePath) throws Exception {
		List<StepsCase> result=new ArrayList<StepsCase>();
		String testCaseXPth="AllTestCases/TestCase";
		List<Element> caseXMLElement=this.getTestObjectXMLs(filePath, testCaseXPth);
		for(Element item:caseXMLElement)
		{
			InterfaceStepsCase caseResult=new InterfaceStepsCase();
			caseResult=(InterfaceStepsCase)XMLSerializer.XMLToObject(caseResult,item.asXML());
			caseResult=formatTestCase(caseResult, filePath);
			result.add(caseResult);
		}
		return result;
	}
	
	private InterfaceStepsCase formatTestCase(InterfaceStepsCase testCase,String filePath)
	{
	    resetAssembly(testCase,filePath);
	    resetGroup(testCase,filePath);
	    resetParametersFilePath(testCase,filePath);
	    resetTestCaseInterfaceID(testCase,filePath);
	    resetTestCaseModuleID(testCase,filePath);
		return testCase;
	}
	
	private void resetTestCaseInterfaceID(InterfaceStepsCase testCase,String filePath)
	{
		String assemblyXpath="AllTestCases/InterfaceID";
		if(testCase.InterfaceID==null || testCase.InterfaceID=="")
		{
		   List<Element> caseElements=XMLParser.getElementsByXPath(filePath, assemblyXpath);
		   if(caseElements.size()>0)
		   {
			   testCase.InterfaceID=caseElements.get(0).getTextTrim();  
		   }
		   else
		   {
			   testCase.InterfaceID="0";
		   }
		}
	}
	
	
	private void resetAssembly(InterfaceStepsCase testCase,String filePath)
	{
		String assemblyXpath="AllTestCases/StepAssembly";
		if(testCase.StepAssembly==null || testCase.StepGroup=="")
		{
			testCase.StepAssembly=XMLParser.getElementsByXPath(filePath, assemblyXpath).get(0).getTextTrim();
		}
		for(InterfaceTestStep step: testCase.Steps)
		{
			if(step.StepAssembly==null || step.StepAssembly=="")
			{
				step.StepAssembly=testCase.StepAssembly;
			}
		}
	}
	
	private void resetGroup(InterfaceStepsCase testCase,String filePath)
	{
		String groupXpath="AllTestCases/StepGroup";
		if(testCase.StepGroup==null || testCase.StepGroup=="")
		{
			testCase.StepGroup=XMLParser.getElementsByXPath(filePath, groupXpath).get(0).getTextTrim();
		}

		for(InterfaceTestStep step: testCase.Steps)
		{			
			if(step.StepGroup==null || step.StepGroup=="")
			{
				step.StepGroup=testCase.StepGroup;
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
			else
			{
				this.transforSpecialChar(step);	
			}
		}
	}
	
	

	
	
	

}
