package by.bsac.core.validation;

import by.bsac.annotations.logging.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Singleton used to share {@link ParameterValidator} implementations
 * for {@link by.bsac.aspects.validation.ParameterValidationAspect} aspect.
 * Use {@link ParameterValidatorsRegistry#addValidator(ParameterValidator)} to add validators for target aspect.
 */
@Singleton
public class ParameterValidatorsRegistry {

    //Logger
    private static final Logger LOGGER = LoggerFactory.getLogger(ParameterValidatorsRegistry.class);
    private static final ParameterValidatorsRegistry REGISTRY = new ParameterValidatorsRegistry();
    private static final List<ParameterValidator> PARAMETER_VALIDATORS = new ArrayList<>();

    //Private constructor
    private ParameterValidatorsRegistry() {
        LOGGER.debug(String.format("Create sharing instance of singleton [%s];", this.getClass().getCanonicalName()));
    }

    /**
     * Method return shared {@link ParameterValidatorsRegistry} instance.
     * @return - {@link ParameterValidatorsRegistry} instance.
     */
    public static ParameterValidatorsRegistry getInstance() {
        return REGISTRY;
    }

    /**
     * Method return defined list of {@link ParameterValidator} validators implementations.
     * @return - List of {@link ParameterValidator} implementations.
     */
    public List<ParameterValidator> getParameterValidators() {
        return PARAMETER_VALIDATORS;
    }

    /**
     * Method add specified {@link ParameterValidator} validator implementation to {@link by.bsac.aspects.validation.ParameterValidationAspect} aspect.
     * @param validator - {@link by.bsac.core.validation.ParameterValidator} implementation.
     */
    public void addValidator(ParameterValidator validator) {
        PARAMETER_VALIDATORS.add(validator);
    }


}
