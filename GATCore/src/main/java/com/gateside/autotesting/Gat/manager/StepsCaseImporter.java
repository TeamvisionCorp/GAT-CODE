package com.gateside.autotesting.Gat.manager;

import java.util.ArrayList;
import java.util.List;

import com.gateside.autotesting.Gat.dataobject.testcase.StepsCase;
import com.gateside.autotesting.Gat.util.FileHelper;
import com.google.gson.JsonObject;

public abstract class StepsCaseImporter extends TestObjectImporter {

	@Override
	public List<String> getFilePath(String rootDir) {
		List<String> result=new ArrayList<String>();
		List<String> filePathes = new ArrayList<String>();
		FileHelper.traverseFolder(rootDir, filePathes);
		for(String filepath:filePathes)
		{
			if(filepath.endsWith("TestCase.xml"))
			{
				result.add(filepath);
			}
		}
		return result;
	}

}
