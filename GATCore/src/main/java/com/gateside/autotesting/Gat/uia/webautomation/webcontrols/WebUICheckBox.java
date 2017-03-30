package com.gateside.autotesting.Gat.uia.webautomation.webcontrols;

import org.openqa.selenium.WebElement;

import com.gateside.autotesting.Gat.uia.webautomation.webcontrols.WebUIControll;
import com.gateside.autotesting.Gat.dataobject.ActionMethod;
import com.gateside.autotesting.Gat.dataobject.uielement.UIElement;
import com.gateside.autotesting.Gat.uia.webautomation.WebBrowser;

public class WebUICheckBox extends WebUIControll
{
	 private WebElement checkBox = null;
     public WebUICheckBox(WebBrowser webBrowser, UIElement elementNode)
     {
     	super(webBrowser,elementNode);
         checkBox = getUIElement(elementNode);
     }
     
     /// <summary>
     /// Clcik check box
     /// </summary>
     @ActionMethod
     public void click() { checkBox.click(); }
}
