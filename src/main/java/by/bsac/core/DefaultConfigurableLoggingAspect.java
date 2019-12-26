package by.bsac.core;

import org.slf4j.Logger;
public class DefaultConfigurableLoggingAspect extends AbstractConfigurableLoggingAspect {


    public DefaultConfigurableLoggingAspect(Logger LOGGER) {
        super();
        super.setLogger(LOGGER);
    }

    @Override
    public void setLoggerLevel(LoggerLevel a_level) {
        super.logger_level = a_level;
    }

}
