package by.bsac.core.validation.exceptions;

/**
 * {@link by.bsac.aspects.validation.ParameterValidationAspect} aspect throw this exception
 * in cases, when target method parameters are not valid.
 */
public class NoValidParameterException extends RuntimeException {

    /**
     * Construct new {@link NoValidParameterException} object with custom exception message.
     */
    public NoValidParameterException(String msg) {
        super(msg);
    }

}
