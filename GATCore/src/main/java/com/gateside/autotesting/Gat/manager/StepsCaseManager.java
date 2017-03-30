package com.gateside.autotesting.Gat.manager;


import com.gateside.autotesting.Gat.manager.TestObjectManager;
import com.gateside.autotesting.Gat.dataobject.TestObject;
import com.gateside.autotesting.Gat.dataobject.testcase.InterfaceStepsCase;
import com.gateside.autotesting.Gat.dataobject.testcase.StepsCase;
import com.gateside.autotesting.Gat.dataobject.testcase.TestStep;
import com.gateside.autotesting.Gat.util.GlobalConfig;
import com.gateside.autotesting.Lib.xmlService.XMLParser;
import com.gateside.autotesting.Lib.xmlService.XMLSerializer;

public abstract class StepsCaseManager extends TestObjectManager
{

	@Override
	public TestObject getItem(String ID) throws Exception
	{
		return geTestCase(ID);
	}

	public abstract StepsCase geTestCase(String caseID) throws Exception;
	

}
