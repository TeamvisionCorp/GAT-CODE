package cn.gateside.gattmg.generation;

import cn.gateside.gattmg.infos.DataFileType;
import cn.gateside.gattmg.infos.ProjectInfos;
import cn.gateside.gattmg.util.ProjectUtil;
import com.gateside.autotesting.Lib.common.SimpleLogger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FrameGenerator {


	public static void createInterfaceTestProject()
	{
				ProjectGenerator.createProject();
				String testClassFilePath = ProjectUtil.getProjectPath() + ProjectInfos.SRC_PATH;
				List<String> packageNameList=new ArrayList<String>();
				HashMap<String ,List<HashMap<String, List<String>>>> tagMap = new HashMap<>();
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
					
					SimpleLogger.logInfo("Start create testNg.xml files");
					ProjectUtil.createTestngXml(packageNameList);

					SimpleLogger.logInfo("Start create Tag Level testNg.xml files");
					tagMap = ProjectUtil.generateTagMap(DataFileType.XML);
					ProjectUtil.createTestNgXml(tagMap);
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
