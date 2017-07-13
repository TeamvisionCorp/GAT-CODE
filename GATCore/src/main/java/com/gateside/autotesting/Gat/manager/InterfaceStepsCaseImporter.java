package com.gateside.autotesting.Gat.manager;

import java.io.IOException;
import java.security.KeyStore.PrivateKeyEntry;
import java.util.ArrayList;
import java.util.List;

import org.apache.bcel.generic.NEW;
import org.apache.bcel.generic.RETURN;
import org.apache.http.client.methods.CloseableHttpResponse;

import com.gateside.autotesting.Gat.dataobject.TestObject;
import com.gateside.autotesting.Gat.dataobject.testcase.AutoTestCase;
import com.gateside.autotesting.Gat.dataobject.testcase.InterfaceStepsCase;
import com.gateside.autotesting.Gat.dataobject.testcase.StepsCase;
import com.gargoylesoftware.htmlunit.javascript.host.Set;
import com.gateside.autotesting.Gat.dataobject.EnumObjectManager;
import com.gateside.autotesting.Gat.dataobject.TestCaseImportApi;
import com.gateside.autotesting.Gat.manager.TestObjectManagerFactory;
import com.gateside.autotesting.Gat.util.GlobalConfig;
import com.gateside.autotesting.Lib.common.SimpleLogger;
import com.gateside.autotesting.Lib.httpclientService.HttpClientHelper;
import com.google.gson.Gson;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class InterfaceStepsCaseImporter extends StepsCaseImporter
{
	
	private String currentCaseFileName="";
	private Integer project=0;
	private List<AutoTestCase> projectAllCase=null;

	public InterfaceStepsCaseImporter(Integer projectID) throws Exception 
	{
		this.project=projectID;
		this.projectAllCase=this.getProjectAutoCase(projectID);
	}
	
	@Override
	public void doImport(String rootDir) throws Exception {
		List<String> allTestCasePaths=this.getFilePath(rootDir);
		for(String caseFilePath:allTestCasePaths)
		{
			setTestCaseFilePath(caseFilePath);//the testcase file path is the test class name
			try
			{
				List<InterfaceStepsCase> interfaceCases=this.getItems(caseFilePath);
				for(InterfaceStepsCase stepCase:interfaceCases)
				{
					if(!stepCase.StepModule)
					{
						this.sendImportRequest(this.toDBBean(stepCase, 1));
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
	

	public AutoTestCase toDBBean(TestObject testObject,Integer caseType) throws Exception {
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
	
	private void setTestCaseFilePath(String caseFilePath)
	{
		Integer startIndex=caseFilePath.lastIndexOf("Xmls")+5;
		Integer endIndex=caseFilePath.lastIndexOf(".");
		currentCaseFileName=caseFilePath.substring(startIndex,endIndex);
		currentCaseFileName=currentCaseFileName.replaceAll(GlobalConfig.getSlash(),"_");
	}
	
	private void sendImportRequest(AutoTestCase stepCase)
	{
		String newCaseKey=stepCase.PackageName+stepCase.ClassName+stepCase.CaseName;
		Boolean isCaseExists=false;
		try
		{
			for(AutoTestCase oldCase: this.projectAllCase)
			{
			   String tempKey=oldCase.PackageName+oldCase.ClassName+oldCase.CaseName;
		       if(newCaseKey.equals(tempKey))
		       {
		    	      isCaseExists=true;
		    	      stepCase.id=oldCase.id;
		    	      break;
		       }
			}
			if(isCaseExists)
			{
				HttpClientHelper.putJson(TestCaseImportApi.putApi+String.valueOf(stepCase.id)+"/",stepCase.toJson());
			}
			else
			{
			   HttpClientHelper.postJson(TestCaseImportApi.postApi,stepCase.toJson());	
			}
			
			
		} 
		catch (Exception e) {
			SimpleLogger.logError(this.getClass(),e);   
		}
	}
	
	
	private List<AutoTestCase> getProjectAutoCase(Integer projectID) throws IOException, Exception
	{
        List<AutoTestCase> result=new ArrayList<AutoTestCase>();
		CloseableHttpResponse response= HttpClientHelper.getJson(TestCaseImportApi.listApi+String.valueOf(projectID));
		JSONObject autoCaseObject=JSONObject.fromObject(HttpClientHelper.getResponseText(response));
		JSONArray autoCaseJsonList=autoCaseObject.getJSONArray("result");
		for(Integer i=0;i<autoCaseJsonList.size();i++)
		{
			Gson gsonObject=new Gson();
			AutoTestCase testCase=gsonObject.fromJson(autoCaseJsonList.getJSONObject(i).toString(), AutoTestCase.class);
            result.add(testCase);
		}
		return result;
	}


}
