package com.gateside.autotesting.Gat.dataobject.testcase;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

import com.gateside.autotesting.Gat.dataobject.testcase.TestStep;
import com.gateside.autotesting.Gat.dataobject.TestObject;


public class WebUITestStep extends TestStep
{		
	@Attribute(name="UIElementsFilePath",required=false)
	public String UIElementsFilePath;
}
