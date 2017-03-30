package com.gateside.autotesting.Lib.common;


import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;


/**   
*    
* @author zhangtiande
* @version  2.0.5
*    
*/
public class ConfigReader 
{
	/**
	 * 
	 * @param configFilePath config file path
	 * @param key config key
	 * @return String
	 */
	public static String GetValue(String configFilePath,String key)
	{
		InputStream stream;
		String value="";
		try
		{
			stream = new FileInputStream(configFilePath);
			Properties logConifgProperties=new Properties();
			logConifgProperties.load(stream);
			value=logConifgProperties.getProperty(key);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return value;
	}

}
