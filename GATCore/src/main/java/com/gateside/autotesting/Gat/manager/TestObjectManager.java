package com.gateside.autotesting.Gat.manager;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.Element;

import com.gateside.autotesting.Gat.manager.IManager;
import com.gateside.autotesting.Gat.dataobject.TestObject;
import com.gateside.autotesting.Lib.xmlService.XMLParser;

public abstract class TestObjectManager implements IManager
{

	public TestObjectManager() 
    {
		// TODO Auto-generated constructor stub
	}

	public abstract TestObject getItem(String ID) throws Exception;
	
	protected String getTestObjectXML(String elementID,String xmlFilePath,String elementXpath,String attributName) throws Exception
	{
		Element XMLElement=XMLParser.getElementByID(xmlFilePath, elementXpath,attributName,elementID);
		if (XMLElement==null) throw new Exception("TestObject with id "+elementID+" can not be found");
		return XMLElement.asXML();
	}
	
	protected List<Element> getTestObjectXMLs(String xmlFilePath,String elementXpath) throws Exception
	{
		List<Element> XMLElements=XMLParser.getElementsByXPath(xmlFilePath, elementXpath);
		return XMLElements;
	}
	
	

}
