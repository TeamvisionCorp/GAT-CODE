package cn.gateside.gattmg.generation;

import cn.gateside.gattmg.infos.DataFileType;
import cn.gateside.gattmg.infos.ExecutorType;
import cn.gateside.gattmg.infos.ProjectInfos;
import cn.gateside.gattmg.util.DataFilesUtil;
import cn.gateside.gattmg.util.FileUtil;
import cn.gateside.gattmg.util.ProjectUtil;
import cn.gateside.gattmg.util.TemplateUtil;
import com.gateside.autotesting.Gat.util.GlobalConfig;
import com.gateside.autotesting.Lib.common.SimpleLogger;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.io.BufferedInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FrameGenerator {


    public static void createInterfaceTestProject() {
        ProjectGenerator.createProject();
        String testClassFilePath = ProjectUtil.getProjectPath() + ProjectInfos.SRC_PATH;
        List<String> packageNameList = new ArrayList<String>();
        HashMap<String, List<HashMap<String, List<String>>>> tagMap;
        try {
//					SimpleLogger.logInfo("Start create project files");
//					ProjectGenerator.createProjectFile(ProjectUtil.getProjectPath());
            //LibsGenerator.copyLibFiles();

            SimpleLogger.logInfo("Start create interface Excel Testcase class files");
            List<String> excelPackageNameList = TestClassGenerator.generateTestClassFile(DataFileType.EXCEL,
                    testClassFilePath);

            SimpleLogger.logInfo("Start create Interface XML Testcase class files");
            List<String> XMLPackageNameList = TestClassGenerator.generateTestClassFile(DataFileType.XML,
                    testClassFilePath);

            packageNameList.addAll(excelPackageNameList);
            packageNameList.addAll(XMLPackageNameList);

            SimpleLogger.logInfo("Start create testNg.xml files");
            ProjectUtil.createTestngXml(packageNameList);

            SimpleLogger.logInfo("Start create Tag Level testNg.xml files");
            tagMap = ProjectUtil.generateTagMap(DataFileType.XML);
            ProjectUtil.createTestNgXml(tagMap);
        } catch (Exception e) {
            SimpleLogger.logError(e);
        }
    }

    public static void createWebUITestProject() {
        ProjectGenerator.createProject();
        String testClassFilePath = ProjectUtil.getProjectPath() + ProjectInfos.SRC_PATH;

        try {


            SimpleLogger.logInfo("Start create Webui Testcase class files");
            List<String> packageNameList = TestClassGenerator.generateTestClassFile(DataFileType.WebUIXML, testClassFilePath);

            SimpleLogger.logInfo("Start create testng.xml files");
            ProjectUtil.createTestngXml(packageNameList);
        } catch (Exception e) {
            SimpleLogger.logError(e);
        }
    }

    public static void createInterfaceTestcaseByConfig(String confPath) {
        try {
            BufferedInputStream input = TemplateUtil.init(FileUtil.getProjectDir() + GlobalConfig.getSlash() + confPath);
            String tmpStrings = FileUtil.fileToString(input);
            JSONObject conf = JSONObject.fromObject(tmpStrings);
            JSONArray confDetails = conf.getJSONArray("map");
            String classFilePath = "";
            if (confDetails != null) {
                for (int i = 0; i < confDetails.size(); i++) {
                    JSONObject o = confDetails.getJSONObject(i);
                    if (o != null && o.getString("template") != null && o.getJSONArray("xmlPath").size() != 0) {
                        String template = o.getString("template");
                        String templatePath = FileUtil.getProjectDir() + GlobalConfig.getSlash() +
                                "TemplateFiles" + GlobalConfig.getSlash() + template;
                        for (Object eachPathObj : o.getJSONArray("xmlPath")) {
                            String eachPath = String.valueOf(eachPathObj);
                            ArrayList<String> allFileNames = new ArrayList<String>();
                            String eachAbsPath = ProjectUtil.getProjectBasePath() + GlobalConfig.getSlash() + "DataFiles"
                                    + GlobalConfig.getSlash() + "Xmls" + GlobalConfig.getSlash() + eachPath;
                            FileUtil.getFilesName(eachAbsPath, allFileNames);
                            for (String eachFileName : allFileNames) {
                                if (eachFileName.endsWith("TestCase.xml")) {
                                    eachFileName = eachFileName.replace(DataFilesUtil.getDataFilesPath(DataFileType.XML) + GlobalConfig.getSlash(), "");
                                    String className = DataFilesUtil.getTestClassNames(DataFileType.XML, eachFileName).get(0);
                                    List<String> testMethodNames = DataFilesUtil.getTestMethodNames(DataFileType.XML, eachFileName, "");
                                    List<String> executorParams = DataFilesUtil.getExecutorParams(DataFileType.XML, eachFileName, "");
                                    String contents = TestClassGenerator.generateTestClassContent(ExecutorType.InterfaceStepsExecutor.toString(),
                                            className, testMethodNames, executorParams, templatePath);
                                    String testStepPackageName = DataFilesUtil.getTestStepPackage(DataFileType.XML, eachFileName);
                                    if (testStepPackageName.length() != 0) {
                                        String[] pageFolders = testStepPackageName.split("\\.");
                                        String tempPath = "";
                                        for (int j = 0; j < pageFolders.length; j++) {
                                            tempPath = tempPath + GlobalConfig.getSlash() + pageFolders[j];
                                        }
                                        classFilePath = ProjectUtil.getProjectPath() + ProjectInfos.TEST_SRC_PATH + tempPath + "_unittest";
                                        String new_package_name = testStepPackageName.substring(0, testStepPackageName.length() - 1) + "_unittest";
                                        contents = contents.replaceAll(ProjectInfos.PACKAGE_NAME, new_package_name);
                                    }
                                    FileUtil.createFileDir(classFilePath);
                                    FileUtil.createFile(classFilePath + GlobalConfig.getSlash(), className + ".java", contents, true);
                                }
                            }
                        }
                    } else {
                        SimpleLogger.logInfo("第" + i + "个配置有问题，skipping");
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
