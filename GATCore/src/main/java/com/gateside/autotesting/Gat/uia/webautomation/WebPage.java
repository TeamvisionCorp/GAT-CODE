package com.gateside.autotesting.Gat.uia.webautomation;

import org.openqa.selenium.WebDriver;

import com.gateside.autotesting.Gat.uia.webautomation.WebBrowser;
import com.gateside.autotesting.Gat.uia.webautomation.WebControll;
import com.gateside.autotesting.Gat.dataobject.EnumObjectManager;
import com.gateside.autotesting.Gat.dataobject.stepparameter.InterfaceStepParameter;
import com.gateside.autotesting.Gat.dataobject.uielement.UIElement;
import com.gateside.autotesting.Gat.manager.IManager;
import com.gateside.autotesting.Gat.manager.TestObjectManagerFactory;
import com.gateside.autotesting.Gat.manager.WebUIElementsManager;
import com.gateside.autotesting.Gat.uia.webautomation.webcontrols.WebUIControll;
import com.gateside.autotesting.Gat.util.ParameterChecker;
import com.gateside.autotesting.Gat.util.ParameterHelper;

public class WebPage {
	
	private WebBrowser browser = null;
    public WebPage(WebBrowser currentBrowser) { browser = currentBrowser; }
	
	public WebControll getWebControll(String uielementID) throws Exception
	{
		ParameterChecker.StringParameterCheck("uielementID",uielementID);
		IManager manager=TestObjectManagerFactory.getTestObjectManager(EnumObjectManager.WebUIElementManager);
		UIElement elementNode=(UIElement)manager.getItem(uielementID);
		
        WebControll pageControl = new WebControll(browser, elementNode);
        return pageControl;
	}
	
	public WebBrowser getBrowser()
	{
		return this.browser;
	}

}
