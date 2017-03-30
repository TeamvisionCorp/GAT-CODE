package com.gateside.autotesting.Lib.xmlService;

import java.io.File; 
import java.util.ArrayList;
import java.util.Iterator; 
import java.util.List; 




import org.dom4j.Document; 
import org.dom4j.Element; 
import org.dom4j.Node;
import org.dom4j.io.SAXReader; 

import com.gateside.autotesting.Lib.common.SimpleLogger;

public class XMLParser
{
	
   /**
    * 
    * @param filePath  xml file path
    * @param xpath xpath
    * @param attributeName  attr name
    * @param attributValue attr value
    * @return Element
    */
   public static Element getElementByID(String filePath,String xpath,String attributeName,String attributValue)
	    {
	       Element result=null;
           List<Element> elementList=getElementsByXPath(filePath, xpath);
           for(Element item : elementList)
           {        	   
              if(item.attributeValue(attributeName)!=null)
              {
            	  if( item.attributeValue(attributeName).equals(attributValue))
            	  {
            		  result=item;
                	  break;
            	  }
              }
           }
            return  result;
	    }
   
   /**
    * 
    * @param filePath file path
    * @param xpath xpath
    * @return Element list
    */
   public static List<Element> getElementsByXPath(String filePath,String xpath)
		{
		 List<Element> result=new ArrayList<Element>();
		 Document document=getDocument(filePath);
		 List<Node> nodesList= document.selectNodes(xpath);
		 Iterator<Node> it=nodesList.iterator(); 
		 
	     while(it.hasNext())
	       {  
	          Element elm=(Element)it.next();
	          result.add(elm);
	       }
	     return result;
	     
		}
	 	    
   private static Document getDocument(String filePath)
	{
		Document  document=null;
		try
		{
			SAXReader reader = new SAXReader();  
		    document = reader.read(new File(filePath));
		}
		catch(Exception ex)
		{
			SimpleLogger.logError(ex);
		}
        return document;
	}
	
	

}
