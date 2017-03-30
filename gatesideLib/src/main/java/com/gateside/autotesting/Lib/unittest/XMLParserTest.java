package com.gateside.autotesting.Lib.unittest;

import java.util.List;

import org.dom4j.Element;
import org.testng.annotations.Test;

import com.gateside.autotesting.Lib.xmlService.XMLParser;

public class XMLParserTest {
  @Test
  public void getElementsByXPathTest()
  {
	  List<Element> resultsElements= XMLParser.getElementsByXPath("d:\\xmltest.xml","ReturnInfo/userlist/item");
	  for(Element item : resultsElements)
	  {
		  System.out.print(item.asXML());
	  }
  }
  
  
  
  @Test
  public void getElementByID()
  {
	  Element resultsElements= XMLParser.getElementByID("d:\\xmltest.xml","ReturnInfo/userlist/item","id","nihao");
	  System.out.print(resultsElements.asXML());
  }
}
