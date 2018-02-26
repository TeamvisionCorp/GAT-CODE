package com.gateside.autotesting.Gat.uia.webautomation;

import org.openqa.selenium.WebElement;

import com.gateside.autotesting.Gat.uia.webautomation.Action;
import com.gateside.autotesting.Gat.uia.webautomation.CheckPoint;
import com.gateside.autotesting.Gat.uia.webautomation.WebBrowser;
import com.gateside.autotesting.Gat.dataobject.uielement.UIElement;
import com.gateside.autotesting.Gat.uia.webautomation.webcontrols.WebUIControll;
import com.gateside.autotesting.Gat.util.GlobalConfig;
import com.gateside.autotesting.Lib.common.ClassReflector;
import com.gateside.autotesting.Lib.common.SimpleLogger;

public class WebControll {

	private WebBrowser webBrowser = null;
    private UIElement uiElementInfo = null;
    private WebUIControll uiControll = null;
    public WebControll(WebBrowser webBrowser, UIElement elementInfo)
    {
        this.webBrowser = webBrowser;
        uiElementInfo = elementInfo;
        uiControll = getUIControll();
    }
    public Action action(String  actionName) throws Exception
    {
        if (uiControll == null) { throw new Exception("Can not found controll by info from "+uiElementInfo.NodeID); }
        return new Action(uiControll, actionName);
    }
    public CheckPoint checkPoint(String CheckPointName) throws Exception
    {
    	if (uiControll == null) { throw new Exception("Can not found controll by info from "+uiElementInfo.NodeID); }
        return new CheckPoint(uiControll, CheckPointName);
    }
	
    public WebElement getBaseElement()
    {
    	return uiControll.getElement();
    }
    
    private WebUIControll getUIControll()
    {
    	Object[] parameters={webBrowser,uiElementInfo};
    	if(!uiElementInfo.ControlType.contains("."))
    	{
    		uiControll= (WebUIControll) ClassReflector.createInstance(GlobalConfig.getWebControllDefaultPackage()+uiElementInfo.ControlType, parameters);
    	}
    	else
    	{
    		uiControll=(WebUIControll) ClassReflector.createInstance(uiElementInfo.ControlType, parameters);
		}
    	return uiControll;
    }

}
 