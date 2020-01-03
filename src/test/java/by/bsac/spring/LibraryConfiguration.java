package by.bsac.spring;

import by.bsac.core.debugging.LoggerLevel;
import by.bsac.core.debugging.beans.MethodsDebugger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static by.bsac.core.logging.SpringCommonLogging.*;

@Configuration
public class LibraryConfiguration {

    //Logger
    private static final Logger LOGGER = LoggerFactory.getLogger(LibraryConfiguration.class);

    @SuppressWarnings("AccessStaticViaInstance")
    @Bean(name = "MethodsDebugger")
    public MethodsDebugger getMethodsDebugger() {
        LOGGER.debug(CREATION.startCreateBean(BeanDefinition.of("MethodsDebugger").ofClass(MethodsDebugger.class)));
        MethodsDebugger debugger = new MethodsDebugger();
        debugger.setLoggerLevel(LoggerLevel.INFO);
        LOGGER.debug(CREATION.endCreateBean(BeanDefinition.of("MethodsDebugger").ofClass(MethodsDebugger.class)));
        return debugger;
    }
}
