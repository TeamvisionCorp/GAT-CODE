package com.gateside.autotesting.Gat.manager;

import java.util.ArrayList;
import java.util.List;

import org.apache.bcel.generic.RETURN;

import com.gateside.autotesting.Gat.dataobject.TestObject;
import com.gateside.autotesting.Gat.dataobject.testcase.InterfaceStepsCase;
import com.gateside.autotesting.Gat.dataobject.testcase.StepsCase;
import com.gateside.autotesting.Gat.dataobject.testcase.WebUIStepsCase;
import com.gateside.autotesting.Gat.dataobject.EnumObjectManager;
import com.gateside.autotesting.Gat.manager.TestObjectManagerFactory;

public class WebUIStepsCaseImporter extends StepsCaseImporter
{


	@Override
	public void doImport(String objectFilePath) throws Exception {
		List<WebUIStepsCase> webUICases=this.getItems(objectFilePath);
	}
	
	
	private List<WebUIStepsCase> getItems(String objectFilePath) throws Exception 
	{
		List<WebUIStepsCase> result=new ArrayList<WebUIStepsCase>();
		WebUIStepsCaseManager webUIStepsCaseManager=(WebUIStepsCaseManager)TestObjectManagerFactory.getTestObjectManager(EnumObjectManager.IStepsCaseManager);
		for(StepsCase item:webUIStepsCaseManager.getAllTestCase(objectFilePath))
		{
			result.add((WebUIStepsCase)item);
		}
		return result;
	}


	@Override
	public String toJsonString(TestObject testObject,Integer objectType) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
