package by.bsac.aspects.validation;

import by.bsac.annotations.logging.Singleton;
import by.bsac.core.validation.ParameterValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

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


    public static ParameterValidatorsRegistry getInstance() {
        return REGISTRY;
    }

    public List<ParameterValidator> getParameterValidators() {
        return PARAMETER_VALIDATORS;
    }


}
