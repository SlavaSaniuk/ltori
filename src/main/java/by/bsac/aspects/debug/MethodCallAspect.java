package by.bsac.aspects.debug;

import by.bsac.annotations.debug.MethodCall;
import by.bsac.aspects.ConfigurableAspects;
import by.bsac.core.AspectsRegistry;
import by.bsac.core.debugging.LoggerLevel;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

/**
 * Aspect for {@link MethodCall} annotation.
 */
@Aspect
public class MethodCallAspect implements ConfigurableAspects {

    //Logger
    private static final Logger LOGGER = LoggerFactory.getLogger("METHOD_CALL_ASPECT_LOG");
    private LoggerLevel logger_level;
    private final AspectsRegistry REGISTRY =  AspectsRegistry.getInstance();

    //Default constructor
    private MethodCallAspect() {}

    //Pointcuts
    @Pointcut("@annotation(by.bsac.annotations.debug.MethodCall) && execution(* *(..))")
    private void methodCallAnnotation() {}

    //Advises
    @Before("methodCallAnnotation()")
    public void logOnMethodCall(JoinPoint jp) {

        //Disable if aspect is not available
        if (!this.isEnabled()) return;

        //log variables
        final String DEFAULT_LOG_MSG = "Program thread [%s] call method [%s] of class [%s]";
        String current_thread_name = Thread.currentThread().getName();
        String caller_method_name = jp.getSignature().getName();
        String declaring_class_name = jp.getSignature().getDeclaringType().getCanonicalName();

        //Final log statement
        String final_log = String.format(DEFAULT_LOG_MSG, current_thread_name, caller_method_name, declaring_class_name);

        //Add logs depends on annotation values
        Method method = ((MethodSignature) jp.getSignature()).getMethod();
        MethodCall annotation = method.getAnnotation(MethodCall.class);

        // ? - Print args
        if (annotation.withArgs()) {
            Parameter[] args = method.getParameters();
            StringBuilder sb = new StringBuilder();
            Arrays.stream(args).forEach(p -> sb.append(String.format("[Parameter: \"%s\" of type [%s]];", p.getName(), p.getParameterizedType().getTypeName())).append(System.lineSeparator()));
            String with_args_log = " with args {" +System.lineSeparator() +sb.toString() +"}";
            final_log += with_args_log;
        }

        // ? - Print return type
        if (annotation.withReturnType()) {
            String return_type_log = String.format(" and return type is [%s]", method.getReturnType().getCanonicalName());
            final_log += return_type_log;
        }

        // ? - Print start time
        if (annotation.withStartTime()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss:SSS");
            String start_time_log = String.format(" at [%s]", formatter.format(LocalTime.now()));
            final_log += start_time_log;
        }

        this.printMessage(final_log +";");
    }

    private void printMessage(String message) {

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

    public void setLoggerLevel(LoggerLevel level) {
        this.logger_level = level;
    }

    @Override
    public void enable() {
        REGISTRY.enableAspect(MethodCallAspect.class);
    }

    @Override
    public void disable() {
        REGISTRY.disableAspect(MethodCallAspect.class);
    }

    @Override
    public boolean isEnabled() {
        return REGISTRY.isEnabled(MethodCallAspect.class);
    }
}
