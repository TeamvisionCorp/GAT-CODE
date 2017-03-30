package com.gateside.autotesting.Gat.manager;



import com.gateside.autotesting.Gat.manager.IManager;
import com.gateside.autotesting.Gat.manager.InterfaceSingleStepCaseManager;
import com.gateside.autotesting.Gat.manager.InterfaceStepsCaseManager;
import com.gateside.autotesting.Gat.manager.InterfaceStepsParameterManager;
import com.gateside.autotesting.Gat.manager.WebUIElementsManager;
import com.gateside.autotesting.Gat.manager.WebUIStepsCaseManager;
import com.gateside.autotesting.Gat.manager.WebUIStepsParameterManager;
import com.gateside.autotesting.Gat.dataobject.EnumObjectManager;
import com.gateside.autotesting.Lib.common.SimpleLogger;

public class TestObjectManagerFactory {

	public static IManager getTestObjectManager(EnumObjectManager objectType) throws Exception
	{
		if(objectType==null){throw new Exception("objectType should not be null or empty!");}
		IManager result=null;
		switch (objectType.ordinal()) 
		{
		   case 2:
	           result=new InterfaceStepsParameterManager();
	           break;
		   case 0:
			   result=new InterfaceSingleStepCaseManager();
			   break;
		   case 1:
			   result=new InterfaceStepsCaseManager();
			   break;
		   case 3:
			   result=new WebUIStepsParameterManager();
			   break;
		   case 4:
			   result= new WebUIStepsCaseManager();
			   break;
		   case 5:
			   result= new WebUIElementsManager();
			   break;
		}
		return result;
	}

}
