package com.designpattern.chainofresponsibility;

import com.designpattern.chainofresponsibility.impl.ConsoleLogger;
import com.designpattern.chainofresponsibility.impl.ErrorLogger;
import com.designpattern.chainofresponsibility.impl.FileLogger;
import org.junit.Test;

public class TestChainofreshponsibility {
    @Test
    public void test1() {
        AbstractLogger logger = getChainOfLoggers();
        logger.logMessage(AbstractLogger.DEBUG, "this is a debug level information.");
    }

    @Test
    public void test2() {
        AbstractLogger logger = getChainOfLoggers();
        logger.logMessage(AbstractLogger.INFO, "this is an information.");
    }

    @Test
    public void test3() {
        AbstractLogger logger = getChainOfLoggers();
        logger.logMessage(AbstractLogger.ERROR, "this is a error level information.");
    }

    public static AbstractLogger getChainOfLoggers() {
        FileLogger fileLogger = new FileLogger(AbstractLogger.DEBUG);
        ConsoleLogger consoleLogger = new ConsoleLogger(AbstractLogger.INFO);
        ErrorLogger errorLogger = new ErrorLogger(AbstractLogger.ERROR);
        fileLogger.setNextLogger(consoleLogger);
        consoleLogger.setNextLogger(errorLogger);
        return fileLogger;
    }
}
