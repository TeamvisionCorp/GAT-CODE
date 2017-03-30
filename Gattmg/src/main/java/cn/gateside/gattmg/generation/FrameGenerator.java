package cn.gateside.gattmg.generation;

import cn.gateside.gattmg.infos.DataFileType;
import cn.gateside.gattmg.infos.ProjectInfos;
import cn.gateside.gattmg.util.ProjectUtil;

import java.util.ArrayList;
import java.util.List;

import com.gateside.autotesting.Lib.common.SimpleLogger;

public class FrameGenerator {

//	public static void main(String[] args) 
//	{
//		String[] args1={"0"};
//		if(args1[0].equals("1"))
//		{
//			SimpleLogger.logInfo("fds"+args[0]);
//			createWebUITestProject();
//		}
//		else
//		{
//		   createInterfaceTestProject();	
//		}
//	}
	
	public static void createInterfaceTestProject()
	{
				ProjectGenerator.createProject();
				String testClassFilePath = ProjectUtil.getProjectPath() + ProjectInfos.SRC_PATH;
				List<String> packageNameList=new ArrayList<String>();
				try 
				{
//					SimpleLogger.logInfo("Start create project files");
//					ProjectGenerator.createProjectFile(ProjectUtil.getProjectPath());
					//LibsGenerator.copyLibFiles();
					
					SimpleLogger.logInfo("Start create interface Excel Testcase class files");
					List<String> excelPackageNameList=TestClassGenerator.generateTestClassFile(DataFileType.EXCEL,
							testClassFilePath);
					
					SimpleLogger.logInfo("Start create Interface XML Testcase class files");
					List<String> XMLPackageNameList=TestClassGenerator.generateTestClassFile(DataFileType.XML,
							testClassFilePath);
					
					packageNameList.addAll(excelPackageNameList);
					packageNameList.addAll(XMLPackageNameList);
					
					SimpleLogger.logInfo("Start create testng.xml files");
					ProjectUtil.createTestngXml(packageNameList);
				} catch (Exception e)
				{
					SimpleLogger.logError(e);
				}	
	}
	
	public static void createWebUITestProject()
	{
				ProjectGenerator.createProject();
				String testClassFilePath = ProjectUtil.getProjectPath() + ProjectInfos.SRC_PATH;
			
				try 
				{

					
					SimpleLogger.logInfo("Start create Webui Testcase class files");
					List<String> packageNameList=TestClassGenerator.generateTestClassFile(DataFileType.WebUIXML,	testClassFilePath);
					
					SimpleLogger.logInfo("Start create testng.xml files");
					ProjectUtil.createTestngXml(packageNameList);
				} catch (Exception e)
				{
					SimpleLogger.logError(e);
				}	
	}
	
	}
