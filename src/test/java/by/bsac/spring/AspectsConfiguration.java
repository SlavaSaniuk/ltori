package by.bsac.spring;

import by.bsac.aspects.MethodCallAspect;
import by.bsac.aspects.MethodExecutionTimeAspect;
import by.bsac.core.LoggerLevel;
import org.aspectj.lang.Aspects;
import org.springframework.context.annotation.*;

@Configuration
public class AspectsConfiguration {

    @Bean(name = "debugMethodCallAspect")
    @Profile("DEBUG")
    public MethodCallAspect getDebugMethodCallAspect() {
        MethodCallAspect aspect = Aspects.aspectOf(MethodCallAspect.class);
        aspect.setLoggerLevel(LoggerLevel.DEBUG);
        return aspect;
    }

    @Bean(name = "debugMethodExecutionTimeAspect")
    @Profile("DEBUG")
    public MethodExecutionTimeAspect getMethodExecutionTimeAspect() {
        MethodExecutionTimeAspect aspect = Aspects.aspectOf(MethodExecutionTimeAspect.class);
        aspect.setLoggerLevel(LoggerLevel.DEBUG);
        return aspect;
    }

}
