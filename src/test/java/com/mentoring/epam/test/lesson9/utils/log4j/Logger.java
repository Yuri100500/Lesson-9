package com.mentoring.epam.test.lesson9.utils.log4j;

import org.apache.commons.lang3.StringEscapeUtils;

/**
 * Created by Iurii_Galias on 5/12/15.
 */
public class Logger {

    private  static  String getHtml3Message(String message){
        return StringEscapeUtils.escapeHtml3(message);
    }

    public static org.apache.log4j.Logger getLogger(){
        return org.apache.log4j.Logger.getRootLogger();
    }

    public static final void fatal(String message){
        getLogger().fatal(getHtml3Message(message));
    }

    public static final void error(String message){
        getLogger().error(getHtml3Message(message));
    }

    public static final void warn(String message){
        getLogger().warn(getHtml3Message(message));
    }

    public static final void info(String message){
        getLogger().info(getHtml3Message(message));
    }

    public static final void debug(String message){
        getLogger().debug(getHtml3Message(message));
    }

    public static final void htmlOutput(String message){
        getLogger().log(LoggerLevel.HTML_OUTPUT,message);
    }

    public static void shutdown(){
        org.apache.log4j.LogManager.shutdown();
    }
}
