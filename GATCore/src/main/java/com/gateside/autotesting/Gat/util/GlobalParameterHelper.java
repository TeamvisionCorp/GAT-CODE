package com.gateside.autotesting.Gat.util;

import java.util.List;

import org.dom4j.Element;

import com.gateside.autotesting.Gat.dataobject.stepparameter.GlobalParameter;
import com.gateside.autotesting.Lib.xmlService.XMLParser;
import com.gateside.autotesting.Lib.xmlService.XMLSerializer;

public class GlobalParameterHelper 
{
	 public static GlobalParameter getGlobalParameter()
	 {
		 String globalParameterPath=GlobalConfig.getRootDir()+GlobalConfig.getAutoProjectName()+GlobalConfig.getSlash()+"DataFiles"+GlobalConfig.getSlash()+"Xmls"+GlobalConfig.getSlash()+GlobalConfig.getGlobalParameterFileName();
		 GlobalParameter parameterResult=new GlobalParameter();
		 String stepParameterXPth="GlobalParameters";
		 List<Element> globalParameterList=XMLParser.getElementsByXPath(globalParameterPath, stepParameterXPth);
		 if(globalParameterList.size()>0)
		 {
			   String parameterXML=globalParameterList.get(0).asXML();
			   parameterResult= (GlobalParameter)XMLSerializer.XMLToObject(parameterResult,parameterXML);
		 }
		 return parameterResult; 
	 }
}
