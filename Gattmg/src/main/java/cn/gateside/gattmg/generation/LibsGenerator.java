package cn.gateside.gattmg.generation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cn.gateside.gattmg.util.FileUtil;
import cn.gateside.gattmg.util.ProjectUtil;

import com.gateside.autotesting.Gat.util.GlobalConfig;

public class LibsGenerator {

	public static void copyLibFiles() throws IOException {
		String testProPath = null;
		String generatorProPath = null;
		ArrayList<String> libNames = new ArrayList<String>();
		
		testProPath = FileUtil.getProjectDir() + GlobalConfig.getSlash()+"lib"+GlobalConfig.getSlash();
		generatorProPath = ProjectUtil.getProjectPath() + GlobalConfig.getSlash()+"lib"+GlobalConfig.getSlash();
	    FileUtil.getFilesName(testProPath, libNames);
		
		for(String eachLibName:libNames){
//			System.out.println("::::::::::"+generatorProPath+eachLibName);
//			System.out.println("=========="+testProPath+eachLibName);
			FileUtil.copyFiles(testProPath, generatorProPath, eachLibName);
		}
	}

}
