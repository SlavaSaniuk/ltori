package by.bsac.aspects;

import by.bsac.annotations.MethodCall;
import by.bsac.core.LoggerLevel;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.stream.Stream;

@SuppressWarnings("MissingAspectjAutoproxyInspection")
@Aspect
public class MethodCallAspect {

    //Logger
    private static final Logger LOGGER = LoggerFactory.getLogger("METHOD_CALL_ASPECT_LOG");
    private LoggerLevel logger_level;

    //Default constructor
    private MethodCallAspect() {}

    //Pointcuts
    @Pointcut("@annotation(by.bsac.annotations.MethodCall)")
    private void methodCallAnnotation() {}

    //Advises
    @Before("methodCallAnnotation()")
    public void logOnMethodCall(JoinPoint jp) {

        //log variables
        final String DEFAULT_LOG_MSG = "Program thread [%s] call method [%s] of class [%s];";
        String current_thread_name = Thread.currentThread().getName();
        String caller_method_name = jp.getSignature().getName();
        String declaring_class_name = jp.getSignature().getDeclaringType().getCanonicalName();

        //Add logs depends on annotation values
        Method method = ((MethodSignature) jp.getSignature()).getMethod();
        MethodCall annotation = method.getAnnotation(MethodCall.class);
        // ? - Print args
        if (annotation.withArgs()) {
            Parameter[] args = method.getParameters();
            StringBuilder sb = new StringBuilder();
           // Arrays.stream(args).forEach(p -> sb.append(String.format("[Parameter: \"%s\" of type [&s] with value [%s]];", p.getName(), ) +System.lineSeparator()));

        }


        this.printMessage(String.format(DEFAULT_LOG_MSG, current_thread_name, caller_method_name, declaring_class_name));
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
}
