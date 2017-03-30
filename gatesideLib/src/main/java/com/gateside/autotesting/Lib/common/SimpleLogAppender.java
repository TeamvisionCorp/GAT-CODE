package com.gateside.autotesting.Lib.common;

import org.apache.log4j.DailyRollingFileAppender;
import org.apache.log4j.Priority;

/**   
*    
* @author zhangtiande   
* @version   2.0.5 
*    
*/

public class SimpleLogAppender extends DailyRollingFileAppender
{
  
 @Override
 public boolean isAsSevereAsThreshold(Priority priority)
 {
	 return this.getThreshold().equals(priority);
 }

}
