package com.gateside.autotesting.Gat.dataobject.uielement;

import java.util.ArrayList;
import java.util.List;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

import com.gateside.autotesting.Gat.dataobject.FrameType;
import com.gateside.autotesting.Gat.dataobject.TestObject;
import com.gateside.autotesting.Gat.dataobject.WindowType;


@Root(name="UIElement")
public class UIElement extends TestObject
{
	@Attribute(name="NodeID",required=true)
	public String NodeID;
	
	@Attribute(name="ControlType",required=true)
	public String ControlType;
	
	@Attribute(name="WindowType",required=false)
	public WindowType WindowType;
	
	@Attribute(name="FrameType",required=false)
	public FrameType FrameType;
	   
	@Attribute(name="ID",required=false)
	public String ID;
	   
	@Attribute(name="Name",required=false)
	public String Name;
	   
	@Attribute(name="XPath",required=false)
	public String XPath;
	
	@Attribute(name="CssSelector",required=false)
	public String CssSelector;
    
	@Attribute(name="InnerText",required=false)
    public String InnerText;
    
	@Attribute(name="LinkText",required=false)
    public String LinkText;
    
	@Attribute(name="TagName",required=false)
    public String TagName;
    
	@Attribute(name="ClassName",required=false)
    public String ClassName;
    
	@Attribute(name="PropertyName",required=false)
    public String PropertyName;
    
	@Attribute(name="PropertyValue",required=false)
    public String PropertyValue;
    
	@Attribute(name="IFrameName",required=false)
    public String IFrameName;
    
	@Attribute(name="IFrameIndex",required=false)
    public String IFrameIndex;
    
	@Attribute(name="WindowName",required=false)
    public String WindowName;
}
