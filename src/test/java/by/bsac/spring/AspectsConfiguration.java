package by.bsac.spring;

import by.bsac.aspects.MethodCallAspect;
import by.bsac.core.LoggerLevel;
import org.aspectj.lang.Aspects;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class AspectsConfiguration {

    @Bean(name = "debugMethodCallAspect")
    @Profile("DEBUG")
    public MethodCallAspect getDebugMethodCallAspect() {
        MethodCallAspect aspect = Aspects.aspectOf(MethodCallAspect.class);
        aspect.setLoggerLevel(LoggerLevel.DEBUG);
        return aspect;
    }

    @Bean(name = "infoMethodCallAspect")
    @Profile("INFO")
    public MethodCallAspect getInfoMethodCallAspect() {
        MethodCallAspect aspect = Aspects.aspectOf(MethodCallAspect.class);
        aspect.setLoggerLevel(LoggerLevel.INFO);
        return aspect;
    }

}
