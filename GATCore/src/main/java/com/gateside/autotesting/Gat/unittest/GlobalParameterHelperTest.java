package com.gateside.autotesting.Gat.unittest;

import org.testng.annotations.Test;

import com.gateside.autotesting.Gat.dataobject.stepparameter.GlobalParameter;
import com.gateside.autotesting.Gat.util.GlobalParameterHelper;

public class GlobalParameterHelperTest
{
  @Test
  public void f() 
  {
	  GlobalParameter parameter=GlobalParameterHelper.getGlobalParameter();
	  System.out.println(parameter.getValue("url"));
  }
}
