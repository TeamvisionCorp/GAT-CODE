package com.gateside.autotesting.Gat.manager;

import java.util.ArrayList;
import java.util.List;

import org.apache.bcel.generic.RETURN;

import com.gateside.autotesting.Gat.dataobject.TestObject;
import com.gateside.autotesting.Gat.dataobject.testcase.AutoTestCase;
import com.gateside.autotesting.Gat.dataobject.testcase.InterfaceStepsCase;
import com.gateside.autotesting.Gat.dataobject.testcase.StepsCase;
import com.gateside.autotesting.Gat.dataobject.EnumObjectManager;
import com.gateside.autotesting.Gat.manager.TestObjectManagerFactory;
import com.gateside.autotesting.Gat.util.GlobalConfig;
import com.gateside.autotesting.Lib.common.SimpleLogger;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import bsh.This;

public class InterfaceStepsCaseImporter extends StepsCaseImporter
{
	private String currentCaseFileName="";
	
	@Override
	public void doImport(String rootDir) throws Exception {
		List<String> allTestCasePaths=this.getFilePath(rootDir);
		for(String caseFilePath:allTestCasePaths)
		{
			Integer startIndex=caseFilePath.lastIndexOf("Xmls")+5;
			Integer endIndex=caseFilePath.lastIndexOf(".");
			currentCaseFileName=caseFilePath.substring(startIndex,endIndex);
			currentCaseFileName=currentCaseFileName.replaceAll(GlobalConfig.getSlash(),"_");
			try
			{
				System.out.println(currentCaseFileName);
				List<InterfaceStepsCase> interfaceCases=this.getItems(caseFilePath);
				for(InterfaceStepsCase stepCase:interfaceCases)
				{
					System.out.println(this.toJsonString(stepCase,1));
				}	
			}
			catch (Exception e) 
			{
				SimpleLogger.logError(this.getClass(), e);
				continue;
			}
			
		}
	}
	
	
	private List<InterfaceStepsCase> getItems(String objectFilePath) throws Exception 
	{
		List<InterfaceStepsCase> result=new ArrayList<InterfaceStepsCase>();
		InterfaceStepsCaseManager interfaceStepsCaseManager=(InterfaceStepsCaseManager)TestObjectManagerFactory.getTestObjectManager(EnumObjectManager.IStepsCaseManager);
		for(StepsCase item:interfaceStepsCaseManager.getAllTestCase(objectFilePath))
		{
			result.add((InterfaceStepsCase)item);
		}
		return result;
	}


	@Override
	public String toJsonString(TestObject testObject,Integer caseType) throws Exception {
		InterfaceStepsCase iStepsCase=(InterfaceStepsCase)testObject;
		AutoTestCase testCase=new AutoTestCase();
		testCase.PackageName=iStepsCase.StepAssembly.substring(0,iStepsCase.StepAssembly.length()-1)+"_unittest";
		testCase.ClassName=currentCaseFileName;
		testCase.CaseName=iStepsCase.Name;
		testCase.CaseTag=iStepsCase.CaseTags;
		testCase.CaseType=caseType;
		testCase.InterfaceID=Integer.valueOf(iStepsCase.InterfaceID);
		testCase.ModuleID=Integer.valueOf(iStepsCase.ModuleID);
		testCase.ProjectID=5;
		Gson gsonObject=new Gson();
		return gsonObject.toJson(testCase);
	}

}
