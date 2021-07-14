package com.gateside.autotesting.Gat.manager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.methods.CloseableHttpResponse;

import com.gateside.autotesting.Gat.dataobject.TestCaseImportApi;
import com.gateside.autotesting.Gat.dataobject.testcase.AutoTestCase;
import com.gateside.autotesting.Gat.dataobject.testcase.StepsCase;
import com.gateside.autotesting.Gat.util.FileHelper;
import com.gateside.autotesting.Gat.util.GlobalConfig;
import com.gateside.autotesting.Lib.common.SimpleLogger;
import com.gateside.autotesting.Lib.httpclientService.HttpClientHelper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public abstract class StepsCaseImporter extends TestObjectImporter {

	@Override
	public List<String> getFilePath(String rootDir) {
		List<String> result=new ArrayList<String>();
		List<String> filePathes = new ArrayList<String>();
		FileHelper.traverseFolder(rootDir, filePathes);
		for(String filepath:filePathes)
		{
			if(filepath.endsWith("TestCase.xml"))
			{
				result.add(filepath);
			}
		}
		return result;
	}
	
	
	protected void sendImportRequest(AutoTestCase stepCase,List<AutoTestCase> projectAllCase )
	{
		String newCaseKey=stepCase.PackageName+stepCase.ClassName+stepCase.CaseName;
		Boolean isCaseExists=false;
		CloseableHttpResponse response=null;
		try
		{
			for(AutoTestCase oldCase: projectAllCase)
			{
			   String tempKey=oldCase.PackageName+oldCase.ClassName+oldCase.CaseName;
		       if(newCaseKey.equals(tempKey))
		       {
		    	      isCaseExists=true;
		    	      stepCase.id=oldCase.id;
		    	      break;
		       }
			}
			SimpleLogger.logInfo(this.getClass(),stepCase.toJson());
			if(isCaseExists)
			{
				response= HttpClientHelper.putJson(TestCaseImportApi.putApi+String.valueOf(stepCase.id)+"/",stepCase.toJson());
			}
			else
			{
				response=HttpClientHelper.postJson(TestCaseImportApi.postApi,stepCase.toJson());	
			}
			SimpleLogger.logInfo(this.getClass(),HttpClientHelper.getResponseText(response));
			
			
		} 
		catch (Exception e) {
			SimpleLogger.logError(this.getClass(),e);   
		}
	}
	
	
	protected List<AutoTestCase> getProjectAutoCase(Integer projectID) throws IOException, Exception
	{
        List<AutoTestCase> result=new ArrayList<AutoTestCase>();
		CloseableHttpResponse response= HttpClientHelper.getJson(TestCaseImportApi.listApi+String.valueOf(projectID));
		JSONObject autoCaseObject=JSONObject.fromObject(HttpClientHelper.getResponseText(response));
		JSONArray autoCaseJsonList=autoCaseObject.getJSONObject("result").getJSONArray("results");
		for(Integer i=0;i<autoCaseJsonList.size();i++)
		{
			Gson gsonObject=new Gson();
			AutoTestCase testCase=gsonObject.fromJson(autoCaseJsonList.getJSONObject(i).toString(), AutoTestCase.class);
            result.add(testCase);
		}
		return result;
	}
	
	public String setTestCaseFilePath(String caseFilePath)
	{
		String result="";
		Integer startIndex=caseFilePath.lastIndexOf("Xmls")+5;
		Integer endIndex=caseFilePath.lastIndexOf(".")+1;
		caseFilePath=caseFilePath.substring(startIndex,endIndex);
		SimpleLogger.logInfo(this.getClass(),"create test class name");
		SimpleLogger.logInfo(this.getClass(),"the test case filepath is:"+caseFilePath);
		if(GlobalConfig.getSlash()=="\\")
		{
			result=caseFilePath.replaceAll("\\\\","_");
		}
		else
		{
			result=caseFilePath.replaceAll(GlobalConfig.getSlash(),"_");
		}
		result=result.substring(0,result.length()-1);
		SimpleLogger.logInfo(this.getClass(),"the test class name  is :"+result);
		return result;
	}

}
