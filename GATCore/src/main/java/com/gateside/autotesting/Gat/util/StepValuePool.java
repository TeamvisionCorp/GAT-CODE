package com.gateside.autotesting.Gat.util;

import java.util.HashMap;

import com.gateside.autotesting.Gat.util.StepValuePool;

public class StepValuePool 
{

	private static StepValuePool valuePool=null;
	private HashMap<String, Object> valueDic=new HashMap<String,Object>();
	
	private StepValuePool() 
	{
	}
	
	public static StepValuePool createInstance()
	{
       if(valuePool!=null){return valuePool;}
       else
       {
		 valuePool=new StepValuePool();
		 return valuePool;
	   }
	}

	/**
	 * @return the valueDic
	 */
	public HashMap<String, Object> getValueDic() {
		return valueDic;
	}

	/**
	 * @param valueDic the valueDic to set
	 */
	public void setValueDic(HashMap<String, Object> valueDic) {
		this.valueDic = valueDic;
	}
	
    public static void cleanValuePool()
    {
   	    valuePool=null;
    }

}
