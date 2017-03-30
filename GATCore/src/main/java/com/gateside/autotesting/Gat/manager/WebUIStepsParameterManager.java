package com.gateside.autotesting.Gat.manager;

import com.gateside.autotesting.Gat.manager.StepParameterManager;
import com.gateside.autotesting.Gat.dataobject.stepparameter.WebUIStepParameter;
import com.gateside.autotesting.Gat.util.GlobalConfig;
import com.gateside.autotesting.Lib.common.SimpleLogger;
import com.gateside.autotesting.Lib.xmlService.XMLSerializer;


public class WebUIStepsParameterManager extends StepParameterManager
{

	@Override
	public WebUIStepParameter getStepParameter(String parameterID) throws Exception
	{
		WebUIStepParameter parameterResult=new WebUIStepParameter();
		String filePath=GlobalConfig.getStepsParameterFilePath();
		String stepParameterXPth="AllStepParameters/StepParameter";
		SimpleLogger.logInfo(this.getClass(),filePath+parameterID);
		String parameterXML=this.getTestObjectXML(parameterID,filePath, stepParameterXPth, "ID");
		return (WebUIStepParameter) XMLSerializer.XMLToObject(parameterResult,parameterXML);
	}
}
