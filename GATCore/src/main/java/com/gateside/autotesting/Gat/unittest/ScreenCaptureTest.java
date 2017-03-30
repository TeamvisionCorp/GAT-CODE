package com.gateside.autotesting.Gat.unittest;

import org.testng.annotations.Test;

import com.gateside.autotesting.Gat.util.FileHelper;
import com.gateside.autotesting.Gat.util.ScreenCapture;

public class ScreenCaptureTest {
  @Test
  public void f() 
  {
	  FileHelper.createDir("test-output\\ScreenPictures\\Pass");
	  FileHelper.createDir("test-output\\ScreenPictures\\Fail");
	  ScreenCapture.saveScreen("test-output\\ScreenPictures\\Pass","abc.jpg");
	  ScreenCapture.saveScreen("test-output\\ScreenPictures\\Fail","abc.jpg");
  }
}
