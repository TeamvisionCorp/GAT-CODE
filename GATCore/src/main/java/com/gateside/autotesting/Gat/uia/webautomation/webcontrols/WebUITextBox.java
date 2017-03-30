package com.gateside.autotesting.Gat.uia.webautomation.webcontrols;

import org.openqa.selenium.WebElement;

import com.gateside.autotesting.Gat.uia.webautomation.webcontrols.WebUIControll;
import com.gateside.autotesting.Gat.dataobject.ActionMethod;
import com.gateside.autotesting.Gat.dataobject.uielement.UIElement;
import com.gateside.autotesting.Gat.uia.webautomation.WebBrowser;

public class WebUITextBox extends WebUIControll
{
	private WebElement textBox = null;
    public  WebUITextBox(WebBrowser webBrowser, UIElement element) 
    {
       super(webBrowser, element);
       textBox= getUIElement(elementNode);
    }
    
    /// <summary>
    /// Click text box
    /// </summary>
    @ActionMethod
    public void click() { textBox.click(); }

    /// <summary>
    /// input text to text box
    /// </summary>
    /// <param name="text"></param>
    @ActionMethod
    public void inputText(String text) 
    {
        textBox.clear();
        textBox.sendKeys(text); 
    }

    /// <summary>
    /// Clear text box
    /// </summary>
    /// <param name="text"></param>
    @ActionMethod
    public void Clear()
    {
        textBox.clear();
    }


    /// <summary>
    /// Get text
    /// </summary>
    /// <returns></returns>
    @ActionMethod
    public String getText() { return textBox.getText();}

    /// <summary>
    /// Get text that is the value attribute
    /// </summary>
    /// <returns></returns>
    @ActionMethod
    public String getValue()
    {
        return textBox.getAttribute("value"); 
    }
}
