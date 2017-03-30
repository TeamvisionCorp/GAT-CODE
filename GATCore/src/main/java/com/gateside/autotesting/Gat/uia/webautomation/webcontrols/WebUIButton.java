package com.gateside.autotesting.Gat.uia.webautomation.webcontrols;

import org.openqa.selenium.WebElement;

import com.gateside.autotesting.Gat.uia.webautomation.webcontrols.WebUIControll;
import com.gateside.autotesting.Gat.dataobject.ActionMethod;
import com.gateside.autotesting.Gat.dataobject.uielement.UIElement;
import com.gateside.autotesting.Gat.uia.webautomation.WebBrowser;

public class WebUIButton extends WebUIControll
{
  private WebElement button = null;
  
  public WebUIButton(WebBrowser webBrowser,UIElement element) 
  {
		super(webBrowser, element);
		button=getUIElement(element);
  }

  // <summary>
  // Click button
  // </summary>
  @ActionMethod
  public void click()
  {
      button.click();
  }

  // <summary>
  // Get button text
  // </summary>
  // <returns></returns>
  @ActionMethod
  public String GetText()
  {
      return button.getText();
  }

  // <summary>
  // Click button to submit form
  // </summary>
  @ActionMethod
  public void Submit()
  {
      button.submit();
  }
}
