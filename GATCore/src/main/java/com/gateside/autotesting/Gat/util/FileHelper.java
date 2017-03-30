package com.gateside.autotesting.Gat.util;

import java.io.File;

public class FileHelper {

	
	public static void createDir(String dirPath)
	{
		File myPath=new File(dirPath);
		if(!myPath.exists())
		{
			myPath.mkdirs();
		}
	}

}
