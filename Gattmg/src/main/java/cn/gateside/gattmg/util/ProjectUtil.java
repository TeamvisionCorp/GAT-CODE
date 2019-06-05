package cn.gateside.gattmg.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.gateside.gattmg.infos.DataFileType;
import com.gateside.autotesting.Gat.dataobject.testcase.InterfaceStepsCase;
import com.gateside.autotesting.Gat.dataobject.testcase.StepsCase;
import com.gateside.autotesting.Lib.xmlService.XMLParser;
import com.gateside.autotesting.Lib.xmlService.XMLSerializer;
import org.dom4j.Document;






import cn.gateside.gattmg.extents.XmlExtents;
import cn.gateside.gattmg.infos.ProjectInfos;
import cn.gateside.gattmg.infos.TempType;

import com.gateside.autotesting.Gat.util.GlobalConfig;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class ProjectUtil {
	
	
	public static String getProjectBasePath(){
		File parentFile = null;
		String basePath = null;
		try {
			parentFile = FileUtil.pathToFile("..");
			basePath = FileUtil.getFileDir(parentFile);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return basePath;
	}
	
	
	private ArrayList<String> appendDirs(){
		ArrayList<String> wholeDirStrings = new ArrayList<String>();
//		wholeDirStrings.add(ProjectInfos.PRO_BIN_DIR);
		wholeDirStrings.add(ProjectInfos.PRO_SRC_DIR);
//		wholeDirStrings.add(ProjectInfos.PRO_LIB_DIR);
//		wholeDirStrings.add(ProjectInfos.PRO_TEST_OUTPUT_DIR);
//		wholeDirStrings.add(ProjectInfos.PRO_SETTINGS_DIR);
		return wholeDirStrings;
	}
	
	
	public void createProjectDir(String proBasePath){
		if(proBasePath.contains("\\")){
			proBasePath = proBasePath.replace("\\", "/");
		}
		for(int i=0; i<this.appendDirs().size(); i++){
			FileUtil.createFileDir(proBasePath + this.appendDirs().get(i).replace("\\", "/"));
		}
	}
	
	
	public static void createProjectFiles(String proBasePath, TempType tmpTypeName, String fileContents){
		String fileName = null;
		
		switch(tmpTypeName){
//		case ClasspathTmp:
//			proBasePath = ProjectUtil.getProjectPath() + GlobalConfig.getSlash();
//			fileName = ProjectInfos.PRO_CLASSPATH_FILE;
//			FileUtil.createFile(proBasePath, fileName, fileContents, false);
//			break;
//		case ProjectTmp:
//			proBasePath = ProjectUtil.getProjectPath() + GlobalConfig.getSlash();
//			fileName = ProjectInfos.PRO_FILE;
//			FileUtil.createFile(proBasePath, fileName, fileContents, false);
//			break;
//		case SettingsTmp:
//			proBasePath = getProjectPath() + GlobalConfig.getSlash()+".settings"+GlobalConfig.getSlash();
//			fileName = ProjectInfos.PRO_SETTINGS_FILE;
//			FileUtil.createFile(proBasePath, fileName, fileContents, false);
//			break;
//		case GatConfigTmp:
//			proBasePath = ProjectUtil.getProjectPath() + GlobalConfig.getSlash();
//			fileName = ProjectInfos.PRO_GAT_CONFIG;
//			FileUtil.createFile(proBasePath, fileName, fileContents, false);
//			break;
//		case LogConfigTmp:
//			proBasePath = ProjectUtil.getProjectPath() + GlobalConfig.getSlash();
//			fileName = ProjectInfos.PRO_LOG_CONFIG;
//			FileUtil.createFile(proBasePath, fileName, fileContents, false);
//			break;
//		case buildTmp:
//			proBasePath = ProjectUtil.getProjectPath() + GlobalConfig.getSlash();
//			fileName = ProjectInfos.PRO_BUILD_FILE;
//			FileUtil.createFile(proBasePath, fileName, fileContents, false);
//			break;
		case TestClassTmp:
			proBasePath = getProjectPath() + ProjectInfos.SRC_PATH;
			break;
		default:
			
		}
	}	
	
	
	public static String getProjectPath(){
		String projectPath = ProjectUtil.getProjectBasePath()+GlobalConfig.getSlash()+ProjectInfos.getProjectName();
		return projectPath;
	}
	
	
	public static void createTestngXml(List<String> packageNameList) throws IOException
	{
		List<String> classStringNames = new ArrayList<String>();
		for(String packageName:packageNameList)
		{
			String[] pageFolders=packageName.split("\\.");
			String tempPath="";
			for(Integer i=0;i<pageFolders.length;i++)
			{
				tempPath=tempPath+GlobalConfig.getSlash()+pageFolders[i];
			}
			List<String> classNames= FileUtil.getFilesNameNoSuffix(ProjectUtil.getProjectPath()+ProjectInfos.TEST_SRC_PATH+tempPath);
			for(String eachName:classNames)
			{
				classStringNames.add(packageName + eachName);
			}
		}
		
		Document document = XmlExtents.createXml("suite", classStringNames);
		XmlExtents.XmlOutput(document, ProjectUtil.getProjectPath() + GlobalConfig.getSlash(), "testng.xml");
	}

	public static void createTestNgXml(HashMap<String ,List<HashMap<String, List<String>>>> map) throws Exception{

		for(Map.Entry<String ,List<HashMap<String, List<String>>>> entry : map.entrySet()){
			Document document = DocumentHelper.createDocument();
			Element root = document.addElement("suite");
			Element test = root.addElement("test");
			Element classes = test.addElement("classes");
			for (HashMap<String, List<String>> map2:entry.getValue()){
				for (Map.Entry<String, List<String>> classMethodsMapEntry : map2.entrySet()) {

					String className = classMethodsMapEntry.getKey();
					Element clazz = classes.addElement("class");
					clazz.addAttribute("name", className);
					List<String> methodNameList = classMethodsMapEntry.getValue();
					Element methods = clazz.addElement("methods");
					for (String str : methodNameList){
						Element ele = methods.addElement("include");
						ele.addAttribute("name", str);
					}
				}
			}
			root.addAttribute("name","Suite");
			root.addAttribute("parallel", "none");
			test.addAttribute("name", "Test");
			XmlExtents.XmlOutput(document, getProjectBasePath() + GlobalConfig.getSlash() + "testNg" + GlobalConfig.getSlash(), entry.getKey() + "testng.xml");
		}
	}

	public static HashMap<String ,List<HashMap<String, List<String>>>> generateTagMap(DataFileType fileType) throws Exception {

		HashMap<String ,List<HashMap<String, List<String>>>> tagMap = new HashMap<>();
		List<String> dataFileNames = DataFilesUtil.getWholeNames(fileType);
		List<String> tagLists = new ArrayList<>();
		String dataFilePath = ProjectUtil.getProjectPath() + GlobalConfig.getSlash()+"DataFiles"+GlobalConfig.getSlash()+"Xmls";
		for(String eachFileName:dataFileNames){   //提取所有tags
			if(eachFileName.endsWith("TestCase.xml")){
				eachFileName=eachFileName.replace(DataFilesUtil.getDataFilesPath(fileType)+GlobalConfig.getSlash(),"");
				List<StepsCase> stepsCaseList = getAllTestCase(dataFilePath + GlobalConfig.getSlash() +eachFileName);
				for (StepsCase stepsCase : stepsCaseList){
					String[] tags = stepsCase.CaseTags.split(",");
					for (String tag : tags){
						if (!tagLists.contains(tag) && (!tag.trim().isEmpty())){
							tagLists.add(tag);
						}
					}
				}
			}
		}
		for (String tag : tagLists) {
			List<HashMap<String, List<String>>> classList = new ArrayList<>();
			for(String eachFileName:dataFileNames)
			{
				List<String> methodList = new ArrayList<>();
				HashMap<String, List<String>> methodClassMap = new HashMap<>();
				if(eachFileName.endsWith("TestCase.xml"))
				{
					eachFileName=eachFileName.replace(DataFilesUtil.getDataFilesPath(fileType)+GlobalConfig.getSlash(),"");
					String className=DataFilesUtil.getTestClassNames(fileType, eachFileName).get(0);
					List<StepsCase> stepsCaseList = getAllTestCase(dataFilePath + GlobalConfig.getSlash() +eachFileName);
					String wholePathToClass = getWholePathToClass(fileType,eachFileName,className);  //package+className
					for (StepsCase stepsCase : stepsCaseList){
						if (inCaseTags(tag, stepsCase.CaseTags)){
							methodList.add(stepsCase.Name);
						}
					}
					if (methodList.size()>0) {
						methodClassMap.put(wholePathToClass,methodList);
					}
				}
				if (!methodClassMap.isEmpty()) {
					classList.add(methodClassMap);
				}
			}
			if (classList.size()>0) {
				tagMap.put(tag,classList);
			}
		}
		return tagMap;
	}

	public static String getWholePathToClass(DataFileType fileType, String eachFileName, String className) throws IOException, DocumentException {

		String testStepPackageName=DataFilesUtil.getTestStepPackage(fileType, eachFileName);
		String wholePathToClass = "";
		if(testStepPackageName.length()!=0)
		{
			String[] pageFolders=testStepPackageName.split("\\.");
			String tempPath="";
			for(Integer i=0;i<pageFolders.length;i++)
			{
				tempPath=tempPath+GlobalConfig.getSlash()+pageFolders[i];
			}
			String new_package_name=testStepPackageName.substring(0,testStepPackageName.length()-1)+"_unittest";
			wholePathToClass = new_package_name + "." + className;
			return wholePathToClass;
		}
		return null;
	}

	public static List<StepsCase> getAllTestCase(String filePath) {
		List<StepsCase> result=new ArrayList<>();
		String testCaseXPth="AllTestCases/TestCase";
		List<Element> caseXMLElement=getTestObjectXMLs(filePath, testCaseXPth);
		for(Element item:caseXMLElement)
		{
			InterfaceStepsCase caseResult=new InterfaceStepsCase();
			caseResult= (InterfaceStepsCase) XMLSerializer.XMLToObject(caseResult,item.asXML());
			// caseResult=iManager.formatTestCase(caseResult, filePath);
			result.add(caseResult);
		}
		return result;
	}

	private static boolean inCaseTags(String tag, String caseTags){
		if (tag.equals(caseTags)){
			return true;
		}
		if (caseTags.contains(tag + ",") || caseTags.contains("," + tag)){
			return true;
		}

		return false;
	}



	protected static List<Element> getTestObjectXMLs(String xmlFilePath,String elementXpath)
	{
		List<Element> XMLElements= XMLParser.getElementsByXPath(xmlFilePath, elementXpath);
		return XMLElements;
	}
}
