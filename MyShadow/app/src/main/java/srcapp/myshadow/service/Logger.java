/**
 * Copyright © 2020 - 2021 by RAS Technologies  All rights reserved.
 * Classes for working with android log
 * @author  Terminator
 * @version 210428_01
 */
package srcapp.myshadow.service;

import android.util.Log;

/**
 * Class for creating log messages into different logs (error, debug, info)
 * @author  Terminator
 * @version  210428_01
 * @since    JDK 1.8
 */
public class Logger {
    /** Name io log tag */
    private static final String TAG = "MyShadowApp";
    private String className;
    private final boolean debugMode = true;

    /**
     * Writing into classname name of clazz
     * @param clazz -- class name for writing protocol
     */
    public Logger(Class clazz) {
        this.className = clazz.getName();
    }

    /**
     * Writing a text message to debug log
     * @param text -- message to log
     */
    public void Debug(String text){
        if (debugMode){
            Log.d(TAG,className + " " + text);
        }
    }

    /**
     * Writing a text message to info log
     * @param text -- message to log
     */
    public void Info(String text){
        Log.i(TAG,className + " " + text);
    }

    /**
     * Writing a text message to error log
     * @param text -- message to log
     */
    public void Error(String text){
        Log.e(TAG,className + " " + text);
    }
}
