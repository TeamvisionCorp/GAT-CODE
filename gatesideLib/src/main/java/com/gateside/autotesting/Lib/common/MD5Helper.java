package com.gateside.autotesting.Lib.common;

import java.security.MessageDigest;

/**   
*    
* @author zhangtiande   
* @version 2.0.5
*    
*/

public class MD5Helper
{
	/**
	 * 
	 * @param str  source string
	 * @return String
	 * @throws Exception exception
	 */
	public static String getMD5Code(String str) throws Exception
    {
        byte [] buf = str.getBytes();
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.update(buf);
        byte [] tmp = md5.digest();
        StringBuilder sb = new StringBuilder();
        for (byte b:tmp) {
            sb.append(Integer.toHexString(b&0xff));
        }
        return sb.toString();
    }
}
