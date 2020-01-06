package by.bsac.annotations.validation;

import by.bsac.core.validation.ParameterValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ParameterValidation {


    Class<? extends ParameterValidator> value();

    Class<?>[] parametersClasses() default {};

    String[] parametersNames() default {};

}
