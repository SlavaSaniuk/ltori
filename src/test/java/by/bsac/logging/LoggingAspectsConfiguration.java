package by.bsac.logging;

import by.bsac.aspects.logging.BeforeLogAspect;
import by.bsac.core.debugging.LoggerLevel;
import by.bsac.spring.BeansConfiguration;
import org.aspectj.lang.Aspects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import static by.bsac.core.logging.SpringCommonLogging.*;

@Configuration
@Import(BeansConfiguration.class)
public class LoggingAspectsConfiguration {

    //Logger
    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingAspectsConfiguration.class);

    @Bean("BeforeLogAspect")
    public BeforeLogAspect getBeforeLogAspect() {

        LOGGER.debug(CREATION.startCreateBean(BeanDefinition.of(BeforeLogAspect.class)));
        BeforeLogAspect aspect = Aspects.aspectOf(BeforeLogAspect.class);

        aspect.setLoggerLevel(LoggerLevel.DEBUG);
        aspect.enable();

        LOGGER.debug(CREATION.endCreateBean(BeanDefinition.of(BeforeLogAspect.class)));
        return aspect;
    }
}
