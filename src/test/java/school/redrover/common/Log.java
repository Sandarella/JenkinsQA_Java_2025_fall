package school.redrover.common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log {

    private static final Logger logger = LogManager.getLogger();

    public static void info(String message) {
        logger.info(message);
    }

    public static void info(String format, Object... args) {
        logger.info(String.format(format, args));
    }

    public static void error(String message) {
        logger.error(message);
    }

    public static void error(String message, Throwable e) {
        logger.error(message, e);
    }
}
