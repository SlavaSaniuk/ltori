package by.bsac.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface MethodCall {

    boolean withArgs() default false;

    boolean withReturnType() default false;

    boolean withStartTime() default false;

}
