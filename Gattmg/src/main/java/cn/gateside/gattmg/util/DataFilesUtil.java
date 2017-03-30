package cn.gateside.gattmg.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.DocumentException;





import cn.gateside.gattmg.extents.ExcelExtents;
import cn.gateside.gattmg.extents.XmlExtents;
import cn.gateside.gattmg.infos.DataFileType;
import cn.gateside.gattmg.infos.ExecutorType;

import com.gateside.autotesting.Gat.util.GlobalConfig;

public class DataFilesUtil {
	
	/**
	 * @param fileType xml excel
	 * @return String
	 * @throws IOException ex
	 */
	public static String getDataFilesPath(DataFileType fileType) throws IOException{
		String dataFilePath = null;
		
		if(fileType == DataFileType.EXCEL)
		{
			dataFilePath = ProjectUtil.getProjectBasePath() + GlobalConfig.getSlash()+"DataFiles"+GlobalConfig.getSlash()+"Excels";
		}
		else
		{
			dataFilePath = ProjectUtil.getProjectBasePath() + GlobalConfig.getSlash()+"DataFiles"+GlobalConfig.getSlash()+"Xmls";
		}
		return dataFilePath;
	}
	
	/**
	 * @param fileType  xml excel
	 * @return String list
	 * @throws IOException ex
	 */
	public static List<String> getWholeNames(DataFileType fileType) throws IOException{
		String dataFilePath = null;
		ArrayList<String> allFileNames = new ArrayList<String>();
		
		if(fileType == DataFileType.EXCEL)
		{
			dataFilePath = getDataFilesPath(fileType);
			FileUtil.getFilesName(dataFilePath, allFileNames);
		}else
		{
			dataFilePath = getDataFilesPath(fileType);
			FileUtil.getFilesName(dataFilePath, allFileNames);
		}
		return allFileNames;
	}
	
	/**
	 * @param fileType excel xml 
	 * @return String list
	 * @throws IOException ex
	 */
	public static List<String> getPreNames(DataFileType fileType) throws IOException{
		String dataFilePath = null;
		ArrayList<String> wholeFileNames =new ArrayList<String>();
		ArrayList<String> preFileNames = new ArrayList<String>();
		
		if(fileType == DataFileType.EXCEL){
			dataFilePath = getDataFilesPath(fileType);
			FileUtil.getFilesName(dataFilePath, wholeFileNames);
			for(int j=0; j<wholeFileNames.size(); j++){
				String eachPreName = wholeFileNames.get(j).substring(0, wholeFileNames.get(j).lastIndexOf("."));
				preFileNames.add(eachPreName);
			}
			return preFileNames;
		}else if(fileType == DataFileType.XML){
			dataFilePath = getDataFilesPath(fileType);
			FileUtil.getFilesName(dataFilePath, wholeFileNames);
			for(int j=0; j<wholeFileNames.size(); j++){
				String eachPreName = wholeFileNames.get(j).substring(0, wholeFileNames.get(j).lastIndexOf("."));
				preFileNames.add(eachPreName);
			}
			return preFileNames;
		}else{
			return preFileNames;
		}
	}
	
	/**
	 * Excel: TestClassName = ExcelFile sheetName
	 * @param fileType excel|xml
	 * @param eachFileName filename
	 * @return String list
	 * @throws IOException ex
	 * @throws DocumentException ex
	 */
	public static List<String> getTestClassNames(DataFileType fileType, String eachFileName) throws IOException, DocumentException{
		List<String> classNameList = new ArrayList<String>();
		String dataFilePath = null;
		
		if(fileType == DataFileType.EXCEL)
		{
			dataFilePath =DataFilesUtil.getDataFilesPath(fileType)+GlobalConfig.getSlash()+eachFileName;
			System.out.println(dataFilePath);
			classNameList = ExcelExtents.getSheetNameList(dataFilePath);
		}
		else
		{
			if(eachFileName.endsWith("TestCase.xml"))
			{
			  String className=eachFileName.substring(0, eachFileName.indexOf("."));
			  System.out.println(className);
			  classNameList.add(className.replace(GlobalConfig.getSlash(),"_"));
			}
		}
		return classNameList;
	}
	

	
	/**
	 * @param fileType excel|xml
	 * @param eachFileName filename
	 * @param fileSheetName excel sheetname
	 * @return TestMethod name list
	 * @throws Exception  ex
	 */
	public static List<String> getTestMethodNames(DataFileType fileType, String eachFileName, String fileSheetName) throws Exception{
		List<String> methodNameList = new ArrayList<String>();
		String dataFilePath = null;
		
		if(fileType == DataFileType.EXCEL)
		{
			dataFilePath = DataFilesUtil.getDataFilesPath(fileType)+GlobalConfig.getSlash()+eachFileName;
			Integer rowCounts = ExcelExtents.getSheetRowCounts(dataFilePath, fileSheetName);
			for(Integer i=1; i<rowCounts; i++)
			{
				Object rowObject = null;
				rowObject = ExcelExtents.getSheetRow(dataFilePath, fileSheetName, i);
				methodNameList.add(ExcelExtents.getFirstColElement(dataFilePath, rowObject));
			}
		}
		else
		{
			if(eachFileName.endsWith("TestCase.xml"))
			{
			   dataFilePath =DataFilesUtil.getDataFilesPath(fileType)+GlobalConfig.getSlash()+eachFileName;
			   methodNameList = XmlExtents.getAttributeValueByElementName(dataFilePath, "TestCase", "Name");
			}
		}
		return methodNameList;
	}
	
	public static List<String> getTestCaseID(DataFileType fileType, String eachFileName, String fileSheetName) throws IOException, DocumentException{
		List<String> caseIDList = new ArrayList<String>();
		String dataFilePath = null;
	    if(eachFileName.endsWith("TestCase.xml"))
		{
			   dataFilePath =DataFilesUtil.getDataFilesPath(fileType)+GlobalConfig.getSlash()+ eachFileName;
			   caseIDList = XmlExtents.getAttributeValueByElementName(dataFilePath, "TestCase", "ID");
		}
		return caseIDList;
	}
	
	public static String getTestStepPackage(DataFileType fileType, String eachFileName) throws IOException, DocumentException{
		String packageName="";
		String dataFilePath = null;
		try
		{
			 if(eachFileName.endsWith("TestCase.xml"))
				{
					   dataFilePath =DataFilesUtil.getDataFilesPath(fileType)+GlobalConfig.getSlash()+ eachFileName;
					   packageName = XmlExtents.getElementByName(dataFilePath,"StepAssembly").getTextTrim();
				}
			
		}
		catch(Exception ex)
		{
			
		}
	 
		return packageName;
	}
	
	/**
	 * @param fileType  excel|xml
	 * @param fileName file name
	 * @param sheetName excel sheet name
	 * @return String list
	 * @throws IOException ex
	 * @throws DocumentException ex
	 */
	public static List<String> getExecutorParams(DataFileType fileType, String fileName, String sheetName) throws Exception, DocumentException{
		List<String> executorParamList = new ArrayList<String>();
				
		if(fileType == DataFileType.EXCEL)
		{
			List<String> ids = getTestMethodNames(fileType, fileName, sheetName);
			for(String id:ids)
			{
//				fileName=fileName.replace(DataFilesUtil.getDataFilesPath(fileType)+GlobalConfig.getSlash(),"");
				executorParamList.add("\"" + fileName.substring(0, fileName.lastIndexOf(".")).replace("\\","\\\\") + "." + sheetName + "." + id + "\"");
			}
		}
		else
		{
			List<String> testCaseNames = getTestClassNames(fileType, fileName);
			for(String eachTestCaseName:testCaseNames)
			{
				List<String> testCaseIds = getTestCaseID(fileType, fileName, "");
				for(String id:testCaseIds)
				{

					
//					fileName=fileName.replace(DataFilesUtil.getDataFilesPath(fileType)+GlobalConfig.getSlash(),"");
					executorParamList.add("\"" + fileName.substring(0, fileName.lastIndexOf(".")).replace("\\","\\\\") + ".xml\"" + ","+"\""+id+"\"");
				}
			}
		}
		return executorParamList;
	}
}
