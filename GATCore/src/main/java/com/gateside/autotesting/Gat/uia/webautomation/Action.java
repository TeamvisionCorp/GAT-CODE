package com.gateside.autotesting.Gat.uia.webautomation;

import java.lang.reflect.Method;

import com.gateside.autotesting.Gat.uia.webautomation.webcontrols.WebUIControll;
import com.gateside.autotesting.Lib.common.ClassReflector;
import com.gateside.autotesting.Lib.common.SimpleLogger;

public class Action 
{

	private String  method;
    private WebUIControll controlWrapper;

    /// <summary>
    /// contructor of action
    /// </summary>
    /// <param name="instance">the object has method to call</param>
    /// <param name="methodName">method name to call</param>
    public Action(Object instance,String methodName)
    {
        controlWrapper =(WebUIControll)instance;
        SimpleLogger.logInfo(this.getClass(),controlWrapper.getClass().toString());
        method = methodName;
    }

   /// <summary>
   /// Invok metho without parameter
   /// </summary>
    public void exec() throws Exception
    {
       Object[] parameters={};
       exec(parameters);
    }
    
  /// <summary>
    /// Invok metho without parameter
    /// </summary>
     public void exec(String parameter) throws Exception
     {
    	 Object[] parameters={parameter};
         exec(parameters);
     }

    /// <summary>
    /// Invoke the method with parameters
    /// </summary>
    /// <param name="parameters">The parameters counts should consistent with invoked method</param>
    public void exec(Object[] parameters) throws Exception
    {
    	if(parameters.length==0){parameters=null;}
        Method methodInfo =ClassReflector.getMethod(controlWrapper, method,parameters);
        methodInfo.invoke(controlWrapper, parameters);
    }

   /// <summary>
   /// Get value form web page with parameters
   /// </summary>
   /// <param name="parameters"></param>
   /// <returns></returns>
    public Object getValue(Object[] parameters) throws Exception
    {
    	if(parameters.length==0){parameters=null;}
    	 Method methodInfo =ClassReflector.getMethod(controlWrapper, method,parameters);
         return methodInfo.invoke(controlWrapper, parameters);
    }

   /// <summary>
   /// Get value from web page without parameters
   /// </summary>
   /// <returns></returns>
    public Object getValue() throws Exception
    {
       return getValue(null);
    }
}
