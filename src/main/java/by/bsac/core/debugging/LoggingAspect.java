package by.bsac.core.debugging;

import org.slf4j.Logger;

/**
 * Default class for all logging aspect. Class has a {@link Logger} object that's l
 * og depend on {@link AbstractConfigurableLoggingAspect#logger_level} logging level.
 */
public class LoggingAspect {

    private Logger LOGGER;
    protected LoggerLevel logger_level;

    /**
     * Print message log depend on {@link LoggingAspect#logger_level}.
     * @param message - message for log.
     */
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
