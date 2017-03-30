package com.gateside.autotesting.Lib.unittest;

import org.testng.annotations.Test;

import com.gateside.autotesting.Lib.common.*;

public class SimpleLoggerTest {
  @Test
  public void SimpleLoggerTest() {
	  System.out.print(System.getProperty("user.dir"));
	  SimpleLogger.logInfo(this.getClass(),"fdsfsdfsdfsdfsdfsd");
  }
}
