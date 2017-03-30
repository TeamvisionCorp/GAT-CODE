package com.gateside.autotesting.Lib.unittest;

import org.testng.annotations.Test;
import org.testng.Assert;

import com.gateside.autotesting.Lib.httpclientService.HttpClientHelper;

public class httpclientHelperTest

{

  
  @Test
  public void uploadFile()
  {
	  String filePath = "d:\\developstu.png";
	  String url="http://test.ngalive.178.com/api/upload?tid=7193282&uid=34165544&token=bb4a9afa7fbbe3b9ba718d26f3759edb&app_id=1010&t=1421903022&sign=f45c17bca38e0b6275ab0e73ba4b7057";
	  HttpClientHelper.uploadFile(filePath,url,"filename");
  }
}
