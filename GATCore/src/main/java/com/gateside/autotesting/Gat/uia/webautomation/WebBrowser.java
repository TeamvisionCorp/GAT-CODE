package com.gateside.autotesting.Gat.uia.webautomation;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.TimeUnit;









import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerDriverService;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Keyboard;
import org.openqa.selenium.interactions.Mouse;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.remote.service.DriverService;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.gateside.autotesting.Gat.uia.webautomation.BrowserType;
import com.gateside.autotesting.Gat.uia.webautomation.WebControll;
import com.gateside.autotesting.Gat.uia.webautomation.WebPage;
import com.gateside.autotesting.Lib.common.SimpleLogger;

/**   
*    
* @author zhangtiande 
* @version  2.0.5
*    
*/
public class WebBrowser {

	private String mainBrowserHandle = "";
	private WebDriver webDriver = null;
	private Mouse browserMouse = null;
	private Integer loadPageTimeouts = 180;
	private BrowserType browserType = BrowserType.IE;

	public WebDriver getDriver() {
		return webDriver;
	}

	public String getMainBrowserHandle() {
		return mainBrowserHandle;
	}

	public void setMainBrowserHandle(String handle) {
		this.mainBrowserHandle = handle;
	}

	public BrowserType getBrowserType() {
		return browserType;
	}

	public Mouse getMouse() {
		switch (this.browserType.ordinal()) {
		case 1:
			browserMouse = ((FirefoxDriver) webDriver).getMouse();
			break;
		case 2:
			browserMouse = ((ChromeDriver) webDriver).getMouse();
			break;
		case 0:
			browserMouse = ((InternetExplorerDriver) webDriver).getMouse();
			break;

		}
		return browserMouse;
	}

	public Integer getBrowserCounts() {
		return this.getDriver().getWindowHandles().size();
	}

	public EventFiringWebDriver getEventFiringWebDriver() {
		return new EventFiringWebDriver(this.getDriver());
	}

	/**
	 * 
	 * @param browserType Chrome FireFox IE
	 */
	public WebBrowser(BrowserType browserType) 
	{
		DriverService service=getDriverService(browserType);
		switch (browserType.ordinal()) 
		{
		case 1: 
		{
			webDriver = new FirefoxDriver();
			break;
		}
		case 0:
			webDriver =new InternetExplorerDriver((InternetExplorerDriverService)service);
			break;
		case 2:
			webDriver =new ChromeDriver((ChromeDriverService)service);
			break;
		}
		mainBrowserHandle = this.getDriver().getWindowHandle();
		this.browserType = browserType;
	}

	/**
	 * 
	 * @param browserType  Chrome IE FireFox
	 * @param browserExePath  browser exe path
	 */
	public WebBrowser(BrowserType browserType,String browserExePath) 
	{
		DriverService driverService=getDriverService(browserType);
		switch (browserType.ordinal()) 
		{
		case 1: 
		{
			webDriver = new FirefoxDriver();
			break;
		}
		case 0:
		{
			webDriver=new InternetExplorerDriver((InternetExplorerDriverService)driverService);
			break;
		}
		case 2:
		{
			DesiredCapabilities desiredCapabilities = DesiredCapabilities
					.chrome();
			desiredCapabilities.setCapability("chrome.binary", browserExePath);
			webDriver = new RemoteWebDriver(driverService.getUrl(),
					desiredCapabilities);
			break;
		}
		}
		mainBrowserHandle = this.getDriver().getWindowHandle();
		this.browserType = browserType;
	}

	/**
	 * 
	 * @param destinationURL  testing URL
	 * @param timeOut  wait for timeout
	 */
	public void navigateTo(String destinationURL, Integer timeOut) {
		if (destinationURL == "") {
			throw new IllegalArgumentException("destinationURL");
		}
		SimpleLogger.logInfo(this.getClass(),destinationURL);
		webDriver.navigate().to(destinationURL);
		webDriver.manage().timeouts().implicitlyWait(timeOut, TimeUnit.SECONDS);
	}

	/**
         * 
         */
	public void back() {
		webDriver.navigate().back();
	}

	public void forward() {
		webDriver.navigate().forward();
	}

	public void quit() {
		if (isAlertExists()) 
		{
			this.getDriver().switchTo().alert().accept();
		}
		closeAlertBox(null);
		try {
			this.getDriver().switchTo().window(this.getMainBrowserHandle());
			for (String handle : this.getDriver().getWindowHandles()) {
				this.getDriver().switchTo().window(handle);
				this.getDriver().quit();
			}
			this.getDriver().quit();
		} catch (Exception ex) {
			SimpleLogger.logError(this.getClass(), ex);
		}
	}

	public void executeJavaScript(String javaScriptString) {
		switch (this.browserType.ordinal()) {
		case 1:
			((FirefoxDriver) webDriver).executeScript(javaScriptString);
			break;
		case 2:
			((ChromeDriver) webDriver).executeScript(javaScriptString);
			break;
		case 0:
			((InternetExplorerDriver) webDriver)
					.executeScript(javaScriptString);
			break;

		}
	}

	public Boolean isAlertExists() {
		Boolean isExist = true;
		try {
			Alert alertBox = this.getDriver().switchTo().alert();
			if (alertBox == null) {
				throw new NoAlertPresentException();
			}
		} catch (Exception ex) {
			SimpleLogger.logError(this.getClass(), ex);
			isExist = false;
		}
		return isExist;
	}


	public Boolean isWindowExists(String windowName) {
		Boolean isExist = false;
		for (String windowHandle : this.getDriver().getWindowHandles()) {
			this.getDriver().switchTo().window(windowHandle);
			if (this.getDriver().getTitle().contains(windowName)) {
				isExist = true;
				this.getDriver().switchTo().window(this.mainBrowserHandle);
				break;
			}
			this.getDriver().switchTo().window(this.mainBrowserHandle);
		}
		return isExist;
	}

	public void clearCookie(String cookieName) {
		if (cookieName == "") {
			throw new IllegalArgumentException("cookieName");
		}
		this.getDriver().manage().getCookies()
				.remove(this.getDriver().manage().getCookieNamed(cookieName));
	}

	
	public void clearAllCookies() {
		this.getDriver().manage().getCookies()
				.removeAll(this.getDriver().manage().getCookies());
	}

	// / <summary>
	// / Close all pop windows
	// / </summary>
	public void closeAllPopWindows() {
		for (String windowHandle : this.getDriver().getWindowHandles()) {
			if (windowHandle != this.mainBrowserHandle) {
				this.getDriver().switchTo().window(windowHandle).close();
			}
			this.getDriver().switchTo().window(this.mainBrowserHandle);
		}
	}

	// / <summary>
	// / Close pop window by title
	// / </summary>
	// / <param name="windowTitle"></param>
	public void closePopWindowByTitle(String windowTitle) {
		if (windowTitle == "") {
			throw new IllegalArgumentException("windowTitle");
		}
		for (String windowHandle : this.getDriver().getWindowHandles()) {
			this.getDriver().switchTo().window(windowHandle);
			if (this.getDriver().getTitle().contains(windowTitle)) {
				this.getDriver().close();
				break;
			}
			this.getDriver().switchTo().window(this.mainBrowserHandle);
		}
	}

	// / <summary>
	// / Close pop window by uri stirng
	// / </summary>
	// / <param name="uriString"></param>
	public void closePopWindowByURIString(String uriString) {
		if (uriString == "") {
			throw new IllegalArgumentException("uriString");
		}
		for (String windowHandle : this.getDriver().getWindowHandles()) {
			this.getDriver().switchTo().window(windowHandle);
			if (this.getDriver().getCurrentUrl().contains(uriString)) {
				this.getDriver().close();
				break;
			}
			this.getDriver().switchTo().window(this.mainBrowserHandle);
		}
	}

	/**
	 * 
	 * @param dismiss  dismiss alert box 
	 */
	public void closeAlertBox(Boolean dismiss) {
		try {
			if (isAlertExists()) {
				if (dismiss) {
					this.getDriver().switchTo().alert().dismiss();
				} else {
					this.getDriver().switchTo().alert().accept();
				}
			}
		} catch (Exception ex) {
			SimpleLogger.logError(this.getClass(), ex);
		}
	}

	// / <summary>
	// / Get alert message
	// / </summary>
	// / <returns></returns>
	public String getAlertText() {
		return this.getDriver().switchTo().alert().getText();
	}

	// / <summary>
	// / Swith to target window by title
	// / </summary>
	// / <param name="title"></param>
	public void switchToWindow(String title) {
		Set<String> handles = this.getDriver().getWindowHandles();
		for (String item : handles) {
			this.getDriver().switchTo().window(item);
			if (this.getDriver().getTitle().contains(title)) {
				break;
			} else {
				this.getDriver().switchTo().window(this.mainBrowserHandle);
			}
		}
	}

	public void switchToChildWindow(Integer timeForWait) throws Exception {
		Thread.sleep(timeForWait);
		Set<String> handles = this.getDriver().getWindowHandles();
		for (String item : handles) {
			if (!this.mainBrowserHandle.equals(item)) {
				this.getDriver().switchTo().window(item);
			} else {
				this.getDriver().switchTo().window(this.mainBrowserHandle);
			}
		}
	}

	public void switchToChildWindow(Integer openedbrowserCounts,
			Integer timeForWait) throws Exception {
		Date startTime = new Date();
		Date nowDate = new Date();
		while ((nowDate.getTime() - startTime.getTime()) < timeForWait * 1000) {
			nowDate = new Date();
			if (this.getDriver().getWindowHandles().size() > openedbrowserCounts) {
				break;
			} else {
				Thread.sleep(1000);
			}
		}
		Set<String> handles = this.getDriver().getWindowHandles();
		for (String item : handles) {
			if (!this.mainBrowserHandle.equals(item)) {
				this.getDriver().switchTo().window(item);
			} else {
				this.getDriver().switchTo().window(this.mainBrowserHandle);
			}
		}
	}

	// / <summary>
	// / return current keyboard
	// / </summary>
	public Keyboard getKeyboard() {
		Keyboard keyboard = null;
		switch (this.browserType.ordinal()) {
		case 1:
			keyboard = ((FirefoxDriver) webDriver).getKeyboard();
			break;
		case 2:
			keyboard = ((ChromeDriver) webDriver).getKeyboard();
			break;
		case 0:
			keyboard = ((InternetExplorerDriver) webDriver).getKeyboard();
			break;

		}
		return keyboard;
	}

	// / <summary>
	// / Resize the browser window
	// / </summary>
	public void windowMaximize() {
		if (this.browserType.equals(BrowserType.Chrome)) {
			Dimension scrSize = Toolkit.getDefaultToolkit().getScreenSize();
			String windWidth = String.valueOf(scrSize.getWidth());
			String windHight = String.valueOf(scrSize.getHeight());
			String jsString = "window.resizeTo(" + windWidth + "," + windHight
					+ ");";
			this.executeJavaScript(jsString);
			this.executeJavaScript("window.moveTo(0,0);");
		} else {
			this.webDriver.manage().window().maximize();
		}
	}

	// / <summary>
	// / wait and get the element
	// / </summary>
	// / <param name="webPage"></param>
	// / <param name="elementNode"></param>
	// / <param name="timeOutSeconds"></param>
	// / <returns></returns>
	public WebElement waitAndGetElement(WebPage webPage, String elementNode,
			Integer timeOutSeconds) throws Exception {
		getDriver().manage().timeouts()
				.implicitlyWait(timeOutSeconds, TimeUnit.SECONDS);
		WebControll webControl = webPage.getWebControll(elementNode);
		getDriver().manage().timeouts()
				.implicitlyWait(loadPageTimeouts, TimeUnit.SECONDS);
		if (webControl.getBaseElement() != null) {
			return webControl.getBaseElement();
		} else {
			return null;
		}
	}

	// / <summary>
	// / Scroll the scroll bar to postion which height is you specified.
	// / </summary>
	// / <param name="height"></param>
	public void scrollTo(Integer height) {
		this.executeJavaScript("document.documentElement.scrollTop="
				+ String.valueOf(height) + ";");
	}

	// / <summary>
	// / scroll the scroll bar to element
	// / </summary>
	// / <param name="element"></param>
	public void scrollTo(WebElement element) {
		org.openqa.selenium.Point targetPostion = element.getLocation();
		this.executeJavaScript("document.documentElement.scrollTop="
				+ String.valueOf(targetPostion.y) + ";");
	}

	// / <summary>
	// / scroll the scroll bar to element
	// / </summary>
	// / <param name="element"></param>
	public void scrollTo(WebElement element, Boolean vertical) {
		org.openqa.selenium.Point targetPostion = element.getLocation();
		if (vertical) {
			this.executeJavaScript("document.documentElement.scrollTop="
					+ String.valueOf(targetPostion.y) + ";");
		} else {
			this.executeJavaScript("document.documentElement.scrollLeft="
					+ String.valueOf(targetPostion.x) + ";");
		}
	}

	// / <summary>
	// / refresh web page
	// / </summary>
	public void refresh() {
		// this.ExecuteJavaScript("window.location = window.location.href.replace(\"#\", \"\");");
		webDriver.navigate().refresh();
	}

	// / <summary>
	// / Move cursor to target element
	// / </summary>
	// / <param name="targetElement"></param>
	public void moveToElement(WebElement targetElement) {
		Actions action = new Actions(this.getDriver());
		action.moveToElement(targetElement);
		action.perform();
	}

	// / <summary>
	// / Click mouse at target element
	// / </summary>
	// / <param name="targetElement"></param>
	public void mouseClick(WebElement targetElement) {
		RemoteWebElement element = (RemoteWebElement) targetElement;
		this.getMouse().click(element.getCoordinates());
	}

	public void SetResponseTimeOut(Integer timeOut) {
		webDriver.manage().timeouts().implicitlyWait(timeOut, TimeUnit.SECONDS);
	}

	// / <summary>
	// / Check controll exists
	// / </summary>
	// / <param name="webPage"></param>
	// / <param name="uiElementNodeID"></param>
	// / <returns></returns>
	public Boolean checkControlExists(WebPage webPage, String uiElementNodeID) throws Exception {
		return webPage.getWebControll(uiElementNodeID).getBaseElement() != null;
	}

	// / <summary>
	// / wait for element show/hide
	// / </summary>
	// / <param name="webPage"></param>
	// / <param name="uiElementNodeID"></param>
	// / <param name="timeOut"></param>
	// / <param name="exist">true: show,false:hide</param>
	public void Wait(WebPage webPage, String uiElementNodeID, Integer timeOut,
			Boolean exist) throws Exception {
		Date startTime = new Date();
		Date now = new Date();
		WebElement element = null;
		while (element == null && exist
				&& (now.getTime() - startTime.getTime()) < timeOut * 1000) {
			now = new Date();
			element = webPage.getWebControll(uiElementNodeID).getBaseElement();
			Thread.sleep(1000);
		}
	}

	public void StopLoadPage(WebPage webPage, String uiElementNodeID,
			Integer timeOut) throws Exception {
		Date startTime = new Date();
		Date now = new Date();
		WebElement element = null;
		while ((now.getTime() - startTime.getTime()) < timeOut * 1000) {
			now = new Date();
			element = webPage.getWebControll(uiElementNodeID).getBaseElement();
			if (element != null) {
				webPage.getBrowser()
						.executeJavaScript(
								"window.stop ? window.stop() : document.execCommand(\"Stop\");");
				break;
			}
			Thread.sleep(1000);
		}
	}

	public void WaitForBrowser(Integer expectBrowserCounts, Integer timeOut,
			Integer interval) throws Exception {
		Date startTime = new Date();
		Date now = new Date();
		while (this.getDriver().getWindowHandles().size() != expectBrowserCounts
				&& (now.getTime() - startTime.getTime()) < timeOut * 1000) {
			Thread.sleep(interval);
		}
	}

	/**
	 * 
	 * @param timeOut
	 *            unit:seconds
	 * @param interval
	 *            unit: millisecond
	 * @throws Exception
	 */
	public void WaitForAlertBox(Integer timeOut, Integer interval)
			throws Exception {
		Date startTime = new Date();
		Date now = new Date();
		while ((now.getTime() - startTime.getTime()) < timeOut * 1000) {
			if (isAlertExists()) {
				break;
			}
			Thread.sleep(interval);
		}
	}

	
	private DriverService getDriverService(BrowserType browserType)
	{
		DriverService service=null;
		switch (browserType.ordinal()) 
		{
		case 0:
		{
			System.setProperty("webdriver.ie.driver","IEDriverServer.exe");		
			service=InternetExplorerDriverService.createDefaultService();
			break;
		}
		case 2:
		{
			System.setProperty("webdriver.chrome.driver","chromedriver.exe");
		    service=ChromeDriverService.createDefaultService();
			break;
		}
		}
		return service;
	}
	 
}
