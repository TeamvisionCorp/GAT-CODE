package cn.gateside.gattmg.infos;

import com.gateside.autotesting.Gat.util.GlobalConfig;
import com.gateside.autotesting.Lib.common.ConfigReader;



public class ProjectInfos 
{
	public static final String PRO_NAME = "GatTestProject";
	
	public static final String PACKAGE_NAME="com.gateside.autotesting.generation.unittest";
	
	public static final String PACKAGE_PATH = GlobalConfig.getSlash()+"com"+GlobalConfig.getSlash()+"gateside"+GlobalConfig.getSlash()+
			                  "autotesting"+GlobalConfig.getSlash()+"generation"+GlobalConfig.getSlash()+"unittest"+GlobalConfig.getSlash();
	
	public static final String SRC_PATH = GlobalConfig.getSlash()+"src"+GlobalConfig.getSlash()+"test"+GlobalConfig.getSlash()+"java"+GlobalConfig.getSlash()+"com"
			+ GlobalConfig.getSlash()+"gateside"+GlobalConfig.getSlash()+"autotesting"+GlobalConfig.getSlash()+"generation"+GlobalConfig.getSlash()+"unittest"+GlobalConfig.getSlash();
	public static final String TEST_SRC_PATH=GlobalConfig.getSlash()+"src"+GlobalConfig.getSlash()+"test"+GlobalConfig.getSlash()+"java"+GlobalConfig.getSlash();
	public static final String PRO_LIB_DIR = "/"+getProjectName()+"/"+"lib";
	public static final String PRO_SRC_DIR = "/"+getProjectName()+"/"+"src"+GlobalConfig.getSlash()+"test"+GlobalConfig.getSlash()+"java"+GlobalConfig.getSlash()+PACKAGE_PATH;
	public static final String PRO_BIN_DIR = "/"+getProjectName()+"/"+"bin";
	public static final String PRO_SETTINGS_DIR = "/"+getProjectName()+"/"+".settings";
	public static final String PRO_TEST_OUTPUT_DIR = "/"+getProjectName()+"/"+"test-output";
	
	public static final String PRO_CLASSPATH_FILE = ".classpath";
	public static final String PRO_FILE = ".project";
	public static final String PRO_BUILD_FILE = "build.xml";
	public static final String PRO_TESTNG_FILE = "testng.xml";
	public static final String PRO_SETTINGS_FILE = "org.eclipse.jdt.core.prefs";
	
	public static final String PRO_GAT_CONFIG = "gatConfig.properties";
	public static final String PRO_LOG_CONFIG = "logConfig.properties";
	
	public static String getProjectName()
	{
		return ConfigReader.GetValue("gatConfig.properties","projectName");		
	}
}
