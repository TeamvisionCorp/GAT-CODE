package com.gateside.autotesting.Gat.manager;

import com.gateside.autotesting.Gat.dataobject.EnumObjectManager;
import com.gateside.autotesting.Gat.dataobject.TestObject;
import com.gateside.autotesting.Gat.dataobject.testcase.AutoTestCase;
import com.gateside.autotesting.Gat.dataobject.testcase.InterfaceStepsCase;
import com.gateside.autotesting.Gat.dataobject.testcase.StepsCase;
import com.gateside.autotesting.Lib.common.SimpleLogger;

import java.util.ArrayList;
import java.util.List;


public class InterfaceStepsCaseImporter extends StepsCaseImporter
{
	
	private String currentCaseFileName="";
	private Integer project=0;
	private List<AutoTestCase> projectAllCase=null;
	private Integer caseType=1;
	private Integer caseGroup = 0;
    public static Integer NEW_CASE_COUNT = 0;
    public static Integer UPDATED_CASE_COUNT = 0;
	
    
    /**
     * 
     * @param projectID  ��ĿID
     * @param caseType   �������ͣ��ӿڣ�WebUI
     * @param caseGroup  �Զ���������ID
     * @throws Exception
     */
	public InterfaceStepsCaseImporter(Integer projectID,Integer caseType,Integer caseGroup) throws Exception 
	{
		this.project=projectID;
		this.projectAllCase=this.getProjectAutoCase(projectID);
		this.caseType=caseType;
		this.caseGroup = caseGroup;
	}

    public InterfaceStepsCaseImporter(Integer projectID, Integer caseType) throws Exception {
        this.project = projectID;
        this.projectAllCase = this.getProjectAutoCase(projectID);
        this.caseType = caseType;
        this.caseGroup = 0;
    }
	
	@Override
	public void doImport(String rootDir) throws Exception {
		List<String> allTestCasePaths=this.getFilePath(rootDir);
		for(String caseFilePath:allTestCasePaths)
		{
			this.currentCaseFileName=setTestCaseFilePath(caseFilePath);//the testcase file path is the test class name
			try
			{
				List<InterfaceStepsCase> interfaceCases=this.getItems(caseFilePath);
				for(InterfaceStepsCase stepCase:interfaceCases)
				{
					if(!stepCase.StepModule)
					{
						this.sendImportRequest(this.toDBBean(stepCase, this.caseType,this.caseGroup),this.projectAllCase);
					}
				}	
			}
			catch (Exception e) 
			{
				SimpleLogger.logError(this.getClass(), e);
				continue;
			}
			
		}
        SimpleLogger.logInfo(this.getClass(), "* * * * * * * * * * Import Statistics * * * * * * * * * *");
        SimpleLogger.logInfo(this.getClass(), "NewCase Count            " + NEW_CASE_COUNT);
        SimpleLogger.logInfo(this.getClass(), "UpdatedCase Count        " + UPDATED_CASE_COUNT);

	}
	

	private AutoTestCase toDBBean(TestObject testObject,Integer caseType,Integer caseGroup) throws Exception {
		InterfaceStepsCase iStepsCase=(InterfaceStepsCase)testObject;
		AutoTestCase testCase=new AutoTestCase();
		testCase.PackageName=iStepsCase.StepAssembly.substring(0,iStepsCase.StepAssembly.length()-1)+"_unittest";
		testCase.ClassName=currentCaseFileName;
		testCase.CaseName=iStepsCase.Name;
		testCase.CaseTag=iStepsCase.CaseTags;
		testCase.CaseType=caseType;
		testCase.InterfaceID=Integer.valueOf(iStepsCase.InterfaceID);
		testCase.ModuleID=Integer.valueOf(iStepsCase.ModuleID);
		testCase.ProjectID=this.project;
		testCase.Desc=iStepsCase.Desc;
		testCase.IsActive=iStepsCase.IsActive;
		testCase.TestCaseKey = iStepsCase.TestCaseKey;
		testCase.CaseGroupID = caseGroup;
		return testCase;
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

}
