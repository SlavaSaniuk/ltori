package by.bsac.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.time.Duration;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

@SuppressWarnings("MissingAspectjAutoproxyInspection")
@Aspect
public class MethodExecutionTimeAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger("METHOD_EXECUTION_TIME_ASPECT_LOG");

    @Pointcut("@annotation(by.bsac.annotations.MethodExecutionTime)")
    private void methodExecutionTimeAnnotation() {}

    @Around("by.bsac.aspects.CommonPointcuts.methodExecution() && methodExecutionTimeAnnotation()")
    public Object logAboutMethodExecutionTime(ProceedingJoinPoint pjp) throws Throwable {

        LocalTime start_time = LocalTime.now();

        Object result = pjp.proceed();

        LocalTime finish_time = LocalTime.now();
        long method_execution_time = ChronoUnit.NANOS.between(start_time, finish_time);
                //Duration.between(start_time, finish_time).toNanos();

        LOGGER.debug("Method executed in " +method_execution_time +" nanoseconds;");
        return result;
    }


}
