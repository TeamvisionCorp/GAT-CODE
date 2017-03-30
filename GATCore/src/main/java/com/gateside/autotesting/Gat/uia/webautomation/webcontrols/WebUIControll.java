package com.gateside.autotesting.Gat.uia.webautomation.webcontrols;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.gateside.autotesting.Gat.dataobject.ActionMethod;
import com.gateside.autotesting.Gat.dataobject.CheckPointMethod;
import com.gateside.autotesting.Gat.dataobject.FrameType;
import com.gateside.autotesting.Gat.dataobject.WindowType;
import com.gateside.autotesting.Gat.dataobject.uielement.UIElement;
import com.gateside.autotesting.Gat.uia.webautomation.WebBrowser;

public class WebUIControll
{
	private WebElement baseUIElement = null;
	private WebElement targetElement=null;
    protected WebDriver webBrowserDriver = null;
    protected WebBrowser webBrowser = null;
    protected UIElement elementNode = null;
    
    public WebUIControll(WebBrowser webBrowser, UIElement element)
    {
        webBrowserDriver = webBrowser.getDriver();
        elementNode = element;
        this.webBrowser = webBrowser;
        //SwitchTOWindow();
        switchTOFrame();
    }

    public WebElement getElement()
    {
    	return this.baseUIElement;
    }


    protected WebElement getUIElement(UIElement uiElement)
    {
        targetElement = getElementByExactlyInfor(uiElement);
        if (targetElement == null)
        {
            targetElement = getElementByAttribute(uiElement);
        }
        baseUIElement = targetElement;
        return targetElement;
    }

    private void switchTOWindow()
    {
        if (elementNode.WindowType.equals(WindowType.ChildBrowser))
        {
            String targetWindowHandle = getWindowHandle(elementNode.WindowName);
            if (targetWindowHandle==null || targetWindowHandle=="")
            {
                webBrowserDriver.switchTo().window(webBrowser.getMainBrowserHandle());
            }
            else
            {
                webBrowserDriver.switchTo().window(targetWindowHandle);
            }
        }
        else
        {
            webBrowserDriver.switchTo().window(webBrowser.getMainBrowserHandle());
        }
    }

    private void switchTOFrame()
    {
        if (elementNode.FrameType.equals(FrameType.FramePage))
        {
        	webBrowserDriver.switchTo().defaultContent();
            if (elementNode.IFrameName!="")
            {
                if (elementNode.IFrameName.contains(","))
                {
                    String[] frameNames = elementNode.IFrameName.toString().split(",");
                    for (String frameName : frameNames)
                    {
                        webBrowserDriver.switchTo().frame(frameName);
                    }
                }
                else
                {
                    webBrowserDriver.switchTo().frame(elementNode.IFrameName);
                }
            }
            else if(elementNode.IFrameIndex!="")
            {
                webBrowserDriver.switchTo().frame(elementNode.IFrameIndex);
            }
         }
        else
        {
            webBrowserDriver.switchTo().defaultContent();
        }
    }

    private String getWindowHandle(String windowName)
    {
        String handle ="";
        for (String windowHandle : webBrowserDriver.getWindowHandles())
        {
            this.webBrowserDriver.switchTo().window(windowHandle);
            if (this.webBrowserDriver.getTitle().contains(windowName))
            {
                handle = windowHandle;
                this.webBrowserDriver.switchTo().window(this.webBrowser.getMainBrowserHandle());
                break;
            }
            this.webBrowserDriver.switchTo().window(this.webBrowser.getMainBrowserHandle());
        }
        return handle;
    }

    private WebElement getElementByExactlyInfor(UIElement elementNode)
    {
        WebElement targetElement = null;
        if (elementNode.ID!="")
        {
            targetElement = webBrowserDriver.findElement(By.id(elementNode.ID));
        }
        else if (elementNode.XPath!="")
        {
            targetElement = webBrowserDriver.findElement(By.xpath(elementNode.XPath));
        }
        else if (elementNode.LinkText!="")
        {
            targetElement = webBrowserDriver.findElement(By.linkText(elementNode.LinkText));
        }
        else if (elementNode.CssSelector!="")
        {
            targetElement = webBrowserDriver.findElement(By.cssSelector(elementNode.CssSelector));
        }
        return targetElement;
    }

   

    private WebElement getElementByAttribute(UIElement elementNode)
    {
        return getElementsByAttribute(elementNode).get(0);
    }


    protected List<WebElement> getElementsByAttribute(UIElement elementNode)
    {
        List<WebElement> tempWebElementList = new ArrayList<WebElement>();
        List<WebElement> iwebElementCollection= webBrowserDriver.findElements(By.tagName(elementNode.TagName));
            if (elementNode.ClassName !="")
            {
                for(WebElement item : iwebElementCollection)
                {
                    if (item.getAttribute("class").equals(elementNode.ClassName))
                    {
                        if (elementNode.InnerText!="")
                        {
                            if (item.getText().equals(elementNode.InnerText))
                            {
                                tempWebElementList.add(item);
                            }
                        }
                        else if (elementNode.PropertyName!="")
                        {
                            if (item.getAttribute(elementNode.PropertyName).equals(elementNode.PropertyValue))
                            {
                                tempWebElementList.add(item);
                            }
                        }
                        else { tempWebElementList.add(item); }
                    }
                }
            }
            else if (elementNode.Name!="")
            {
                for(WebElement item : iwebElementCollection)
                {
                    if (item.getAttribute("name").equals(elementNode.Name))
                    {
                        if(elementNode.InnerText!="")
                        {
                            if (item.getText().equals(elementNode.InnerText))
                            {
                                tempWebElementList.add(item);
                            }
                        }
                        else if (elementNode.PropertyName!="")
                        {
                            if (item.getAttribute(elementNode.PropertyName).equals(elementNode.PropertyValue))
                            {
                                tempWebElementList.add(item);
                            }
                        }
                        else { tempWebElementList.add(item); }
                    }
                }
            }
            else if (elementNode.InnerText!="")
            {
                for(WebElement item : iwebElementCollection)
                {
                    if (item.getText().equals(elementNode.InnerText))
                    {
                        tempWebElementList.add(item);
                    }
                }
            }
            else if (elementNode.PropertyName!="")
            {
                for(WebElement item : iwebElementCollection)
                {
                    if (item.getAttribute(elementNode.PropertyName).equals(elementNode.PropertyValue))
                    {
                        tempWebElementList.add(item);
                    }
                }
            }
        return tempWebElementList;
    }	

    
    @ActionMethod
    public List<WebElement> getElementsByXPath(String xpath)
    {
    	return this.webBrowserDriver.findElements(By.xpath(xpath));
    }
    
  /// <summary>
    /// Check control Is Enable
    /// </summary>
    /// <returns></returns>
    @CheckPointMethod
    public Boolean isEnable(){ return targetElement.isEnabled(); }

    /// <summary>
    /// Check control is checked
    /// </summary>
    /// <returns></returns>
    @CheckPointMethod
    public Boolean isChecked(){ return targetElement.isSelected(); }
}
