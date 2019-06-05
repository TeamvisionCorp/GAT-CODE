package cn.gateside.gattmg.generation;

import cn.gateside.gattmg.infos.DataFileType;
import cn.gateside.gattmg.infos.ExecutorType;
import cn.gateside.gattmg.infos.ProjectInfos;
import cn.gateside.gattmg.infos.TemplateInfos;
import cn.gateside.gattmg.util.DataFilesUtil;
import cn.gateside.gattmg.util.FileUtil;
import cn.gateside.gattmg.util.ProjectUtil;
import cn.gateside.gattmg.util.TemplateUtil;
import com.gateside.autotesting.Gat.util.GlobalConfig;

import java.io.BufferedInputStream;
import java.util.ArrayList;
import java.util.List;


public class TestClassGenerator {
	

	

		//dataFileNames[overoverover]-----------DataFilesUtil.getPreNames
		//testClassNames[overoverover]----------DataFilesUtil.getTestClassNames
		//testMethodNames[overoverover]---------DataFilesUtil.getTestMethodNames
		//executorName[overoverover]------------DataFilesUtil.getExecutorName
		//executorParams[overoverover]----------DataFilesUtil.getExecutorParams
		//--[dataFileNames,testClassNames,testMethodNames]
	


	
	/**

	 * @param executorName testcase executor name
	 * @param testClassName class name
	 * @param testMethodNames method name
	 * @param executorParams executor parameters
	 * @return String
	 * @throws Exception  ex
	 */
	public static String generateTestClassContent(String executorName, String testClassName, 
			List<String> testMethodNames, List<String> executorParams) throws Exception{
		String testString = null;
		StringBuilder stringBuilder = new StringBuilder();
		
		BufferedInputStream input = TemplateUtil.init(TemplateUtil.getTempPath() + "TestClassTmp");
		String srcStrings = FileUtil.fileToString(input);
		String[] splitedStrings = TemplateUtil.splitTestClassTmp(srcStrings, TemplateInfos.__TEST__);
		
		for (int i = 0; i < splitedStrings.length; i++) {
			splitedStrings[i] = TemplateUtil.replaceTemplateFile(splitedStrings[i], TemplateInfos.$TemplateClassName, testClassName);
			splitedStrings[i] = TemplateUtil.replaceTemplateFile(splitedStrings[i], TemplateInfos.$InterfaceStepExecutorName, executorName);	
			if( i == 1 ){
				testString = splitedStrings[i];
				for(int j = 0; j < testMethodNames.size(); j++){
					splitedStrings[i] = TemplateUtil.replaceTemplateFile(testString, TemplateInfos.$TemplateTestMethod, testMethodNames.get(j));
					splitedStrings[i] = TemplateUtil.replaceTemplateFile(splitedStrings[i], TemplateInfos.$ExecutorParam, executorParams.get(j));
					stringBuilder.append(splitedStrings[i]);
				}
				splitedStrings[i] = "";
			}
			stringBuilder.append(splitedStrings[i]);
		}
		return stringBuilder.toString();
	}
	
	/**
	 * @param fileType  excel , xml
	 * @param filePath   file path
	 * @throws Exception ex
	 * @return String list
	 */
	public static List<String> generateTestClassFile(DataFileType fileType, String filePath) throws Exception{
		List<String> packageNameList=new ArrayList<String>();
		packageNameList.add(ProjectInfos.PACKAGE_NAME+".");
		
		if(fileType == DataFileType.EXCEL)
		{
			createTestCaseClassFileForExcel(filePath);
		}
	    if(fileType == DataFileType.XML)
		{
	    	packageNameList=createTestCaseClassFileForXml(filePath, fileType,ExecutorType.InterfaceStepsExecutor);
		}
	    
	    if(fileType == DataFileType.WebUIXML)
		{
	    	packageNameList=createTestCaseClassFileForXml(filePath, fileType,ExecutorType.WebUIStepsExecutor);
		}
	    
	    return packageNameList;
		
	}
	
	/**
	 * 
	 *        create testclass.java file for excel case
	 * @param filePath excel file path
	 * @throws Exception ex
	 */
	private static void createTestCaseClassFileForExcel(String filePath) throws Exception
	{
		List<String> dataFileNames = DataFilesUtil.getWholeNames(DataFileType.EXCEL);
		for(String eachFileName:dataFileNames)
		{
			eachFileName=eachFileName.replace(DataFilesUtil.getDataFilesPath(DataFileType.EXCEL)+GlobalConfig.getSlash(),"");
			String moduleName=eachFileName.substring(0,eachFileName.indexOf("."))+"_";
			moduleName=moduleName.replace(GlobalConfig.getSlash(),"_");
			List<String> sheetNames = DataFilesUtil.getTestClassNames(DataFileType.EXCEL, eachFileName);
			for(String eachSheet:sheetNames)
			{
				List<String> testMethodNames = DataFilesUtil.getTestMethodNames(DataFileType.EXCEL, eachFileName, eachSheet);
				List<String> executorParams = DataFilesUtil.getExecutorParams(DataFileType.EXCEL, eachFileName, eachSheet);
				String contents = TestClassGenerator.generateTestClassContent(ExecutorType.InterfaceSingleStepExecutor.toString(),
						moduleName+eachSheet, testMethodNames, executorParams);
				FileUtil.createFile(filePath+moduleName, eachSheet + ".java", contents, true);
			}	
		}
	}
	
	/**
	 * 
	 * @param filePath xml file path
	 * @param fileType xml file type :WebUIXML,XML
	 * @param execturType   executurType
	 * @throws Exception ex
	 * @return String list
	 */
	private static List<String> createTestCaseClassFileForXml(String filePath,DataFileType fileType,ExecutorType execturType) throws Exception
	{
		List<String> dataFileNames = DataFilesUtil.getWholeNames(fileType);
		List<String> packageNameList=new ArrayList<String>();
		String classFilePath=filePath;
		for(String eachFileName:dataFileNames)
		{
            if(eachFileName.endsWith("TestCase.xml"))
            {
            eachFileName=eachFileName.replace(DataFilesUtil.getDataFilesPath(DataFileType.XML)+GlobalConfig.getSlash(),"");
            String className=DataFilesUtil.getTestClassNames(DataFileType.XML, eachFileName).get(0);
			List<String> testMethodNames = DataFilesUtil.getTestMethodNames(fileType, eachFileName, "");
			List<String> executorParams = DataFilesUtil.getExecutorParams(fileType, eachFileName, "");				
			String contents = TestClassGenerator.generateTestClassContent(execturType.toString(),
					className, testMethodNames, executorParams);
			String testStepPackageName=DataFilesUtil.getTestStepPackage(fileType, eachFileName);
			if(testStepPackageName.length()!=0)
			{
				String[] pageFolders=testStepPackageName.split("\\.");
				String tempPath="";
				classFilePath="";
				for(Integer i=0;i<pageFolders.length;i++)
				{
					tempPath=tempPath+GlobalConfig.getSlash()+pageFolders[i];
				}
				classFilePath=ProjectUtil.getProjectPath()+ProjectInfos.TEST_SRC_PATH+tempPath+"_unittest";
				String new_package_name=testStepPackageName.substring(0,testStepPackageName.length()-1)+"_unittest";
				contents=contents.replaceAll(ProjectInfos.PACKAGE_NAME,new_package_name);
				/*
				/如果packageNameList中已存在该packageName则不再放到list里
				/修复生成testng xml文件中classes数量不正确的问题
				*/

				if (!packageNameList.contains(new_package_name+".")) {
					packageNameList.add(new_package_name+".");
				}
			}
			FileUtil.createFileDir(classFilePath);
			FileUtil.createFile(classFilePath+GlobalConfig.getSlash(), className + ".java", contents, true);
            }	
		}
		return packageNameList;
	}
    
}
