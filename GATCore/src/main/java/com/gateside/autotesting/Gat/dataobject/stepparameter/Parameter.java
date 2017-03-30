package com.gateside.autotesting.Gat.dataobject.stepparameter;

import org.simpleframework.xml.Attribute;

public class Parameter 
{
	@Attribute(name="Key",required=true)
	public String key="";
	
	@Attribute(name="Value",required=true)
	public String value="";
	
}
