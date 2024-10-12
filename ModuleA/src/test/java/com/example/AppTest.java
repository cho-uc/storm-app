package com.example;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
// import java.util.Map;
// import java.io.Writer;
// import java.io.OutputStreamWriter;
// import java.io.FileOutputStream;
// import java.io.File;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{

    private BufferedWriter writer;
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );

        String text = "Hello world";
        BufferedWriter writer = null;
        String filePath = "/home/hapu/Documents/my-storm-app-local/logs/output.txt";
        try {
            int finalDigit = 1345;
            writer = new BufferedWriter(new FileWriter(filePath));
            writer.write(text);
            writer.newLine();
            writer.write("Second line");
            writer.newLine();
            writer.write(Integer.toString(finalDigit));
            writer.newLine();
        } catch ( IOException e ) {
            e.printStackTrace();
        } finally {
            if ( writer != null ) {
                try {
                    writer.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
        }
        }
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        System.out.println("Unit test from ModuleA");

        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }
}
