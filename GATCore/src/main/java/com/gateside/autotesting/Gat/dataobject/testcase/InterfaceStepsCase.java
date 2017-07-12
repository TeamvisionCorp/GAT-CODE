package com.gateside.autotesting.Gat.dataobject.testcase;

import java.util.ArrayList;
import java.util.List;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import com.gateside.autotesting.Gat.dataobject.testcase.InterfaceTestStep;
import com.gateside.autotesting.Gat.dataobject.testcase.StepsCase;


public class InterfaceStepsCase extends StepsCase
{
	@ElementList(name="Steps",required=true,inline=true)
	public List<InterfaceTestStep> Steps=new ArrayList<InterfaceTestStep>();
	
	
	@Attribute(name="InterfaceID",required=false)
	public String InterfaceID="";
}
