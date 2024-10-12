package com.example;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;


public class Algorithm
{
    private static final Logger logger = LogManager.getLogger(Algorithm.class);

    public Algorithm() {
        System.out.println("Creating ModuleB:Algorithm...");
        logger.debug("Debug Message Logged !");
        logger.info("Info Message Logged !");
        // logger.error("Error Message Logged !", new NullPointerException("NullError"));

    }

    public double getResult(){
        return 0.7292;
    }
}
