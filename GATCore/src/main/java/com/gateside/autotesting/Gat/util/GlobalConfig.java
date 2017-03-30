package com.gateside.autotesting.Gat.util;

import java.io.File;
import java.util.Properties;




import bsh.This;

import com.gateside.autotesting.Gat.util.GlobalConfig;
import com.gateside.autotesting.Lib.common.ConfigReader;
import com.gateside.autotesting.Lib.common.SimpleLogger;

public class GlobalConfig 
{
  private static String TestCaseFilePath="";
  private static String StepsParameterFilePath="";
  private static String uIElementsFilePath="";
  private static String preStepResult="PRESTEPRESULT";
  private static String rootDir="";

  
  
/**
 * @return the testCaseFilePath
 */
public static String getTestCaseFilePath() {
	return TestCaseFilePath;
}


/**
 * @param testCaseFilePath the testCaseFilePath to set
 */
public static void setTestCaseFilePath(String testCaseFilePath)
{
	TestCaseFilePath =getRootDir()+testCaseFilePath;
}


/**
 * @return the stepsParameterFilePath
 */
public static String getStepsParameterFilePath() {
	return StepsParameterFilePath;
}

public static String getSlash()
{
	String slash="\\";
	Properties props=System.getProperties();  
    String oSName= props.getProperty("os.name"); 
	if(!oSName.startsWith("Windows"))
	{
		slash="/";
	}
    return slash;
}

public static String getRootDir()
{
   
    try
    {
    	rootDir=ConfigReader.GetValue("gatConfig.properties","rootDir");
    	if(rootDir.equals(""))
    	{
    	   File directory = new File("..");
           rootDir=directory.getCanonicalPath()+getSlash();
    	}
    	else
    	{
		   rootDir=rootDir+getSlash();
		}
    }
    catch(Exception e)
    {
    	SimpleLogger.logError(GlobalConfig.class,e);
    }
    return rootDir;
}

/**
 * @param stepsParameterFilePath the stepsParameterFilePath to set
 */
public static void setStepsParameterFilePath(String stepsParameterFilePath)
{
	StepsParameterFilePath =getRootDir()+ stepsParameterFilePath;
}


/**
 * @return the urlParametersSignal
 */
public static String getUrlParametersSignal() 
{
	return ConfigReader.GetValue("gatConfig.properties", "urlParametersSignal");
}


/**
 * @return the preStepResult
 */
public static String getPreStepResult() {
	return preStepResult;
}





/**
 * @return the domainName
 */
public static String getDomainName() {
	return ConfigReader.GetValue("gatConfig.properties","domainName");
}


public static String getStepMethodJarPath()
{
	if(ConfigReader.GetValue("gatConfig.properties","stepMethodJarFiles")=="")
	{
		return null;
	}
	else 
	{
		return getRootDir()+"Libs"+getSlash()+ConfigReader.GetValue("gatConfig.properties","stepMethodJarFiles");	
	}
}

public static String getDescColumnSignal()
{
	return ConfigReader.GetValue("gatConfig.properties", "descColumnSignal");
}


/**
 * @return the uIElementsFilePath
 */
public static String getuIElementsFilePath() {
	return uIElementsFilePath;
}


/**
 * @param uIElementsFilePath the uIElementsFilePath to set
 */
public static void setuIElementsFilePath(String uIElementsFilePath) {
	GlobalConfig.uIElementsFilePath =getRootDir()+ uIElementsFilePath;
}

public static String getWebControllDefaultPackage()
{
	return ConfigReader.GetValue("gatConfig.properties","webcontrolldefaultpackage");
}

public static String getBrowserType()
{
	return ConfigReader.GetValue("gatConfig.properties","browserType");
}

public static String screenPicuterPath()
{
	return ConfigReader.GetValue("gatConfig.properties","screenpicturedir")+getSlash();
}

public static String getAutoProjectName()
{
	String rootDir=ConfigReader.GetValue("gatConfig.properties","rootDir");
	String autoProjectName=ConfigReader.GetValue("gatConfig.properties","autoprojectfolder");
	if(rootDir.equals(""))
	{
		autoProjectName="";
	}
	else
	{
	   autoProjectName=autoProjectName+getSlash();	
	}
	return autoProjectName;
}

public static String getGlobalParameterFileName()
{
	String globalParameterFileName=ConfigReader.GetValue("gatConfig.properties","globalParameterFileName");
	return globalParameterFileName;
}

}
