package com.gateside.autotesting.Lib.httpclientService;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import bsh.This;

import com.gateside.autotesting.Lib.common.SimpleLogger;

public class HttpClientHelper 
{

   private static  HttpClientContext context = null;
   
   private static CloseableHttpClient httpClient=null;
	
   /**
    * 
    * @param url http url
    * @param isGet boolean  is get method
    * @return String Response Text
    * @throws Exception ex
    */
   public static String getResponseText(String url,Boolean isGet) throws Exception
   {
	   String resultString="";
	   if(isGet)
	   {
		   resultString=getResponseText(getmethod(url));
	   }
	   else
	   {
		   resultString=getResponseText(postmethod(url));
	   }
	   return resultString;
	   
   }
   
 
   public static void cleanContext()
   {
	   HttpClientHelper.context=null;
	   HttpClientHelper.httpClient=null;
   }
   
   /**
    * 
    * @param apiUri  restapi url
    * @param json post json
    * @return response
    * @throws Exception ex
    */
   public static CloseableHttpResponse postJson(String apiUri,String json) throws Exception
   {
	    return HttpClientHelper.postJson(apiUri, json,null);
   }
   
   /**
    * 
    * @param apiUri  restapi url
    * @param json post json
    * @param headers http headers
    * @return response
    * @throws Exception ex
    */
   public static CloseableHttpResponse postJson(String apiUri,String json,Header[] headers) throws Exception
   {
	    HttpClientHelper.createClient();
	    HttpClientHelper.createContext();
	    HttpPost httPost = new HttpPost(apiUri);
	    if(headers!=null)
	    {
	      httPost.setHeaders(headers);
	    }
	    httPost.addHeader("Content-type","application/json; charset=utf-8");  
	    httPost.setHeader("Accept", "application/json");  
	    httPost.setEntity(new StringEntity(json, Charset.forName("UTF-8"))); 
	    CloseableHttpResponse response = httpClient.execute(httPost,context);
	    return response;
   }
   
   /**
    * 
   * @param apiUri  restapi url
    * @param json post json
    * @return response
    * @throws Exception ex
    */
   public static CloseableHttpResponse putJson(String apiUri,String json) throws Exception
   {
	    return HttpClientHelper.putJson(apiUri, json,null);
   }
   
   /**
    * 
    * @param apiUri  restapi url
    * @param json post json
    * @param headers http headers
    * @return response
    * @throws Exception ex
    */
   public static CloseableHttpResponse putJson(String apiUri,String json,Header[] headers) throws Exception
   {
	    HttpClientHelper.createClient();
	    HttpClientHelper.createContext();
	    HttpPut httpPut = new HttpPut(apiUri);
	    if(headers!=null)
	    {
	    	httpPut.setHeaders(headers);
	    }
	    httpPut.addHeader("Content-type","application/json; charset=utf-8");  
	    httpPut.setHeader("Accept", "application/json");  
	    httpPut.setEntity(new StringEntity(json, Charset.forName("UTF-8"))); 
	    CloseableHttpResponse response = httpClient.execute(httpPut,context);
	    return response;
   }
   
   
   /**
    * 
    * @param apiUri  restapi url
    * @param json post json
    * @return response
    * @throws Exception ex
    */
   public static CloseableHttpResponse patchJson(String apiUri,String json) throws Exception
   {
	    return HttpClientHelper.patchJson(apiUri, json,null);
   }
   
   /**
    * 
    * @param apiUri  restapi url
    * @param json post json
    * @param headers http headers
    * @return response
    * @throws Exception ex
    */
   public static CloseableHttpResponse patchJson(String apiUri,String json,Header[] headers) throws Exception
   {
	    HttpClientHelper.createClient();
	    HttpClientHelper.createContext();
	    HttpPatch httpPatch = new HttpPatch(apiUri);
	    if(headers!=null)
	    {
	    	httpPatch.setHeaders(headers);
	    }
	    httpPatch.addHeader("Content-type","application/json; charset=utf-8");  
	    httpPatch.setHeader("Accept", "application/json");  
	    httpPatch.setEntity(new StringEntity(json, Charset.forName("UTF-8"))); 
	    CloseableHttpResponse response = httpClient.execute(httpPatch,context);
	    return response;
   }
   
   /**
    * 
   * @param apiUri  restapi url
    * @param json post json
    * @return response
    * @throws Exception ex
    */
   public static CloseableHttpResponse deleteJson(String apiUri,String json) throws Exception
   {
	    return HttpClientHelper.deleteJson(apiUri, json,null);
   }
   
   
   /**
    * 
    * @param apiUri  restapi url
    * @param json post json
    * @param headers http headers
    * @return response
    * @throws Exception ex
    */
   public static CloseableHttpResponse deleteJson(String apiUri,String json,Header[] headers) throws Exception
   {
	   HttpClientHelper.createClient();
	    HttpClientHelper.createContext();
	    HttpDelete httpDelete = new HttpDelete(apiUri);
	    if(headers!=null)
	    {
	    	httpDelete.setHeaders(headers);
	    }
	    CloseableHttpResponse response = httpClient.execute(httpDelete,context);
	    return response;
   }
   
 
   
   /**
    * 
    * @param apiUri  restapi url
    * @return response
    * @throws Exception ex
    */
   public static CloseableHttpResponse getJson(String apiUri) throws Exception
   {
	    return HttpClientHelper.getJson(apiUri,null);
   }
   
   
   /**
    * 
   * @param apiUri  restapi url
    * @param headers http headers
    * @return response
    * @throws Exception ex
    */
   public static CloseableHttpResponse getJson(String apiUri,Header[] headers) throws Exception
   {
	    HttpClientHelper.createClient();
	    HttpClientHelper.createContext();
	    HttpGet httpGet = new HttpGet(apiUri);
	    if(headers!=null)
	    {
	    	httpGet.setHeaders(headers);
	    }
	    CloseableHttpResponse response = httpClient.execute(httpGet,context);
	    return response;
   }
   
 
   
   
   
   
   
   
   
   
   /**
    * 
    * @return Redirect URI list
    */
   public static List<URI> getRedrectURL()
   {
	   return context.getRedirectLocations();
   }
   
   /**
    * 
    * @param response  CloseableHttpResponse
    * @return String
    * @throws Exception ex
    */
   public static String getResponseText(CloseableHttpResponse response) throws Exception
   {
	    StringBuilder textView=new StringBuilder();
	    BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent())); 
	    String line = "";
	    while ((line = rd.readLine()) != null) 
	    {
	    	textView.append(line);
	    } 
      response.close();
      return textView.toString();
   }
   
   
   /**
    * 
    * @param filePath  upload file path
    * @param url upload url
    * @param fileInputFieldName  Form file name
    * @return String
    */
   public static String uploadFile(String filePath,String url,String fileInputFieldName)
   {
	   String  result="";
	   try 
	   {  
		   HttpClientHelper.createClient();
		   HttpClientHelper.createContext();
		   HttpPost httppost=new HttpPost(url);
           FileBody bin = new FileBody(new File(filePath)); 
           MultipartEntity reqEntity = new MultipartEntity();
           reqEntity.addPart(fileInputFieldName, bin);
           httppost.setEntity(reqEntity);  
           HttpResponse response = httpClient.execute(httppost);     
           int statusCode = response.getStatusLine().getStatusCode();
           HttpEntity resEntity = response.getEntity();  
           result=EntityUtils.toString(resEntity);//
           EntityUtils.consume(resEntity);
       } 
	   catch (Exception e) 
	       {  
               SimpleLogger.logError(HttpClientHelper.class,e);
               result=e.getMessage();
           }
	   finally 
	   {  
		   try {
			httpClient.close();
			httpClient.getConnectionManager().shutdown(); 
			HttpClientHelper.cleanContext();
		} catch (Exception e) {
			SimpleLogger.logError(HttpClientHelper.class,e);
		}
       } 
	   return result;
   }
   
   
   
   
   /**
    * 
    * @param url http url
    * @return  CloseableHttpResponse
    * @throws ClientProtocolException
    * @throws Exception
    */
   private static CloseableHttpResponse getmethod(String url) throws ClientProtocolException, Exception
   {
	    HttpClientHelper.createClient();
	    HttpClientHelper.createContext();
	    HttpGet httpGet = new HttpGet(url);
	    CloseableHttpResponse response = httpClient.execute(httpGet,context);
	    return response;
   }
   
   /**
    * 
    * @param url http Url
    * @return CloseableHttpResponse
    * @throws ClientProtocolException
    * @throws Exception
    */
   private static CloseableHttpResponse postmethod(String url) throws ClientProtocolException, Exception
   {
	    HttpClientHelper.createClient();
	    HttpClientHelper.createContext();
	    HttpPost httPost = new HttpPost(url);
	    CloseableHttpResponse response = httpClient.execute(httPost,context);
	    return response;
   }
   
   
   private static void  createContext()
   {
	   if(HttpClientHelper.context==null)
	   {
		   HttpClientHelper.context=HttpClientContext.create();
	   }
   }
   
   private static void createClient()
   {
	   if(HttpClientHelper.httpClient==null)
	   {
		   HttpClientHelper.httpClient=HttpClients.createDefault();
	   }
   }
   
   
   
}
