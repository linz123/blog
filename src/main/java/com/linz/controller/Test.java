package com.linz.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Test {

//    private static final Logger logger = LogManager.getLogger("Hello World");

    private static Logger logger = LogManager.getLogger(Test.class.getName());
    public static void main(String[] args) {


        logger.info("Log4j Hello world!");
    }
}
