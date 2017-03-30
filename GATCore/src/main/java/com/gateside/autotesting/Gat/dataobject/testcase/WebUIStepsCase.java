package com.gateside.autotesting.Gat.dataobject.testcase;

import java.util.ArrayList;
import java.util.List;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;

import com.gateside.autotesting.Gat.dataobject.testcase.StepsCase;
import com.gateside.autotesting.Gat.dataobject.testcase.WebUITestStep;


public class WebUIStepsCase extends StepsCase
{
	@ElementList(name="Steps",required=true,inline=true)
	public List<WebUITestStep> Steps=new ArrayList<WebUITestStep>();
	
	@Attribute(name="UIElementsFilePath",required=false)
	public String UIElementsFilePath;
}
