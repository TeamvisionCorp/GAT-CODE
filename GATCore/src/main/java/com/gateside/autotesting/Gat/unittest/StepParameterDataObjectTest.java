package com.gateside.autotesting.Gat.unittest;


import java.awt.print.Printable;

import org.testng.annotations.Test;

import com.gateside.autotesting.Gat.dataobject.stepparameter.GlobalParameter;
import com.gateside.autotesting.Gat.dataobject.stepparameter.InterfaceStepParameter;
import com.gateside.autotesting.Gat.dataobject.stepparameter.Parameter;
import com.gateside.autotesting.Gat.manager.InterfaceStepsParameterManager;
import com.gateside.autotesting.Gat.util.GlobalConfig;
import com.gateside.autotesting.Lib.xmlService.XMLParser;
import com.gateside.autotesting.Lib.xmlService.XMLSerializer;

public class StepParameterDataObjectTest 
{
  @Test
  public void interfaceStepParameterObjectTest()
  {
	  InterfaceStepParameter sttepParameter=new InterfaceStepParameter();
//	  sttepParameter.CommandText="fdsfdsfsd";
//	  sttepParameter.ConnectiongString="fdsfds";
//	  sttepParameter.ID="fdsfsd";
//	  sttepParameter.Name="fdsfsdfsd";
//	  GlobalParameter gparameter=new GlobalParameter();
//	  Parameter parameter=new Parameter();
//	  parameter.key="user";
//	  parameter.value="zhangtiande";
////	  sttepParameter.parameters.add(parameter);
//	  gparameter.parameters.add(parameter);
//	  XMLSerializer.ObjectToXML(sttepParameter,"d:\\stepparameter.xml");
//      String xmlString=XMLParser.getElementByID("d:\\stepparameter.xml", "AllStepParameters/StepParameter", "ID", "Test01").asXML();
//  sttepParameter=(InterfaceStepParameter)XMLSerializer.XMLToObject(sttepParameter, xmlString);
//  System.out.print(sttepParameter.CommandText);
//  System.out.print(sttepParameter.getValue("user"));
	  
  }
  
//  @Test
//  public void globalParameterTest() throws Exception
//  {
//	 GlobalConfig.setStepsParameterFilePath("InterfaceAutomation\\DataFiles\\Xmls\\RegisterwithInviteCodeParameters.xml");
//	 InterfaceStepsParameterManager manager=new InterfaceStepsParameterManager();
//	 InterfaceStepParameter parameter= (InterfaceStepParameter)manager.getItem("Test01");
//	 System.out.println(parameter.globalParameter.parameters.size());
//  }
}
