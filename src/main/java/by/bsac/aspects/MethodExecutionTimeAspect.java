package by.bsac.aspects;

import by.bsac.annotations.MethodExecutionTime;
import by.bsac.core.DefaultConfigurableLoggingAspect;
import by.bsac.core.LoggerLevel;
import by.bsac.time.TimeUtilities;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.time.LocalTime;

@SuppressWarnings("MissingAspectjAutoproxyInspection")
@Aspect
public class MethodExecutionTimeAspect{

    //Logger
    private static final Logger LOGGER = LoggerFactory.getLogger("METHOD_EXECUTION_TIME_ASPECT_LOG");
    private static final DefaultConfigurableLoggingAspect EXECUTION_TIME_LOGGER = new DefaultConfigurableLoggingAspect(LOGGER);

    @Pointcut("@annotation(by.bsac.annotations.MethodExecutionTime)")
    public void methodExecutionTimeAnnotation() {}

    @SuppressWarnings("StringConcatenationInsideStringBufferAppend")
    @Around("methodExecutionTimeAnnotation() && by.bsac.aspects.CommonPointcuts.methodExecution()")
    public Object logOnMethodExecutionTime(ProceedingJoinPoint pjp) throws Throwable {

        Method method = ((MethodSignature) pjp.getSignature()).getMethod();
        EXECUTION_TIME_LOGGER.printMessage(String.format("Start of measure execution time of method [%s] of class [%s]",
                method.getName(), method.getDeclaringClass().getCanonicalName()));

        LocalTime start_time = LocalTime.now();
        Object method_result = pjp.proceed();
        long execution_time = TimeUtilities.timeBetweenInNanos(start_time, LocalTime.now());

        MethodExecutionTime annotation = method.getAnnotation(MethodExecutionTime.class);
        StringBuilder sb = new StringBuilder();

        if (annotation.inNanos()) {
            sb.append(String.format("[%d nanos],", execution_time));
        }

        if (annotation.inMicros()) {
            sb.append("[" +execution_time/1000 +" micros]");
        }

        if (annotation.inMillis()) {
            sb.append("[" +execution_time/1000000L +" millis]");
        }

        if (annotation.inSeconds()) {
            sb.append("[" +execution_time/1000000000L +" second]");
        }

        if (annotation.inMinutes()) {
            sb.append("[" +execution_time/60000000000L +" minutes]");
        }

        if (sb.length() == 0) {
            sb.append(String.format("[%d nanos],", execution_time));
        }

        final String EXECUTED_TIME_LOG = "Method [%s] of class [%s] executed in " +sb.toString();

        EXECUTION_TIME_LOGGER.printMessage(String.format(EXECUTED_TIME_LOG +";",
                method.getName(), method.getDeclaringClass().getCanonicalName()));

        return method_result;
    }

    public void setLoggerLevel(LoggerLevel a_level) {
        EXECUTION_TIME_LOGGER.setLoggerLevel(a_level);
    }
}
