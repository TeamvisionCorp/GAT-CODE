package com.gateside.autotesting.Lib;

import java.io.IOException;

import org.testng.annotations.Test;

import com.gateside.autotesting.Lib.httpclientService.HttpClientHelper;

public class HttpClientTest {
	
  public void postJsonTest() throws Exception {
	  String apiURL = "http://localhost:8000/api/ci/auto_testcases"; 
	  String json="{\"PackageName\":\"com.wanmei.mobile.iat.story.steps\",\"ClassName\":\"fdsf\",\"CaseTag\":\"hgfhgf\",\"CaseName\":\"test04LoginPostWrongTicket\",\"CaseType\":1,\"ProjectID\":5,\"ModuleID\":0,\"InterfaceID\":0}";
	  System.out.println(HttpClientHelper.getResponseText(HttpClientHelper.postJson(apiURL, json)));
  }
}
