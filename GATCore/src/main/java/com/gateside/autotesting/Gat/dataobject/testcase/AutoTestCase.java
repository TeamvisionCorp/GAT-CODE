package com.gateside.autotesting.Gat.dataobject.testcase;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;

import com.google.gson.Gson;

public class AutoTestCase {
	
	public Integer id;
	
	public String PackageName;
	
	public String ClassName;
	
	public String CaseName;
	
	public Integer CaseType;
	
	public Integer ProjectID;
	
	public Integer ModuleID;
	
	public Integer InterfaceID;
	public Integer CaseGroupID;
	
	public String CaseTag;
	
	public Integer Version;
	
	public String Desc;
	
	public String TestCaseKey="";
	
	public Boolean IsActive=true;
	
	
	public String toJson()
	{
		Gson gsonObject=new Gson();
		return gsonObject.toJson(this);
	}

}
