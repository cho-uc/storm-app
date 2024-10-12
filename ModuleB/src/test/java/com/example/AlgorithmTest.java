package com.example;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import com.example.Algorithm;

/**
 * Unit test for simple App.
 */
public class AlgorithmTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AlgorithmTest( String testName )
    {
        super( testName );
        Algorithm myAlgo = new Algorithm();
        
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AlgorithmTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }
}
