package com.gateside.autotesting.Gat.util;

import java.util.HashMap;

import com.gateside.autotesting.Gat.util.StepValuePool;




public class StepValueHelper {

	public StepValueHelper() {
		// TODO Auto-generated constructor stub
	}
	
	/// <summary>
    /// Get value from componetsOutPutValue
    /// </summary>
    /// <param name="key"></param>
    /// <returns></returns>
    public static  Object getStepOutputValue(String key)
    {
        StepValuePool outPutValues = StepValuePool.createInstance();
        if (outPutValues.getValueDic().containsKey(key))
        {
            return outPutValues.getValueDic().get(key);
        }
        else
        {
            return null;
        }
    }
    
  /// <summary>
    /// Get string value from componetsOutPutValue
    /// </summary>
    /// <param name="key"></param>
    /// <returns></returns>
    public static  String getStepOutputStringValue(String key)
    {
        StepValuePool outPutValues = StepValuePool.createInstance();
        if (outPutValues.getValueDic().containsKey(key))
        {
            return  outPutValues.getValueDic().get(key).toString();
        }
        else
        {
            return null;
        }
    }
    
    
  /// <summary>
    /// Get int value from componetsOutPutValue
    /// </summary>
    /// <param name="key"></param>
    /// <returns></returns>
    public static  Integer getStepOutputIntegerValue(String key)
    {
        StepValuePool outPutValues = StepValuePool.createInstance();
        if (outPutValues.getValueDic().containsKey(key))
        {
            return Integer.valueOf(outPutValues.getValueDic().get(key).toString());
        }
        else
        {
            return null;
        }
    }
    
  /// <summary>
    /// Get int value from componetsOutPutValue
    /// </summary>
    /// <param name="key"></param>
    /// <returns></returns>
    public static  Boolean getStepOutputBooleanValue(String key)
    {
        StepValuePool outPutValues = StepValuePool.createInstance();
        if (outPutValues.getValueDic().containsKey(key))
        {
            return Boolean.valueOf(outPutValues.getValueDic().get(key).toString());
        }
        else
        {
            return null;
        }
    }
    
    public static Boolean putStepOutputValue(String key,Object value)
    {
    	StepValuePool outPutValues = StepValuePool.createInstance();
        if (outPutValues.getValueDic().containsKey(key))
        {
            return false;
        }
        else
        {
            outPutValues.getValueDic().put(key, value);
            return true;
        }
    }
    
    public static Boolean overrideStepValue(String key,Object value)
    {
    	StepValuePool outPutValues = StepValuePool.createInstance();
        if (outPutValues.getValueDic().containsKey(key))
        {
        	outPutValues.getValueDic().remove(key);
        	outPutValues.getValueDic().put(key, value);
        }
        else
        {
            outPutValues.getValueDic().put(key, value);
        }
        return true;
    }
    
    public static HashMap<String, Object> getAllKeyValues()
    {
    	StepValuePool outPutValues = StepValuePool.createInstance();
    	return outPutValues.getValueDic();
    }
    
  /// <summary>
    /// 
    /// </summary>
    /// <param name="key"></param>
     public static void RemoveStepOutputValue(String key)
     {
    	 StepValuePool outPutValues = StepValuePool.createInstance();
         if (outPutValues.getValueDic().containsKey(key))
         {
             outPutValues.getValueDic().remove(key);
         }
     }

    /// <summary>
    /// 
    /// </summary>
    /// <param name="key"></param>
    /// <param name="value"></param>
     public static void UpdateStepOutputValue(String key,Object value)
     {
    	 StepValuePool outPutValues = StepValuePool.createInstance();
         if (outPutValues.getValueDic().containsKey(key))
         {
             outPutValues.getValueDic().remove(key);
             putStepOutputValue(key, value);
         }
     }
     
     

}
