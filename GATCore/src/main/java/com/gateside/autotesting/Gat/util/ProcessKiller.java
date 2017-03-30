package com.gateside.autotesting.Gat.util;

import java.io.IOException;

import org.openqa.selenium.support.FindAll;

public class ProcessKiller 
{
	public static void killProcess(String processName) throws Exception
	{  
		Runtime.getRuntime().exec("taskkill /IM " + processName);
	}
}
