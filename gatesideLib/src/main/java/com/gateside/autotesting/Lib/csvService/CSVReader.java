package com.gateside.autotesting.Lib.csvService;




import java.nio.charset.Charset;
import java.util.ArrayList;

import com.csvreader.*;
import com.gateside.autotesting.Lib.common.*;


/**   
*    
* @author zhangtiande
* @version 2.0.5
*    
*/
public class CSVReader
{
	private String filePath;
	private char delimiter;
	private Charset charset=null;
	
    /**
     * 
     * @param filePath  CVS File path
     * @param delimiter  delimiter char
     * @param charset  char set
     */
	public CSVReader(String filePath,char delimiter,Charset charset)
	{
		this.filePath=filePath;
		this.delimiter=delimiter;
		this.charset=charset;
	}
	
	/**
	 * 
	 * @return String[]
	 */
	public String[] getHeaders()
	{
		CsvReader csvReader=getReader();
		String[] headers=null;
		try 
		{
			csvReader.readHeaders();
			headers=csvReader.getHeaders();
			csvReader.close();
		}
		catch (Exception e)
		{
			csvReader.close();
			SimpleLogger.logError(e);
		}
		return headers;
	}
	
	/**
	 * 
	 * @return  ArrayList(String)
	 */
	public ArrayList<String[]> getAllValues()
	{
		ArrayList<String[]> csvList = new ArrayList<String[]>();
		CsvReader csvReader=getReader();
		try 
		{
			csvReader.readHeaders();
			while(csvReader.readRecord())
			{ 
	            csvList.add(csvReader.getValues());
	        }            
			csvReader.close();
		}
		catch (Exception e)
		{
			csvReader.close();
			SimpleLogger.logError(e);
		}
		return csvList;
	}

	/**
	 * 
	 * @return column counts
	 */
	public Integer getColumnCount()
	{
		return this.getHeaders().length;
	}
	
	/**
	 * 
	 * @return CsvReader
	 */
	private CsvReader getReader() 
	{
		CsvReader csvReader=null;
		try 
		{
			csvReader=new CsvReader(filePath,delimiter,charset);
		}
		catch (Exception e)
		{
			SimpleLogger.logError(e);
		}
		return csvReader;
	}
}