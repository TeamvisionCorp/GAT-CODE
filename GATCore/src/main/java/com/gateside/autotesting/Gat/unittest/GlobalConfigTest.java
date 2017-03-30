package com.gateside.autotesting.Gat.unittest;

import org.testng.annotations.Test;

import com.gateside.autotesting.Gat.util.GlobalConfig;

public class GlobalConfigTest {
  @Test
  public void getrootDirTest() 
  {
	  System.out.println(GlobalConfig.getRootDir());
	  System.out.println(GlobalConfig.getStepMethodJarPath());
	  System.out.println(GlobalConfig.getStepsParameterFilePath());
  }
}
