package com.gateside.autotesting.Lib.unittest;

import org.testng.annotations.Test;

import com.gateside.autotesting.Lib.excelservice.*;

public class ExcelReaderTest {
  @Test
  public void f() throws Exception 
  {
	  ExcelReader reader=new ExcelReader();
	  
	  reader.readData("d:\\test.xlsx","test",1);
  }
}
