package by.bsac.spring;

import by.bsac.aspects.debug.MethodCallAspect;
import by.bsac.aspects.debug.MethodExecutionTimeAspect;
import by.bsac.aspects.validation.ParameterValidationAspect;
import by.bsac.core.debugging.LoggerLevel;
import by.bsac.validation.validators.HelloParameterValidator;
import by.bsac.validation.validators.SimpleValidator;
import org.aspectj.lang.Aspects;
import org.springframework.context.annotation.*;

@Configuration
public class AspectsConfiguration {

    @Bean(name = "debugMethodCallAspect")
    @Profile("DEBUG")
    public MethodCallAspect getDebugMethodCallAspect() {
        MethodCallAspect aspect = Aspects.aspectOf(MethodCallAspect.class);
        aspect.setLoggerLevel(LoggerLevel.INFO);
        return aspect;
    }

    @Bean(name = "debugMethodExecutionTimeAspect")
    @Profile("DEBUG")
    public MethodExecutionTimeAspect getMethodExecutionTimeAspect() {
        MethodExecutionTimeAspect aspect = Aspects.aspectOf(MethodExecutionTimeAspect.class);
        aspect.setLoggerLevel(LoggerLevel.INFO);
        return aspect;
    }

    @Bean(name = "ParameterValidationAspect")
    public ParameterValidationAspect getParameterValidationAspect() {
        ParameterValidationAspect aspect = Aspects.aspectOf(ParameterValidationAspect.class);
        aspect.addValidator(new SimpleValidator());
        aspect.addValidator(new HelloParameterValidator());
        return aspect;
    }

}
