package com.gateside.autotesting.Lib.unittest;

import org.testng.annotations.Test;

import com.gateside.autotesting.Lib.common.RSAEncryptHelper;

public class RSAEncryptHelperTest
{
  @Test
  public void f() 
  {
	 
      byte[] cipher=RSAEncryptHelper.encrypt(RSAEncryptHelper.DEFAULT_PUBLIC_KEY,"123456789");
      System.out.println(cipher.toString());
      System.out.println(RSAEncryptHelper.getBase64Code(cipher));
      String decodeString=RSAEncryptHelper.decrypt(RSAEncryptHelper.DEFAULT_PRIVATE_KEY,cipher);
      System.out.println(decodeString);
  }
}
