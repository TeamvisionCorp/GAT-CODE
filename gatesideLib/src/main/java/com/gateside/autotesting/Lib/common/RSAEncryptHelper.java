package com.gateside.autotesting.Lib.common;



import java.io.BufferedReader;  
import java.io.InputStream;  
import java.io.InputStreamReader;  
import java.io.UnsupportedEncodingException; 
import java.security.KeyFactory;  
import java.security.KeyPair;  
import java.security.KeyPairGenerator;  
import java.security.NoSuchAlgorithmException;  
import java.security.SecureRandom;  
import java.security.interfaces.RSAPrivateKey;  
import java.security.interfaces.RSAPublicKey;  
import java.security.spec.PKCS8EncodedKeySpec;  
import java.security.spec.X509EncodedKeySpec;  




import javax.crypto.Cipher;  
import sun.misc.BASE64Decoder;  
import sun.misc.BASE64Encoder;

public class RSAEncryptHelper 
{  
    
  public static final String DEFAULT_PUBLIC_KEY=   
      "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQChDzcjw/rWgFwnxunbKp7/4e8w" + "\r" +  
      "/UmXx2jk6qEEn69t6N2R1i/LmcyDT1xr/T2AHGOiXNQ5V8W4iCaaeNawi7aJaRht" + "\r" +  
      "Vx1uOH/2U378fscEESEG8XDqll0GCfB1/TjKI2aitVSzXOtRs8kYgGU78f7VmDNg" + "\r" +  
      "XIlk3gdhnzh+uoEQywIDAQAB" + "\r";  
    
  public static final String DEFAULT_PRIVATE_KEY=  
      "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAKEPNyPD+taAXCfG" + "\r" +  
      "6dsqnv/h7zD9SZfHaOTqoQSfr23o3ZHWL8uZzINPXGv9PYAcY6Jc1DlXxbiIJpp4" + "\r" +  
      "1rCLtolpGG1XHW44f/ZTfvx+xwQRIQbxcOqWXQYJ8HX9OMojZqK1VLNc61GzyRiA" + "\r" +  
      "ZTvx/tWYM2BciWTeB2GfOH66gRDLAgMBAAECgYBp4qTvoJKynuT3SbDJY/XwaEtm" + "\r" +  
      "u768SF9P0GlXrtwYuDWjAVue0VhBI9WxMWZTaVafkcP8hxX4QZqPh84td0zjcq3j" + "\r" +  
      "DLOegAFJkIorGzq5FyK7ydBoU1TLjFV459c8dTZMTu+LgsOTD11/V/Jr4NJxIudo" + "\r" +  
      "MBQ3c4cHmOoYv4uzkQJBANR+7Fc3e6oZgqTOesqPSPqljbsdF9E4x4eDFuOecCkJ" + "\r" +  
      "DvVLOOoAzvtHfAiUp+H3fk4hXRpALiNBEHiIdhIuX2UCQQDCCHiPHFd4gC58yyCM" + "\r" +  
      "6Leqkmoa+6YpfRb3oxykLBXcWx7DtbX+ayKy5OQmnkEG+MW8XB8wAdiUl0/tb6cQ" + "\r" +  
      "FaRvAkBhvP94Hk0DMDinFVHlWYJ3xy4pongSA8vCyMj+aSGtvjzjFnZXK4gIjBjA" + "\r" +  
      "2Z9ekDfIOBBawqp2DLdGuX2VXz8BAkByMuIh+KBSv76cnEDwLhfLQJlKgEnvqTvX" + "\r" +  
      "TB0TUw8avlaBAXW34/5sI+NUB1hmbgyTK/T/IFcEPXpBWLGO+e3pAkAGWLpnH0Zh" + "\r" +  
      "Fae7oAqkMAd3xCNY6ec180tAe57hZ6kS+SYLKwb4gGzYaCxc22vMtYksXHtUeamo" + "\r" +  
      "1NMLzI2ZfUoX" + "\r";  

 
  private RSAPrivateKey privateKey;  


 
  private RSAPublicKey publicKey;  

  private static final char[] HEX_CHAR= {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};  
    


  public RSAPrivateKey getPrivateKey()
  {  
      return privateKey;  
  }  

  
  public RSAPublicKey getPublicKey() 
  {  
      return publicKey;  
  }  


  public void genKeyPair()
  {  
      KeyPairGenerator keyPairGen= null;  
      try {  
          keyPairGen= KeyPairGenerator.getInstance("RSA");  
      } catch (NoSuchAlgorithmException e) {  
          e.printStackTrace();  
      }  
      keyPairGen.initialize(1024, new SecureRandom());  
      KeyPair keyPair= keyPairGen.generateKeyPair();  
      this.privateKey= (RSAPrivateKey) keyPair.getPrivate();  
      this.publicKey= (RSAPublicKey) keyPair.getPublic();  
  }  
  
  
  public static String getStringFromBase64(String base64Code) {  
      byte[] b = null;  
      String result = null;  
      if (base64Code != null) {  
          BASE64Decoder decoder = new BASE64Decoder();  
          try 
          {  
              b = decoder.decodeBuffer(base64Code);  
              result = new String(b, "utf-8");  
          } 
          catch (Exception e) 
          {  
             SimpleLogger.logError(RSAEncryptHelper.class,e);
          }  
      }  
      return result;  
  } 
  
  
  
  public static String getBase64Code(String str)
  {  
      byte[] b = null;  
      String s = null;  
      try 
      {  
          b = str.getBytes("utf-8");  
      } catch (UnsupportedEncodingException e) 
      {  
    	  SimpleLogger.logError(RSAEncryptHelper.class,e);
      }  
      if (b != null)
      {  
          s = new BASE64Encoder().encode(b);  
      }  
      return s;  
  }  
  
  public static String getBase64Code(byte[] bytearray)
  {
	  String base64code="";
      if (bytearray!= null)
      {  
    	  base64code= new BASE64Encoder().encode(bytearray);  
      }  
      return base64code;  
  }  
  
  public static byte[] encrypt(InputStream publicKeyStream,String targetCode)
  {
	  RSAEncryptHelper rsaEncrypt= new RSAEncryptHelper();
	  byte[] cipher=null;
	  try
      {  
        rsaEncrypt.loadPublicKey(publicKeyStream);
        cipher = rsaEncrypt.encrypt(rsaEncrypt.getPublicKey(), targetCode.getBytes());
      } 
      catch (Exception e) 
      {  
    	SimpleLogger.logError(RSAEncryptHelper.class,e);
      }
	  return cipher;
  }
  
  public static byte[] encrypt(String publicKeyString,String targetCode)
  {
	  RSAEncryptHelper rsaEncrypt= new RSAEncryptHelper();
	  byte[] cipher=null;
	  try
      {  
        rsaEncrypt.loadPublicKey(publicKeyString);
        cipher = rsaEncrypt.encrypt(rsaEncrypt.getPublicKey(), targetCode.getBytes());
      } 
      catch (Exception e) 
      {  
    	SimpleLogger.logError(RSAEncryptHelper.class,e);
      }
	  return cipher;
  }
  
  
  public static byte[] decrypt(InputStream privateKeyStream,byte[] targetArray)
  {
	  RSAEncryptHelper rsaEncrypt= new RSAEncryptHelper();
	  byte[] cipher=null;
	  try
      {  
        rsaEncrypt.loadPrivateKey(privateKeyStream);
        cipher = rsaEncrypt.decrypt(rsaEncrypt.getPrivateKey(),targetArray);
      } 
      catch (Exception e) 
      {  
    	SimpleLogger.logError(RSAEncryptHelper.class,e);
      }
	  return cipher;
  }
  
  public static String decrypt(String privateKeyString,byte[] targetArray)
  {
	  RSAEncryptHelper rsaEncrypt= new RSAEncryptHelper();
	  String result="";
	  try
      {  
      	rsaEncrypt.loadPrivateKey(privateKeyString);  
        byte[] plainText = rsaEncrypt.decrypt(rsaEncrypt.getPrivateKey(),targetArray);
        result=new String(plainText);
      } 
      catch (Exception e) 
      {  
    	SimpleLogger.logError(RSAEncryptHelper.class,e);
      }
	  return result;
  }


  private void loadPublicKey(InputStream in) throws Exception
  {  
      try {  
          BufferedReader br= new BufferedReader(new InputStreamReader(in));  
          String readLine= null;  
          StringBuilder sb= new StringBuilder();  
          while((readLine= br.readLine())!=null){  
              if(readLine.charAt(0)=='-'){  
                  continue;  
              }else{  
                  sb.append(readLine);  
                  sb.append('\r');  
              }  
          }  
          loadPublicKey(sb.toString());  
      } 
      catch (Exception e) 
      {  
    	  SimpleLogger.logError(RSAEncryptHelper.class,e); 
      }
  }  


 
  private void loadPublicKey(String publicKeyStr) throws Exception{  
      try 
      {  
          BASE64Decoder base64Decoder= new BASE64Decoder();  
          byte[] buffer= base64Decoder.decodeBuffer(publicKeyStr);  
          KeyFactory keyFactory= KeyFactory.getInstance("RSA");  
          X509EncodedKeySpec keySpec= new X509EncodedKeySpec(buffer);  
          this.publicKey= (RSAPublicKey) keyFactory.generatePublic(keySpec);  
      } 
      catch (Exception e) 
      {  
    	  SimpleLogger.logError(RSAEncryptHelper.class,e); 
      }
  }  


  private void loadPrivateKey(InputStream in) throws Exception
  {  
      try {  
          BufferedReader br= new BufferedReader(new InputStreamReader(in));  
          String readLine= null;  
          StringBuilder sb= new StringBuilder();  
          while((readLine= br.readLine())!=null)
          {  
              if(readLine.charAt(0)=='-')
              {  
                  continue;  
              }
              else
              {  
                  sb.append(readLine);  
                  sb.append('\r');  
              }  
          }  
          loadPrivateKey(sb.toString());  
      } 
      catch (Exception e) 
      {  
    	  SimpleLogger.logError(RSAEncryptHelper.class,e); 
      }
  }  

  private void loadPrivateKey(String privateKeyStr) throws Exception
  {  
      try {  
          BASE64Decoder base64Decoder= new BASE64Decoder();  
          byte[] buffer= base64Decoder.decodeBuffer(privateKeyStr);  
          PKCS8EncodedKeySpec keySpec= new PKCS8EncodedKeySpec(buffer);  
          KeyFactory keyFactory= KeyFactory.getInstance("RSA");  
          this.privateKey= (RSAPrivateKey) keyFactory.generatePrivate(keySpec);  
      } 
      catch (Exception e) 
      {  
    	  SimpleLogger.logError(RSAEncryptHelper.class,e); 
      }
  }  

 
  private byte[] encrypt(RSAPublicKey publicKey, byte[] plainTextData) throws Exception
  {  
      if(publicKey== null)
      {  
          throw new Exception("public key should not be null");  
      }  
      Cipher cipher= null;  
      byte[] output=null;
      try 
      {  
          cipher= Cipher.getInstance("RSA");  
          cipher.init(Cipher.ENCRYPT_MODE, publicKey);  
          output= cipher.doFinal(plainTextData);    
      }
      catch (Exception e) 
      {  
    	  SimpleLogger.logError(RSAEncryptHelper.class,e); 
      }
      return output;
  }  

 
  private byte[] decrypt(RSAPrivateKey privateKey, byte[] cipherData) throws Exception
  {  
      if (privateKey== null)
      {  
          throw new Exception("privateKey should be not null");  
      }  
      Cipher cipher= null; 
      byte[] output=null;
      try 
      {  
          cipher= Cipher.getInstance("RSA");  
          cipher.init(Cipher.DECRYPT_MODE, privateKey);  
          output= cipher.doFinal(cipherData);    
      } 
      catch (Exception e) 
      {  
    	  SimpleLogger.logError(RSAEncryptHelper.class,e); 
      }
      return output;
  }  

    

  private static String byteArrayToString(byte[] data)
  {  
      StringBuilder stringBuilder= new StringBuilder();  
      for (int i=0; i<data.length; i++)
      {  
  
          stringBuilder.append(HEX_CHAR[(data[i] & 0xf0)>>> 4]);  
          
          stringBuilder.append(HEX_CHAR[(data[i] & 0x0f)]);  
          if (i<data.length-1)
          {  
              stringBuilder.append(' ');  
          }  
      }  
      return stringBuilder.toString();  
  }  

  public static void main(String[] args)
  {  
      RSAEncryptHelper rsaEncrypt= new RSAEncryptHelper();   
      byte[] cipher=rsaEncrypt.encrypt(rsaEncrypt.DEFAULT_PUBLIC_KEY,"123456789");
      System.out.println(cipher.toString());
      System.out.println(getBase64Code(cipher));
      String decodeString=rsaEncrypt.decrypt(rsaEncrypt.DEFAULT_PRIVATE_KEY,cipher);
      System.out.println(decodeString);
  } 
  
}  