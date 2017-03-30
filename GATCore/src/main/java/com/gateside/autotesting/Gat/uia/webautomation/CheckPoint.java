package com.gateside.autotesting.Gat.uia.webautomation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.gateside.autotesting.Gat.uia.webautomation.webcontrols.WebUIControll;
import com.gateside.autotesting.Lib.common.ClassReflector;

public class CheckPoint
{

	private String  method;
    private WebUIControll controlWrapper;

    /// <summary>
    /// contructor of checkpoint
    /// </summary>
    /// <param name="instance">the object has method to call</param>
    /// <param name="methodName">method name to call</param>
    public CheckPoint(Object instance,String methodName)
    {
    	controlWrapper = (WebUIControll)instance;
        method = methodName;
    }

   /// <summary>
   /// Check the control
   /// </summary>
   /// <param name="parameters"></param>
   /// <returns></returns>
    public Boolean Check(Object[] parameters) throws Exception
    {
    	Boolean returnValue=false;
    	Method methodInfo =ClassReflector.getMethod(controlWrapper, method);
        returnValue=(Boolean)methodInfo.invoke(controlWrapper, parameters);
        return returnValue;
    }

   /// <summary>
   /// Comopare data 
   /// </summary>
   /// <param name="parameters"></param>
   /// <returns></returns>
    public String Compare(Object[] parameters) throws Exception
    {
        Method methodInfo = ClassReflector.getMethod(controlWrapper, method);
        String returnValue = (String)methodInfo.invoke(controlWrapper, parameters);
        return returnValue;
    }
}
