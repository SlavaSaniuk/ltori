package by.bsac.core.debugging.beans;

import by.bsac.aspects.debug.MethodCallAspect;
import by.bsac.aspects.debug.MethodExecutionTimeAspect;
import by.bsac.core.debugging.LoggerLevel;
import org.aspectj.lang.Aspects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static by.bsac.core.logging.SpringCommonLogging.*;

@SuppressWarnings({"SpringFacetCodeInspection", "AccessStaticViaInstance"})
@Configuration
public class MethodsDebugger implements BeanFactoryAware, InitializingBean {

    //Logger
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodsDebugger.class);
    //Spring beans
    private ConfigurableBeanFactory beans_factory;
    //Default logger level for aspects
    private LoggerLevel logger_level; //Configurable logger level

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beans_factory = (ConfigurableBeanFactory) beanFactory;
    }

    @Override
    public void afterPropertiesSet() {

        //Check logger level property
        if (this.logger_level == null) {
            LOGGER.debug("LoggerLevel property for [%s] is not set. Use default [%s] logger level.");
            this.logger_level = LoggerLevel.DEBUG;
        }

        //Register beans
        //MethodCallAspect bean
        MethodCallAspect method_call_aspect_bean = this.getMethodCallAspect();
        this.beans_factory.registerSingleton("MethodCallAspect", method_call_aspect_bean);
        //MethodExecutionTimeAspect
        MethodExecutionTimeAspect method_execution_time_aspect = this.getMethodExecutionTimeAspect();
        this.beans_factory.registerSingleton("MethodExecutionTimeAspect", method_execution_time_aspect);
    }

    @Bean(name = "MethodCallAspect")
    public MethodCallAspect getMethodCallAspect() {
        LOGGER.info(CREATION.startCreateBean(BeanDefinition.of("MethodCallAspect").ofClass(MethodCallAspect.class)));
        MethodCallAspect aspect = Aspects.aspectOf(MethodCallAspect.class);
        aspect.setLoggerLevel(this.logger_level);
        LOGGER.info(CREATION.endCreateBean(BeanDefinition.of("MethodCallAspect").ofClass(MethodCallAspect.class)));
        return aspect;
    }

    @Bean(name = "MethodExecutionTimeAspect")
    public MethodExecutionTimeAspect getMethodExecutionTimeAspect() {
        LOGGER.info(CREATION.startCreateBean(BeanDefinition.of("MethodExecutionTimeAspect").ofClass(MethodExecutionTimeAspect.class)));
        MethodExecutionTimeAspect aspect = Aspects.aspectOf(MethodExecutionTimeAspect.class);
        aspect.setLoggerLevel(this.logger_level);
        LOGGER.info(CREATION.endCreateBean(BeanDefinition.of("MethodExecutionTimeAspect").ofClass(MethodExecutionTimeAspect.class)));
        return aspect;
    }

    public void setLoggerLevel(LoggerLevel a_logger_level) {
        this.logger_level = a_logger_level;
    }



}
