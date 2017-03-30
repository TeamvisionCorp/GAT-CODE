package com.gateside.autotesting.Gat.util;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import bsh.This;

import com.gateside.autotesting.Gat.util.ScreenCapture;
import com.gateside.autotesting.Lib.common.SimpleLogger;

public class ScreenCapture {

   public static void saveScreen(String savedir,String fileName)
   {
	try 
	{
		   Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();  
	       Rectangle screenRectangle = new Rectangle(screenSize);  
	       Robot robot = new Robot();
		   BufferedImage image = robot.createScreenCapture(screenRectangle);  
	       ImageIO.write(image, "png", new File(savedir+"\\"+fileName));
	} catch (Exception e) 
	{
        SimpleLogger.logError(ScreenCapture.class,e);
	}  
     
   }

}
