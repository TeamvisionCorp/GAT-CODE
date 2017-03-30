package com.gateside.autotesting.Gat.dataobject.testcase;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

import com.gateside.autotesting.Gat.dataobject.TestObject;

@Root(name="Step")
public class TestStep extends TestObject
{
	@Attribute(name="StepAssembly",required=false)
	public String StepAssembly;
	
	@Attribute(name="StepGroup",required=false)
	public String StepGroup;
	
	@Attribute(name="StepName",required=true)
	public String StepName;
	
	@Attribute(name="StepParameterID",required=false)
	public String StepParameterID;
	
	@Attribute(name="StepParametersFilePath",required=false)
	public String StepParametersFilePath;
	
	@Attribute(name="SetUp",required=false)
	public Boolean SetUp=false;
	
	@Attribute(name="TearDown",required=false)
	public Boolean TearDown=false;
	
	@Attribute(name="For",required=false)
	public Integer For=1;
	
	@Attribute(name="StepModule",required=false)
	public Boolean StepModule=false;
	
	@Attribute(name="StepModuleID",required=false)
	public String StepModuleID="";
	
	@Attribute(name="StepModuleFilePath",required=false)
	public String StepModuleFilePath="";
	
	@Attribute(name="ModuleStepParameters",required=false)
	public String ModuleStepParameters="";
		
	
}
