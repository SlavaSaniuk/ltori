package by.bsac.annotations.debug;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotations mark annotated method to be logged about method execution.
 * SLF4L logger log: "METHOD_CALL_ASPECT_LOG: "Program thread [current_thread] call
 * method [target_method] of class [target_class]" when method executed.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface MethodCall {

    /**
     * Add information to log statement about target method parameters when this parameter is's true.
     * @return - {@link Boolean} default false;
     */
    boolean withArgs() default false;

    /**
     * Add information to log statement about target method return type when this parameter is's true.
     * @return - {@link Boolean} default false;
     */
    boolean withReturnType() default false;

    /**
     * Add information to log statement about target method start execution time when this parameter is's true.
     * @return - {@link Boolean} default false;
     */
    boolean withStartTime() default false;

}
