package by.bsac.annotations.validation;

import by.bsac.core.validation.ParameterValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Trigger annotation. Annotation applied to target method that's method parameter need to be validated.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ParameterValidation {

    /**
     * {@link ParameterValidator} validator implementation for target method parameter.
     * @return - {@link Class} that implements {@link ParameterValidator} interface.
     */
    Class<? extends ParameterValidator> value();

    /**
     * Class array of target method parameters classes that's need to be validated.
     * @return - default empty array.
     */
    Class<?>[] parametersClasses() default {};

    /**
     * Exception message for {@link by.bsac.core.validation.exceptions.NoValidParameterException} exception.
     * @return - custom exception message.
     */
    String errorMessage() default "";

}
