package by.bsac.annotations;

import org.aspectj.lang.ProceedingJoinPoint;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation mark method to be logged about it's method execution time.
 * SLF4L logger print:  "METHOD_EXECUTION_TIME_ASPECT_LOG: "Start of measure execution
 * time of method [method_name] of class [target_class]"" before method execution.
 * Next {@link by.bsac.aspects.MethodExecutionTimeAspect#logOnMethodExecutionTime(ProceedingJoinPoint)}
 * around advise calculate method execution time, and logger print "METHOD_EXECUTION_TIME_ASPECT_LOG:
 * "Method [target_method] of class [target_class] executed in [execution_time];"" log.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface MethodExecutionTime {

    /**
     * Print method execution time value in nanoseconds.
     * @return - {@link Boolean} default 'true';
     */
    boolean inNanos() default true;

    /**
     * Print method execution time value in microseconds.
     * @return - {@link Boolean} default 'false';
     */
    boolean inMicros() default false;

    /**
     * Print method execution time value in milliseconds.
     * @return - {@link Boolean} default 'false';
     */
    boolean inMillis() default false;

    /**
     * Print method execution time value in seconds.
     * @return - {@link Boolean} default 'false';
     */
    boolean inSeconds() default false;

    /**
     * Print method execution time value in minutes.
     * @return - {@link Boolean} default 'false';
     */
    boolean inMinutes() default false;

}
