package com.mentoring.epam.test.lesson9.utils.log4j;

import org.apache.log4j.Level;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Iurii_Galias on 5/12/15.
 */

@SuppressWarnings("serial")
public class LoggerLevel extends org.apache.log4j.Level {
    private static final Map<Integer, LoggerLevel> LOGGER_LEVELS;

    static {
        LOGGER_LEVELS = Collections
                .synchronizedMap(new HashMap<Integer, LoggerLevel>());
    }

    public final static int HTML_OUTPUT_INT = FATAL_INT -16;

    public final static Level HTML_OUTPUT = new LoggerLevel(HTML_OUTPUT_INT, "HTML_OUTPUT", 0);

    protected LoggerLevel(int level, String levelStr, int syslogEquivalent) {
        super(level, levelStr, syslogEquivalent);
        LOGGER_LEVELS.put(level, this);
    }
}
