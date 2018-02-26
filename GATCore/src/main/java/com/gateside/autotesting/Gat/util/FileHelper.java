package com.gateside.autotesting.Gat.util;

import java.io.File;
import java.util.List;

import com.gateside.autotesting.Lib.common.SimpleLogger;


public class FileHelper {

	
	public static void createDir(String dirPath)
	{
		File myPath=new File(dirPath);
		if(!myPath.exists())
		{
			myPath.mkdirs();
		}
	}
	
	public static void traverseFolder(String path,List<String> filePathes) {
		try
		{
			File file = new File(path);
	        if (file.exists()) {
	            File[] files = file.listFiles();
	            if (files.length == 0) {
	                return;
	            } 
	            else 
	            {
	                for (File childFile : files) {
	                    if (childFile.isDirectory()) {
	                        traverseFolder(childFile.getAbsolutePath(),filePathes);
	                    } else 
	                    {
	                    	filePathes.add(childFile.getAbsolutePath());
	                    }
	                }
	            }
	        }
			
		}
		catch(Exception ex)
		{
			SimpleLogger.logError(FileHelper.class,ex);
		}
        
    }

}
