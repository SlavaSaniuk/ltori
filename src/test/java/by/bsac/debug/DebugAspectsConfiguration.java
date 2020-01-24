package by.bsac.debug;

import by.bsac.aspects.debug.MethodCallAspect;
import by.bsac.aspects.debug.MethodExecutionTimeAspect;
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
public class DebugAspectsConfiguration {

    //Logger
    private static final Logger LOGGER = LoggerFactory.getLogger(DebugAspectsConfiguration.class);

    @Bean
    public MethodCallAspect getMethodCallAspect() {

        LOGGER.debug(CREATION.startCreateBean(BeanDefinition.of(MethodCallAspect.class)));
        MethodCallAspect aspect = Aspects.aspectOf(MethodCallAspect.class);

        aspect.setLoggerLevel(LoggerLevel.DEBUG);

        LOGGER.debug(CREATION.endCreateBean(BeanDefinition.of(MethodCallAspect.class)));
        return aspect;
    }

    @Bean
    public MethodExecutionTimeAspect getMethodExecutionTimeAspect() {

        LOGGER.debug(CREATION.startCreateBean(BeanDefinition.of(MethodExecutionTimeAspect.class)));
        MethodExecutionTimeAspect aspect = Aspects.aspectOf(MethodExecutionTimeAspect.class);

        aspect.setLoggerLevel(LoggerLevel.DEBUG);

        LOGGER.debug(CREATION.endCreateBean(BeanDefinition.of(MethodExecutionTimeAspect.class)));
        return aspect;
    }
}
