package com.designpattern.chainofresponsibility.impl;

import com.designpattern.chainofresponsibility.AbstractLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by jingbin on 2020-02-03.
 */
public class FileLogger extends AbstractLogger {
    private static Logger log = LoggerFactory.getLogger(FileLogger.class);

    public FileLogger(int level) {
        this.level = level;
    }

    @Override
    protected void write(String message) {
        log.info("--- File::Logger  " + message);
    }
}
