package com.gateside.autotesting.Gat.util;

import com.gateside.autotesting.Gat.dataobject.EnumObjectManager;
import com.gateside.autotesting.Gat.dataobject.TestObject;
import com.gateside.autotesting.Gat.dataobject.stepparameter.InterfaceStepParameter;
import com.gateside.autotesting.Gat.dataobject.stepparameter.WebUIStepParameter;
import com.gateside.autotesting.Gat.manager.IManager;
import com.gateside.autotesting.Gat.manager.TestObjectManagerFactory;
import com.gateside.autotesting.Lib.common.SimpleLogger;

public class ParameterHelper {

	public ParameterHelper() 
    {
		// TODO Auto-generated constructor stub
	}
	
	@Deprecated
	public static TestObject getParameter(String parameterID) throws Exception
	{
		IManager manager=TestObjectManagerFactory.getTestObjectManager(EnumObjectManager.IStepParameterManager);
		InterfaceStepParameter parameter=(InterfaceStepParameter)manager.getItem(parameterID);
		return parameter;
	}
	
	public static TestObject getInterfaceStepParameter(String parameterID) throws Exception
	{
		IManager manager=TestObjectManagerFactory.getTestObjectManager(EnumObjectManager.IStepParameterManager);
		InterfaceStepParameter parameter=(InterfaceStepParameter)manager.getItem(parameterID);
		return parameter;
	}
	
	public static TestObject getWebUIStepParameter(String parameterID) throws Exception
	{
		IManager manager=TestObjectManagerFactory.getTestObjectManager(EnumObjectManager.WebUIStepParameterManager);
		WebUIStepParameter parameter=(WebUIStepParameter)manager.getItem(parameterID);
		return parameter;
	}
    
}
