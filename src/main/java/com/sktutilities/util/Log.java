package com.sktutilities.util;

//import org.apache.log4j.Logger;
/**
 * Logger - Logging component to log all the messages.
 * The Correct Version of Log.java for logging.CP
 */
public class Log {
    
    //private static Logger instance = Logger.getLogger("Log");;
    
    public static void logInfo(String info) {
        //instance.info(info);
        System.out.println(info);
    }
    public static void logInfo(Object info) {
        // instance.info(info);
        // System.out.println(info);
     }
    public static void logInfo(int info) {
        // instance.info(info);
       //  System.out.println(info);
     }
}