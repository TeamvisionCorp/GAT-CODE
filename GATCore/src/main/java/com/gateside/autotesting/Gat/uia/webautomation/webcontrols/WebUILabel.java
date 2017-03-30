package com.gateside.autotesting.Gat.uia.webautomation.webcontrols;

import org.openqa.selenium.WebElement;

import com.gateside.autotesting.Gat.uia.webautomation.webcontrols.WebUIControll;
import com.gateside.autotesting.Gat.dataobject.ActionMethod;
import com.gateside.autotesting.Gat.dataobject.uielement.UIElement;
import com.gateside.autotesting.Gat.uia.webautomation.WebBrowser;

public class WebUILabel extends WebUIControll
{
	 private WebElement  label= null;
     public WebUILabel(WebBrowser webBrowser, UIElement elementNode)
     {
     	super(webBrowser, elementNode);
         label = getUIElement(elementNode);
     }

     /// <summary>
     /// Click label
     /// </summary>
     @ActionMethod
     public void click() { label.click(); }

     /// <summary>
     /// Get label text
     /// </summary>
     /// <returns></returns>
     @ActionMethod
     public String getText() { return label.getText(); }
}
