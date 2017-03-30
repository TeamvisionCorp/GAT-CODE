package com.gateside.autotesting.Gat.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;







import com.gateside.autotesting.Gat.manager.IManager;
import com.gateside.autotesting.Gat.dataobject.TestObject;
import com.gateside.autotesting.Gat.dataobject.testcase.InterfaceSingleStepCase;
import com.gateside.autotesting.Gat.util.GlobalConfig;
import com.gateside.autotesting.Lib.common.ClassReflector;
import com.gateside.autotesting.Lib.excelservice.ExcelReader;

public class InterfaceSingleStepCaseManager implements IManager
{

	private List<String> headers=new ArrayList<String>();
	
	public TestObject getItem(String ID) throws Exception 
	{
		InterfaceSingleStepCase singleStepCase=geTestCase(ID);
		return singleStepCase;
	}

	public InterfaceSingleStepCase geTestCase(String caseID) throws Exception
	{
		String[] caseIDs=caseID.split("\\.");
		List<String> rowValue=getRow("ID",caseIDs[1],caseIDs[0]);
		List<String> headersList=getHeaders(caseIDs[0]);
		return createCase(rowValue, headersList);
	}
	
	private InterfaceSingleStepCase createCase(List<String> row,List<String> headers) throws Exception
	{
        InterfaceSingleStepCase resultCase=new InterfaceSingleStepCase();
        Map<String,String> urlParameters=new LinkedHashMap<String,String>();
        for(Integer i=0;i<headers.size();i++)
        {
        	String rowValue="";
        	if(i<row.size())
    		{
    		  rowValue=row.get(i);
    		}
        	if(headers.get(i).startsWith(GlobalConfig.getUrlParametersSignal()))
        	{
        	  String tempHeader=headers.get(i).substring(GlobalConfig.getUrlParametersSignal().length());
        	  if(rowValue.equals("$NULL")){rowValue="";}
        	  if(!rowValue.equals("$EMP"))
        	  {
        	     urlParameters.put(tempHeader,rowValue);
        	  }
        	}
        	else if(!headers.get(i).startsWith(GlobalConfig.getDescColumnSignal()))
        	{
        		if(headers.get(i).equals("DomainName"))
        		{
        			if(rowValue.equals("$DYNAMIC"))
        			{
        				rowValue=GlobalConfig.getDomainName();
        			}
        		}
        		ClassReflector.setFiled(resultCase.getClass(),resultCase,headers.get(i),rowValue);
			}
        }
        ClassReflector.setFiled(resultCase.getClass(),resultCase,"UrlParameters",urlParameters);
		return resultCase;
	}
	
    private List<String> getHeaders(String sheetName)
	{
	   ExcelReader reader=new ExcelReader();
	   headers= reader.readData(GlobalConfig.getTestCaseFilePath(),sheetName,0);
	   return headers;
	}
	
    private List<String> getRow(String colunmName,String value,String sheetName)
	{    
		ExcelReader reader=new ExcelReader();
		List<List<String>> allrows=reader.readAllData(GlobalConfig.getTestCaseFilePath(),sheetName);
		List<String> resultRow=null;
		Integer columnIndex=getColumnIndex(colunmName,getHeaders(sheetName));
		for(List<String> row : allrows)
		{
		   resultRow=row;
		   if(row.get(columnIndex).equals(value))
		   {
			   break;
		   }
		}
		return resultRow;
	}
	
	private Integer getColumnIndex(String cloumnName,List<String> headers)
	{
        Integer columnIndex=0;
        for(String item : headers)
        {
             if(item.equals(cloumnName))
             {
            	 break;
             }
             columnIndex++;
        }
        return columnIndex;
	}
    
}
