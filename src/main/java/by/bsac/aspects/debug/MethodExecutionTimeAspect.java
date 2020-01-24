package by.bsac.aspects.debug;

import by.bsac.annotations.debug.MethodExecutionTime;
import by.bsac.aspects.ConfigurableAspects;
import by.bsac.core.AspectsRegistry;
import by.bsac.core.debugging.DefaultConfigurableLoggingAspect;
import by.bsac.core.debugging.LoggerLevel;
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

/**
 * Aspect for {@link MethodExecutionTime} annotation.
 */
@Aspect
public class MethodExecutionTimeAspect implements ConfigurableAspects {

    //Logger
    private static final Logger LOGGER = LoggerFactory.getLogger("METHOD_EXECUTION_TIME_ASPECT_LOG");
    private static final DefaultConfigurableLoggingAspect EXECUTION_TIME_LOGGER = new DefaultConfigurableLoggingAspect(LOGGER);
    private final AspectsRegistry REGISTRY = AspectsRegistry.getInstance();

    /**
     * {@link Pointcut} for {@link MethodExecutionTime} annotation.
     */
    @Pointcut("@annotation(by.bsac.annotations.debug.MethodExecutionTime)")
    public void methodExecutionTimeAnnotation() {}

    /**
     * {@link Around} advise log about target method execution time.
     * @param pjp - {@link ProceedingJoinPoint} join point.
     * @return - {@link Object} target method result.
     * @throws Throwable - throws if target method throws exception.
     */
    @SuppressWarnings("StringConcatenationInsideStringBufferAppend")
    @Around("methodExecutionTimeAnnotation() && by.bsac.aspects.CommonPointcuts.methodExecution()")
    public Object logOnMethodExecutionTime(ProceedingJoinPoint pjp) throws Throwable {

        if (!REGISTRY.isEnabled(MethodExecutionTimeAspect.class)) return pjp.proceed();

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

    @Override
    public void enable() {
        REGISTRY.enableAspect(MethodExecutionTimeAspect.class);
    }

    @Override
    public void disable() {
        REGISTRY.disableAspect(MethodExecutionTimeAspect.class);
    }

    @Override
    public boolean isEnabled() {
        return REGISTRY.isEnabled(MethodExecutionTimeAspect.class);
    }
}
