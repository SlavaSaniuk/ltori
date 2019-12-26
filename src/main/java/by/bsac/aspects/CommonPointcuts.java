package by.bsac.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * Commons share {@link Pointcut} for other aspects.
 */
@Aspect
public class CommonPointcuts {

    /**
     * {@link Pointcut} for any method execution.
     */
    @Pointcut("execution(* *(..))")
    public static void methodExecution() {}
}
