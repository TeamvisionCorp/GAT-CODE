package com.gateside.autotesting.Gat.dataobject.testcase;

import java.util.HashMap;
import java.util.LinkedHashMap;

import com.gateside.autotesting.Gat.dataobject.TestObject;

public class InterfaceSingleStepCase extends TestObject
{
  
  private String ID;
  private String DomainName;
  private String Path;
  private LinkedHashMap<String,String> UrlParameters=new LinkedHashMap<String,String>();
  private String ExpectResult;
  private String SetupType;
  private String TearDownType;
  private String Setup;
  private String TearDown;
  private String AssertType;
  private String AssertMethod;
  private String HttpMethod;
  
/**
 * @return the domainName
 */
public String getDomainName() {
	return DomainName;
}

/**
 * @param domainName the domainName to set
 */
public void setDomainName(String domainName) {
	this.DomainName = domainName;
}

/**
 * @return the path
 */
public String getPath() {
	return Path;
}

/**
 * @param path the path to set
 */
public void setPath(String path) {
	this.Path = path;
}

/**
 * @return the urlParameters
 */
public LinkedHashMap<String,String> getUrlParameters() {
	return UrlParameters;
}

public void updateUrlParameters(String key,String value)
{
    UrlParameters.put(key, value);
}


/**
 * @param urlParameters the urlParameters to set
 */
public void setUrlParameters(LinkedHashMap<String,String> urlParameters) {
	this.UrlParameters = urlParameters;
}

/**
 * @return the expectResult
 */
public String getExpectResult() {
	return ExpectResult;
}

/**
 * @param expectResult the expectResult to set
 */
public void setExpectResult(String expectResult) {
	this.ExpectResult = expectResult;
}

/**
 * @return the assertType
 */
public String getAssertType() {
	return AssertType;
}

/**
 * @param assertType the assertType to set
 */
public void setAssertType(String assertType) {
	this.AssertType = assertType;
}

/**
 * @return the assertMethod
 */
public String getAssertMethod() {
	return AssertMethod;
}

/**
 * @param assertMethod the assertMethod to set
 */
public void setAssertMethod(String assertMethod) {
	this.AssertMethod = assertMethod;
}

/**
 * @return the tearDownType
 */
public String getTearDownType() {
	return TearDownType;
}

/**
 * @param tearDownType the tearDownType to set
 */
public void setTearDownType(String tearDownType) {
	TearDownType = tearDownType;
}

/**
 * @return the setup
 */
public String getSetup() {
	return Setup;
}

/**
 * @param setup the setup to set
 */
public void setSetup(String setup) {
	Setup = setup;
}

/**
 * @return the setupType
 */
public String getSetupType() {
	return SetupType;
}

/**
 * @param setupType the setupType to set
 */
public void setSetupType(String setupType) {
	SetupType = setupType;
}

/**
 * @return the tearDown
 */
public String getTearDown() {
	return TearDown;
}

/**
 * @param tearDown the tearDown to set
 */
public void setTearDown(String tearDown) {
	TearDown = tearDown;
}

/**
 * @return the httpMethod
 */
public String getHttpMethod() {
	return HttpMethod;
}

/**
 * @param httpMethod the httpMethod to set
 */
public void setHttpMethod(String httpMethod) {
	this.HttpMethod = httpMethod;
}

/**
 * @return the iD
 */
public String getID() {
	return ID;
}

/**
 * @param iD the iD to set
 */
public void setID(String iD) {
	ID = iD;
} 
	


}
