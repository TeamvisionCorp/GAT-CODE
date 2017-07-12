package com.gateside.autotesting.Gat.manager;


import com.gateside.autotesting.Gat.manager.TestObjectManager;

import java.util.List;

import org.dom4j.Element;

import com.gateside.autotesting.Gat.dataobject.TestObject;
import com.gateside.autotesting.Gat.dataobject.testcase.InterfaceStepsCase;
import com.gateside.autotesting.Gat.dataobject.testcase.InterfaceTestStep;
import com.gateside.autotesting.Gat.dataobject.testcase.StepsCase;
import com.gateside.autotesting.Gat.dataobject.testcase.TestStep;
import com.gateside.autotesting.Gat.util.GlobalConfig;
import com.gateside.autotesting.Lib.xmlService.XMLParser;
import com.gateside.autotesting.Lib.xmlService.XMLSerializer;

public abstract class StepsCaseManager extends TestObjectManager
{

	@Override
	public TestObject getItem(String ID) throws Exception
	{
		return geTestCase(ID);
	}

	public abstract StepsCase geTestCase(String caseID) throws Exception;
	
	
	public abstract List<StepsCase> getAllTestCase(String filePath) throws Exception;
	
	protected void transforSpecialChar(TestStep step)
	{
		step.StepParametersFilePath=step.StepParametersFilePath.replaceAll("_","/");
	}
	
	protected void resetTestCaseModuleID(StepsCase testCase,String filePath)
	{
		String assemblyXpath="AllTestCases/ModuleID";
		if(testCase.ModuleID==null || testCase.ModuleID=="")
		{
		   List<Element> moduleElements=XMLParser.getElementsByXPath(filePath, assemblyXpath);
		   if(moduleElements.size()>0)
		   {
			   testCase.ModuleID=moduleElements.get(0).getTextTrim();
		   }
		   else
		   {
			   testCase.ModuleID="0";
		   }
		}
	}
}
