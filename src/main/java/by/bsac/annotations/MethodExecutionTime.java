package by.bsac.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface MethodExecutionTime {

    boolean inNanos() default true;

    boolean inMicros() default false;

    boolean inMillis() default false;

    boolean inSeconds() default false;

    boolean inMinutes() default false;

}
