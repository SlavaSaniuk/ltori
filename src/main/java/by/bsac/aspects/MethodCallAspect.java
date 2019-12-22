package by.bsac.aspects;

import by.bsac.core.LoggerLevel;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
        final String LOG_MSG = "Program thread call method [%s];";
        this.printMessage(String.format(LOG_MSG, jp.getSignature().getName()));
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
