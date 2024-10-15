package com.coindcx.util;

import java.util.logging.Logger;

public class LoggerUtil {
    private static final Logger logger = Logger.getLogger(LoggerUtil.class.getName());

    public static void logError(String message) {
        logger.severe(message);
    }

    public static void logAction(String message) {
        logger.info(message);
    }
}
