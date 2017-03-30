package com.gateside.autotesting.Gat.manager;

import com.gateside.autotesting.Gat.manager.TestObjectManager;
import com.gateside.autotesting.Gat.dataobject.TestObject;
import com.gateside.autotesting.Gat.dataobject.stepparameter.InterfaceStepParameter;
import com.gateside.autotesting.Gat.dataobject.uielement.UIElement;
import com.gateside.autotesting.Gat.util.GlobalConfig;
import com.gateside.autotesting.Lib.xmlService.XMLSerializer;

public class WebUIElementsManager extends TestObjectManager
{

	public WebUIElementsManager() 
    {
		// TODO Auto-generated constructor stub
	}

	@Override
	public TestObject getItem(String ID) throws Exception 
	{
		
		return getElement(ID);
	}
   
	private UIElement getElement(String ID) throws Exception
	{
		UIElement elementResult=new UIElement();
		String filePath=GlobalConfig.getuIElementsFilePath();
		String elementXPth="AllUIElements/UIElement";
		String elementXML=this.getTestObjectXML(ID,filePath, elementXPth, "NodeID");
		return (UIElement) XMLSerializer.XMLToObject(elementResult,elementXML);
	}
}
