package com.baidu.gameqa.unittest;

import java.io.IOException;

import org.dom4j.DocumentException;

import cn.gateside.gattmg.generation.TestClassGenerator;
import cn.gateside.gattmg.infos.DataFileType;
import cn.gateside.gattmg.infos.ProjectInfos;
import cn.gateside.gattmg.util.ProjectUtil;

public class DataFileUnitTest {
	public static void main(String[] args) throws Exception {
//		/*----------getDataFilesPath Test----------*/
//		System.out.println(DataFilesUtil.getDataFilesPath(DataFileType.EXCEL));
//		System.out.println(DataFilesUtil.getDataFilesPath(DataFileType.XML));
//		
//		/*----------getWholeNames Test----------*/
//		System.out.println(DataFilesUtil.getWholeNames(DataFileType.EXCEL));
//		System.out.println(DataFilesUtil.getWholeNames(DataFileType.XML));
//		
//		/*----------getPreNames Test----------*/
//		System.out.println(DataFilesUtil.getPreNames(DataFileType.EXCEL));
//		System.out.println(DataFilesUtil.getPreNames(DataFileType.XML));
		
		/*----------getTestClassNames Test----------*/
//		System.out.println(DataFilesUtil.getTestClassNames(DataFileType.XML, "TestCase2.xml") + "\n\n");
//		for(String eachDataFileName:(DataFilesUtil.getWholeNames(DataFileType.EXCEL))){
//			System.out.println(eachDataFileName);
//			System.out.println(DataFilesUtil.getTestClassNames(DataFileType.EXCEL, eachDataFileName));
//			System.out.println(DataFilesUtil.getTestMethodNames(DataFileType.EXCEL, "Platform.xlsx", "ajax_sign"));
//			System.out.println("\n\n");
//		}
		
		/*----------ExcelExtents Test----------*/
//		Integer rowCount = ExcelExtents.getSheetRowCounts("D:\\workspace\\InterfaceAutoTest\\DataFiles\\Excels\\Platform.xlsx", "ajax_sign");
//		System.out.println(rowCount);
//		
//		Object rowObject = ExcelExtents.getSheetRow("D:\\workspace\\InterfaceAutoTest\\DataFiles\\Excels\\Platform.xlsx", "ajax_sign", 20);
//		String string = ExcelExtents.getFirstColElement("D:\\workspace\\InterfaceAutoTest\\DataFiles\\Excels\\Platform.xlsx", rowObject);
//		System.out.println(string);
//		
//		List<String> dataFileNames = DataFilesUtil.getWholeNames(DataFileType.EXCEL);
//		System.out.println("dataFileNames:::  " + dataFileNames);
//		List<String> executorParams = new ArrayList<String>();
//		
//		for(String eachFile:dataFileNames){
//			List<String> fileSheetNames = DataFilesUtil.getTestClassNames(DataFileType.EXCEL, eachFile);
//			System.out.println("fileSheetNames:::   " + fileSheetNames);
//			for(String eachSheetName:fileSheetNames){
//				List<String> ids = DataFilesUtil.getTestMethodNames(DataFileType.EXCEL, eachFile, eachSheetName);
//				for(String id:ids){
//					System.out.println("fileName=="+eachFile+"==sheetName=="+eachSheetName+"==ids=="+id);
//					executorParams.add(eachFile+"_"+eachSheetName+"_"+id);
//				}
//			}
//			System.out.println("\n\n\n");
//		}
//		System.out.println(executorParams);
//		System.out.println(DataFilesUtil.getTestMethodNames(DataFileType.EXCEL, "Platform.xlsx", "ajax_sign"));
		
		/*----------getExecutorParams Test----------*/
//		for(String each:(DataFilesUtil.getExecutorParams(DataFileType.XML)))
//			System.out.println(each);
//		System.out.println("\n\n\n");
//		for(String eachString:(DataFilesUtil.getExecutorParams(DataFileType.EXCEL)))
//			System.out.println(eachString);
		
		/*----------getTestMethodNames Test EXCEL----------*/
//		List<String> testMethodNames = DataFilesUtil.getTestMethodNames(DataFileType.EXCEL, "Platform.xlsx", "ajax_sign");
//		List<String> executorParams = DataFilesUtil.getExecutorParams(DataFileType.EXCEL, "Platform.xlsx", "ajax_sign");
		//XML
//		List<String> dataFileNames = DataFilesUtil.getWholeNames(DataFileType.XML);
//		for(String eachFileName:dataFileNames){
//			List<String> testMethodNames = DataFilesUtil.getTestMethodNames(DataFileType.XML, eachFileName, "");
//			List<String> executorParams = DataFilesUtil.getExecutorParams(DataFileType.XML, eachFileName, "");
//			System.out.println(executorParams);
//			System.out.println(TestClassGenerator.generateTestClassContent("InterfaceMultiStepExecutor",
//					eachFileName.substring(0, eachFileName.lastIndexOf(".")), testMethodNames, executorParams));
//			String contents = TestClassGenerator.generateTestClassContent("InterfaceMultiStepExecutor",
//					eachFileName.substring(0, eachFileName.lastIndexOf(".")), testMethodNames, executorParams);
//			FileUtil.createFile("D:\\workspace\\GatTestProject\\src\\com\\baidu\\gameqa\\generation\\", 
//					eachFileName.substring(0, eachFileName.lastIndexOf("."))+".java", contents);
//		}
		
		//Excel
//		List<String> dataFileNames = DataFilesUtil.getWholeNames(DataFileType.EXCEL);
//		for(String eachFileName:dataFileNames){
//			List<String> sheetNames = DataFilesUtil.getTestClassNames(DataFileType.EXCEL, eachFileName);
//			for(String eachSheet:sheetNames){
//				List<String> testMethodNames = DataFilesUtil.getTestMethodNames(DataFileType.EXCEL, eachFileName, eachSheet);
//				List<String> executorParams = DataFilesUtil.getExecutorParams(DataFileType.EXCEL, eachFileName, eachSheet);
//				System.out.println(executorParams);
//				System.out.println(TestClassGenerator.generateTestClassContent("InterfaceSingleStepExecutor",
//						eachFileName.substring(0, eachFileName.lastIndexOf(".")), testMethodNames, executorParams));
//				String contents = TestClassGenerator.generateTestClassContent("InterfaceSingleStepExecutor",
//						eachFileName.substring(0, eachFileName.lastIndexOf(".")), testMethodNames, executorParams);
//				FileUtil.createFile("D:\\workspace\\GatTestProject\\src\\com\\baidu\\gameqa\\generation\\", eachSheet + ".java", contents);
//			}	
//		}
		String testClassFilePath = ProjectUtil.getProjectPath() + ProjectInfos.SRC_PATH;
		TestClassGenerator.generateTestClassFile(DataFileType.EXCEL, testClassFilePath);
	}
}
