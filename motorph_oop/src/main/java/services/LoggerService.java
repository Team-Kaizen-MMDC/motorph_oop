/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author brianjancarlos
 */
// Encapsulation: Centralized logging 
public class LoggerService {

    private static final Logger LOGGER = Logger.getLogger(LoggerService.class.getName());

    // Retrieves the calling class and method name dynamically
    private static String getCallerInfo() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        if (stackTrace.length >= 4) {
            StackTraceElement caller = stackTrace[3]; // Get the actual calling method
            return "[" + caller.getClassName() + "." + caller.getMethodName() + "] ";
        }
        return "[Unknown] ";
    }

    // Logs an INFO message with caller details
    public static void logInfo(String message) {
        LOGGER.log(Level.INFO, getCallerInfo() + message);
    }

    // Logs a WARNING message with caller details
    public static void logWarning(String message) {
        LOGGER.log(Level.WARNING, getCallerInfo() + message);
    }

    // Logs a SEVERE (error) message with caller details
    public static void logError(String message, Exception e) {
        LOGGER.log(Level.SEVERE, getCallerInfo() + message, e);
    }
}
