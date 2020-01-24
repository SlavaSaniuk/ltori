package by.bsac.annotations.logging;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * AspectJ trigger annotation. Annotate required method with this annotation,
 * set {@link BeforeLog#value()} string message and, before required method executed,
 * logger print your {@link BeforeLog#value()} message. {@link by.bsac.aspects.logging.BeforeLogAspect}
 * used {@link String#format(String, Object...)} to print your message, so you can specify method arguments classes
 * to print method arguments {@link Object#toString()}.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface BeforeLog {

    /**
     * Message to print. You can use Java printf style format.
     * @return {@link String} message.
     */
    String value();

    /**
     * {@link Class} classes of required method arguments.
     * @return - default empty array.
     */
    Class<?>[] argsClasses() default {};
}
