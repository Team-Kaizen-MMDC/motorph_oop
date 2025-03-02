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

    // Logs an INFO message
    public static void logInfo(String message) {
        LOGGER.log(Level.INFO, message);
    }

    // Logs a WARNING message
    public static void logWarning(String message) {
        LOGGER.log(Level.WARNING, message);
    }

    // Logs a SEVERE (error) message
    public static void logError(String message, Exception e) {
        LOGGER.log(Level.SEVERE, message, e);
    }
}
