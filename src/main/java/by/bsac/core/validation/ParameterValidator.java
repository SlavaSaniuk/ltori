package by.bsac.core.validation;

import java.util.List;

/**
 * Common contract for custom ParameterValidation validators implementations.
 */
public interface ParameterValidator {

    /**
     * Implement all valdation login in this method.
     * @param parameters - {@link List} of validated parameters.
     * @return - 'true' - if parameters are valid.
     */
    boolean validate(List<Object> parameters);

}
