package com.gateside.autotesting.Gat.uia.webautomation.webcontrols;

import org.openqa.selenium.WebElement;

import com.gateside.autotesting.Gat.uia.webautomation.webcontrols.WebUIControll;
import com.gateside.autotesting.Gat.dataobject.ActionMethod;
import com.gateside.autotesting.Gat.dataobject.uielement.UIElement;
import com.gateside.autotesting.Gat.uia.webautomation.WebBrowser;

public class WebUILink extends WebUIControll
{
	private  WebElement link = null;
    public WebUILink(WebBrowser webBrowser, UIElement elementNode)
    {
    	super(webBrowser, elementNode);
        link =getUIElement(elementNode);
    }
   
    /// <summary>
    /// Click link text
    /// </summary>
    @ActionMethod
    public void click()
    {
        link.click();
    }
    
    /// <summary>
    /// Get Link text
    /// </summary>
    /// <returns></returns>
    @ActionMethod
    public String getText()
    {
        return link.getText();
    }
}
