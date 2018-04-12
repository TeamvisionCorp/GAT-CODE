package com.gateside.autotesting.Gat.manager;

import java.util.ArrayList;
import java.util.List;


import com.gateside.autotesting.Gat.dataobject.TestObject;
import com.gateside.autotesting.Gat.dataobject.testcase.AutoTestCase;
import com.gateside.autotesting.Gat.dataobject.testcase.InterfaceStepsCase;
import com.gateside.autotesting.Gat.dataobject.testcase.StepsCase;
import com.gateside.autotesting.Gat.dataobject.testcase.WebUIStepsCase;
import com.gateside.autotesting.Gat.dataobject.EnumObjectManager;
import com.gateside.autotesting.Gat.manager.TestObjectManagerFactory;
import com.gateside.autotesting.Lib.common.SimpleLogger;

public class WebUIStepsCaseImporter extends StepsCaseImporter
{

	
	
	private String currentCaseFileName="";
	private Integer project=0;
	private List<AutoTestCase> projectAllCase=null;
	private Integer caseType=1;

	public WebUIStepsCaseImporter(Integer projectID,Integer caseType) throws Exception 
	{
		this.project=projectID;
		this.projectAllCase=this.getProjectAutoCase(projectID);
		this.caseType=caseType;
	}

	@Override
	public void doImport(String rootDir) throws Exception {
		List<String> allTestCasePaths=this.getFilePath(rootDir);
		for(String caseFilePath:allTestCasePaths)
		{
			this.currentCaseFileName=setTestCaseFilePath(caseFilePath);//the testcase file path is the test class name
			try
			{
				List<WebUIStepsCase> webUIStepsCases=this.getItems(caseFilePath);
				for(WebUIStepsCase stepCase:webUIStepsCases)
				{
					if(!stepCase.StepModule)
					{
						this.sendImportRequest(this.toDBBean(stepCase, this.caseType),this.projectAllCase);
					}
				}	
			}
			catch (Exception e) 
			{
				SimpleLogger.logError(this.getClass(), e);
				continue;
			}
			
		}
	}
	
	
	private List<WebUIStepsCase> getItems(String objectFilePath) throws Exception 
	{
		List<WebUIStepsCase> result=new ArrayList<WebUIStepsCase>();
		WebUIStepsCaseManager webUIStepsCaseManager=(WebUIStepsCaseManager)TestObjectManagerFactory.getTestObjectManager(EnumObjectManager.WebUIElementManager);
		for(StepsCase item:webUIStepsCaseManager.getAllTestCase(objectFilePath))
		{
			result.add((WebUIStepsCase)item);
		}
		return result;
	}
	
	
	private AutoTestCase toDBBean(TestObject testObject,Integer caseType) throws Exception {
		WebUIStepsCase iStepsCase=(WebUIStepsCase)testObject;
		AutoTestCase testCase=new AutoTestCase();
		testCase.PackageName=iStepsCase.StepAssembly.substring(0,iStepsCase.StepAssembly.length()-1)+"_unittest";
		testCase.ClassName=currentCaseFileName;
		testCase.CaseName=iStepsCase.Name;
		testCase.CaseTag=iStepsCase.CaseTags;
		testCase.CaseType=caseType;
		testCase.InterfaceID=0;
		testCase.ModuleID=Integer.valueOf(iStepsCase.ModuleID);
		testCase.ProjectID=this.project;
		testCase.Desc=iStepsCase.Desc;
		testCase.IsActive=iStepsCase.IsActive;
		return testCase;
	}


	
}
