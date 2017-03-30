package com.gateside.autotesting.Gat.unittest;



import org.testng.annotations.Test;

import com.gateside.autotesting.Gat.dataobject.FrameType;
import com.gateside.autotesting.Gat.dataobject.WindowType;
import com.gateside.autotesting.Gat.dataobject.testcase.InterfaceStepsCase;
import com.gateside.autotesting.Gat.dataobject.uielement.UIElement;
import com.gateside.autotesting.Lib.xmlService.XMLParser;
import com.gateside.autotesting.Lib.xmlService.XMLSerializer;

public class UIElementTest 
{
  @Test
  public void UIElementTest() 
  {
	  UIElement element=new UIElement();
	  element.NodeID="fdsfsd";
	  element.ControlType="fdsfsd";
	  element.FrameType=FrameType.FramePage.DefaultPage;
	  element.ClassName="nihao";
	  element.CssSelector="fsdfdsfsd";
	  element.ID="Test01";
	  element.IFrameIndex="1";
	  element.IFrameName="nihao";
	  element.InnerText="fdsfsd";
	  element.LinkText="text";
	  element.Name="sdfdsfsd";
	  element.PropertyName="fsdfds";
	  element.PropertyValue="fdsfdsfsd";
	  element.TagName="fsdfdsfsd";
	  element.WindowName="fsdfsd";
	  element.WindowType=WindowType.MainBrowser;
	  element.XPath="fdsfdsfsd";
	  XMLSerializer.ObjectToXML(element,"d:\\element.xml");
//	  UIElement case12=new UIElement();
//	  String caseXML= XMLParser.getElementByID("d:\\element.xml","AllTestCases/TestCase","ID","Test01").asXML();
//	  System.out.print(caseXML);
//	  XMLSerializer.XMLToObject(case12,caseXML);
//	  System.out.print(case12.ID);
  }
}
