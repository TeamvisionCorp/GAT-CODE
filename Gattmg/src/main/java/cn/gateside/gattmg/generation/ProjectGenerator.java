package cn.gateside.gattmg.generation;

import java.io.IOException;

import com.gateside.autotesting.Lib.common.ConfigReader;

import cn.gateside.gattmg.infos.TempType;
import cn.gateside.gattmg.util.ProjectUtil;
import cn.gateside.gattmg.util.TemplateUtil;


public class ProjectGenerator {
	
	public static void createProject(){
		new ProjectUtil().createProjectDir(ProjectUtil.getProjectBasePath());
	}
	
	/**
	 * @param filePath project dir
	 * @throws IOException ex
	 */
	public static void createProjectFile(String filePath) throws IOException{
		String tmpString = TemplateUtil.getTempPath();
//		List<String> tmpNames = TemplateUtil.tempFileNameList();
		
		TempType[] tempNames = TempType.values();
		for(TempType eachTempName : tempNames)
		{
			String tmpFileContents = TemplateUtil.getTempFileString(tmpString, eachTempName.name());
			tmpFileContents=formatprojectFileContent(tmpFileContents,eachTempName);
			ProjectUtil.createProjectFiles(filePath, eachTempName, tmpFileContents);
		}
	}
	
	private static String formatprojectFileContent(String tempeteContent,TempType temName)
	{
		String result="";

		result=tempeteContent.replace("{PROJECTNAME}",ConfigReader.GetValue("gatCreator.properties","projectName"));

		result=result.replace("{LIBPATH}",ConfigReader.GetValue("gatCreator.properties","libPath").replaceAll("\\\\","\\\\\\\\"));

		result=result.replace("{ROOTDIR}",ConfigReader.GetValue("gatCreator.properties","rootDir").replaceAll("\\\\","\\\\\\\\"));
		
		//result=result.replaceAll("\\\\","\\\\\\\\");
		return result; 
	}
	
}
