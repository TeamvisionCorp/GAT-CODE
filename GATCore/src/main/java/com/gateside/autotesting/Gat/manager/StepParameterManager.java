package com.gateside.autotesting.Gat.manager;


import java.util.List;
import java.util.Set;

import org.dom4j.Element;

import com.gateside.autotesting.Gat.manager.TestObjectManager;
import com.gateside.autotesting.Gat.dataobject.TestObject;
import com.gateside.autotesting.Gat.dataobject.stepparameter.GlobalParameter;
import com.gateside.autotesting.Gat.dataobject.stepparameter.InterfaceStepParameter;
import com.gateside.autotesting.Gat.dataobject.stepparameter.StepParameter;
import com.gateside.autotesting.Gat.util.GlobalConfig;
import com.gateside.autotesting.Lib.xmlService.XMLParser;
import com.gateside.autotesting.Lib.xmlService.XMLSerializer;

public abstract class StepParameterManager extends TestObjectManager
{

	@Override
	public TestObject getItem(String ID) throws Exception
	{
		StepParameter stepParameter=getStepParameter(ID);
		GlobalParameter globalParameter=this.getGlobalParameter();
		stepParameter.globalParameter=globalParameter;
		return stepParameter;
	}

	protected abstract StepParameter getStepParameter(String parameterID) throws Exception;
	
	protected GlobalParameter getGlobalParameter()
	{
		GlobalParameter parameterResult=new GlobalParameter();
		String filePath=GlobalConfig.getStepsParameterFilePath();
		String stepParameterXPth="AllStepParameters/GlobalParameters";
		List<Element> globalParameterList=XMLParser.getElementsByXPath(filePath, stepParameterXPth);
		if(globalParameterList.size()>0)
		{
		   String parameterXML=globalParameterList.get(0).asXML();
		   parameterResult= (GlobalParameter)XMLSerializer.XMLToObject(parameterResult,parameterXML);
		}
		return parameterResult;
	}
	

}
