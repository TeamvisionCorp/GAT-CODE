package com.baidu.gameqa.unittest;

import java.io.IOException;

import org.testng.annotations.Test;

import cn.gateside.gattmg.util.ProjectUtil;

public class FileDirUnitTest {
  @Test
  public void f() 
  {
	  System.out.println(ProjectUtil.getProjectPath());
  }
  
  @Test
  public void creatTesgngFile() throws IOException
  {
	  ProjectUtil.createTestngXml(null);
  }
}
