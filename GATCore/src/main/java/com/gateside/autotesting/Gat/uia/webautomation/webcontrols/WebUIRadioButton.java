package com.gateside.autotesting.Gat.uia.webautomation.webcontrols;

import org.openqa.selenium.WebElement;

import com.gateside.autotesting.Gat.uia.webautomation.webcontrols.WebUIControll;
import com.gateside.autotesting.Gat.dataobject.ActionMethod;
import com.gateside.autotesting.Gat.dataobject.CheckPointMethod;
import com.gateside.autotesting.Gat.dataobject.uielement.UIElement;
import com.gateside.autotesting.Gat.uia.webautomation.WebBrowser;

public class WebUIRadioButton extends WebUIControll
{
	 private WebElement radioButton = null;
     public WebUIRadioButton(WebBrowser webBrowser, UIElement element) 
     {
     	super(webBrowser, element);
        radioButton= getUIElement(elementNode);
     }
    
     /// <summary>
     /// Click radio button
     /// </summary>
     @ActionMethod
     public void select() 
     {
         if (!radioButton.isSelected()) { radioButton.click(); }
     }


     /// <summary>
     /// Check control is checked
     /// </summary>
     /// <returns></returns>
     @CheckPointMethod
     public Boolean IsChecked() { return radioButton.isSelected(); }
}
