package com.gateside.autotesting.Gat.dataobject.testcase;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import com.gateside.autotesting.Gat.dataobject.testcase.EnumSetupType;
import com.gateside.autotesting.Gat.dataobject.testcase.EnumTearDownType;
import com.gateside.autotesting.Gat.dataobject.TestObject;

@Root(name="TestCase")
public class StepsCase extends TestObject
{
	@Attribute(name="ID",required=true)
	public String ID;
	
	@Attribute(name="Name",required=true)
	public String Name;
	
	@Attribute(name="StepParametersFilePath",required=false)
	public String StepParametersFilePath;
	
	@Attribute(name="StepAssembly",required=false)
	public String StepAssembly;
	
	@Attribute(name="StepGroup",required=false)
	public String StepGroup;
	
	@Attribute(name="SetupType",required=false)
	public EnumSetupType SetupType;
	   
	@Attribute(name="TearDownType",required=false)
	public EnumTearDownType TearDownType;
	   
	@Attribute(name="Setup",required=false)
	public String Setup;
	   
	@Attribute(name="TearDown",required=false)
	public String TearDown;
	
	@Attribute(name="StepModule",required=false)
	public Boolean StepModule=false;
	
	@Attribute(name="ModuleID",required=false)
	public String ModuleID="";

	
	@Attribute(name="CaseTags",required=false)
	public String CaseTags="";
	
	@Element(name="Desc",required=false)
	public String Desc="";
	
	
	
}
