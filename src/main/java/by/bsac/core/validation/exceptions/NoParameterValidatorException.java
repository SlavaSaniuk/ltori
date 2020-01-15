package by.bsac.core.validation.exceptions;

/**
 * {@link by.bsac.aspects.validation.ParameterValidationAspect} aspect throws this exception in cases,
 * when specified in {@link by.bsac.annotations.validation.ParameterValidation} annotation
 * parameter validator not registered in {@link by.bsac.core.validation.ParameterValidatorsRegistry} registry.
 */
public class NoParameterValidatorException extends RuntimeException {

    /**
     * Construct new {@link NoParameterValidatorException} object with custom exception message.
     * @param msg - custom exception message.
     */
    public NoParameterValidatorException(String msg) {
        super(msg);
    }
}
