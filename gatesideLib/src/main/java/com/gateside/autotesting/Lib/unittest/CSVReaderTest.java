package com.gateside.autotesting.Lib.unittest;

import java.nio.charset.Charset;

import org.testng.annotations.Test;

import com.gateside.autotesting.Lib.csvService.*;

public class CSVReaderTest {
  @Test
  public void getColumnCountTest() 
  {
	  CSVReader reader=new CSVReader("d:\\test.csv",',',Charset.defaultCharset());
	  System.out.print(reader.getColumnCount());
  }
}
