package com.gateside.autotesting.Lib.common;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexHelper
{
	/**
	 * 
	 * @param numberString number String
	 * @return Boolean
	 */
	 public static Boolean isNumber(String numberString)
	    { 
		    if(isSingleChar(numberString)){return false;}
	    	String regEx = "[0-9]*.[0-9]*"; 
	    	Pattern pat = Pattern.compile(regEx);  
	    	Matcher mat = pat.matcher(numberString);  
	    	boolean rs = mat.matches(); 
	        return rs;
	    }
	 
	 /**
	  * 
	  * @param str  String
	  * @return Boolean
	  */
	 public static Boolean isStartWithSpecalChar(String str)
	 {
		 Integer charValue=(int)str.charAt(0);
		 if(charValue>=33 && charValue<=47)
		 {
			 return true;
		 }
		 else
		 {
			 return false;
		 }
	 }
	 
	 /**
	  * 
	  * @param str String
	  * @return boolean
	  */
	 
	 public static Boolean isSingleChar(String str)
	 {
		 Integer charValue=(int)str.charAt(0);
		 if( charValue<48 || charValue>57)
		 {
			 return true;
		 }
		 else
		 {
			 return false;
		 }
	 }
}
