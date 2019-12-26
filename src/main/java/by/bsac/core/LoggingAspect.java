package by.bsac.core;

import org.slf4j.Logger;

public class LoggingAspect {

    private Logger LOGGER;
    protected LoggerLevel logger_level;

    public void printMessage(String message) {

        if (this.logger_level == null) {
            LOGGER.debug(message);
            return;
        }

        switch (this.logger_level) {
            case TRACE: {
                LOGGER.trace(message);
                break;
            }
            case DEBUG: {
                LOGGER.debug(message);
                break;
            }
            case INFO: {
                LOGGER.info(message);
                break;
            }
        }
    }

    protected void setLogger(Logger a_logger) {
        this.LOGGER = a_logger;
    }

}
