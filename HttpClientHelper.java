//package com.gateside.autotesting.Gat.util;
//
//import java.io.IOException;  
//import java.nio.charset.Charset;  
//  
//import org.apache.commons.logging.Log;  
//import org.apache.commons.logging.LogFactory;  
//import org.apache.http.HttpResponse;  
//import org.apache.http.HttpStatus;  
//import org.apache.http.client.HttpClient;  
//import org.apache.http.client.methods.HttpPost;  
//import org.apache.http.entity.StringEntity;  
//import org.apache.http.impl.client.DefaultHttpClient;  
//import org.apache.http.util.EntityUtils;  
//  
//import com.google.gson.JsonArray;  
//import com.google.gson.JsonObject;  
//  
//public class HttpClientHelper {  
//  
//    // 接口地址  
//    private static String apiURL = "http://localhost:8000/api/ci/auto_testcases";  
//    private Log logger = LogFactory.getLog(this.getClass());  
//    private static final String pattern = "yyyy-MM-dd HH:mm:ss:SSS";  
//    private HttpClient httpClient = null;  
//    private HttpPost method = null;  
//    private long startTime = 0L;  
//    private long endTime = 0L;  
//    private int status = 0;  
//  
//    /** 
//     * 接口地址 
//     *  
//     * @param url 
//     */  
//    public HttpClientHelper(String url) {  
//  
//        if (url != null) {  
//            this.apiURL = url;  
//        }  
//        if (apiURL != null) {  
//            httpClient = new DefaultHttpClient();  
//            method = new HttpPost(apiURL); 
//  
//        }  
//    }  
//  
//    /** 
//     * 调用 API 
//     *  
//     * @param parameters 
//     * @return 
//     */  
//    public String post(String parameters) {  
//        String body = null;  
//        logger.info("parameters:" + parameters);  
//  
//        if (method != null & parameters != null  
//                && !"".equals(parameters.trim())) {  
//            try {  
//  
//
//                method.addHeader("Content-type","application/json; charset=utf-8");  
//                method.setHeader("Accept", "application/json");  
//                method.setEntity(new StringEntity(parameters, Charset.forName("UTF-8")));  
//                startTime = System.currentTimeMillis();  
//  
//                HttpResponse response = httpClient.execute(method);  
//                  
//                endTime = System.currentTimeMillis();  
//                int statusCode = response.getStatusLine().getStatusCode();  
//                  
//                logger.info("statusCode:" + statusCode);  
//                logger.info("调用API 花费时间(单位：毫秒)：" + (endTime - startTime));  
//                if (statusCode != HttpStatus.SC_OK) {  
//                    logger.error("Method failed:" + response.getStatusLine());  
//                    status = 1;  
//                }  
//  
//                // Read the response body  
//                body = EntityUtils.toString(response.getEntity());  
//  
//            } catch (IOException e) {  
// 
//                status = 3;  
//            } finally {  
//                logger.info("调用接口状态：" + status);  
//            }  
//  
//        }  
//        return body;  
//    }  
//  
//    public static void main(String[] args) {  
//        HttpClientHelper ac = new HttpClientHelper(apiURL);
//        String json="{\"PackageName\":\"com.wanmei.mobile.iat.story.steps\",\"ClassName\":\"fdsf\",\"CaseTag\":\"hgfhgf\",\"CaseName\":\"test04LoginPostWrongTicket\",\"CaseType\":1,\"ProjectID\":5,\"ModuleID\":0,\"InterfaceID\":0}";
//        
//        System.out.println(ac.post(json));  
//    }  
//  
//    /** 
//     * 0.成功 1.执行方法失败 2.协议错误 3.网络错误 
//     *  
//     * @return the status 
//     */  
//    public int getStatus() {  
//        return status;  
//    }  
//  
//    /** 
//     * @param status 
//     *            the status to set 
//     */  
//    public void setStatus(int status) {  
//        this.status = status;  
//    }  
//  
//    /** 
//     * @return the startTime 
//     */  
//    public long getStartTime() {  
//        return startTime;  
//    }  
//  
//    /** 
//     * @return the endTime 
//     */  
//    public long getEndTime() {  
//        return endTime;  
//    }  
//}  