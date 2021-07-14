package cn.gateside.gattmg;

import java.io.File;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import com.gateside.autotesting.Gat.util.GlobalConfig;


/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
       String fileName = this.getClass().getResource("").getPath();
       fileName = fileName.substring(0, fileName.lastIndexOf(".")).replace("/","\"+GlobalConfig.getSlash()+\"");
       fileName = fileName.substring(0, fileName.lastIndexOf(".")).replace("\\","\"+GlobalConfig.getSlash()+\"");
       fileName = fileName.substring(1);
       fileName = 	"\"" + fileName + ".xml"+"\""; 
       System.out.println(fileName);
    }
}
