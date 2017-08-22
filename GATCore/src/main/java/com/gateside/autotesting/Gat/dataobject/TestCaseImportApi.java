package com.gateside.autotesting.Gat.dataobject;

import com.gateside.autotesting.Gat.util.GlobalConfig;

public final class TestCaseImportApi {
	public static String postApi=GlobalConfig.apiHost()+"/api/ci/auto_cases";
	public static String listApi=GlobalConfig.apiHost()+"/api/ci/auto_cases?page_size=10000&ProjectID=";
	public static String putApi=GlobalConfig.apiHost()+"/api/ci/auto_case/";

}
