package com.gateside.autotesting.Gat.unittest;

import org.testng.annotations.Test;

import com.gateside.autotesting.Gat.util.ProcessKiller;

public class ProcessKillerTest 

{
  @Test
  public void f() throws Exception
  {
	  ProcessKiller.killProcess("");
  }
}
