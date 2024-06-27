package com.designpattern.chainofresponsibility.impl;



import com.designpattern.chainofresponsibility.AbstractLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by jingbin on 2020-02-03.
 * 2. 创建扩展了该记录器类的实体类。
 */
public class ConsoleLogger extends AbstractLogger {
    private static Logger log = LoggerFactory.getLogger(ConsoleLogger.class);
    public ConsoleLogger(int level) {
        this.level = level;
    }

    @Override
    protected void write(String message) {
        log.info("--- Standard Console::Logger  " + message);
    }
}
