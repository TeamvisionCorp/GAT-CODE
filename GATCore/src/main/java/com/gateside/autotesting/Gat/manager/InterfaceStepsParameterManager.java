package com.gateside.autotesting.Gat.manager;

import com.gateside.autotesting.Gat.manager.StepParameterManager;
import com.gateside.autotesting.Gat.dataobject.stepparameter.InterfaceStepParameter;
import com.gateside.autotesting.Gat.util.GlobalConfig;
import com.gateside.autotesting.Lib.xmlService.XMLSerializer;


public class InterfaceStepsParameterManager extends StepParameterManager
{
    @Override
	public InterfaceStepParameter getStepParameter(String parameterID) throws Exception
	{
		InterfaceStepParameter parameterResult=new InterfaceStepParameter();
		String filePath=GlobalConfig.getStepsParameterFilePath();
		String stepParameterXPth="AllStepParameters/StepParameter";
		String parameterXML=this.getTestObjectXML(parameterID,filePath, stepParameterXPth, "ID");
		return (InterfaceStepParameter)XMLSerializer.XMLToObject(parameterResult,parameterXML);
	}
}
