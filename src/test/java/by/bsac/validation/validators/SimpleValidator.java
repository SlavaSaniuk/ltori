package by.bsac.validation.validators;

import by.bsac.core.validation.ParameterValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class SimpleValidator implements ParameterValidator {

    //Logger
    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleValidator.class);

    @Override
    public boolean validate(List<Object> parameters) {

        parameters.forEach(p -> LOGGER.debug("Validate parameter: " +p.getClass()));
        String msg = (String) parameters.get(0);
        LOGGER.debug("Parameter value: " +msg);

        LOGGER.debug("VALID PARAMETER!");
        return true;
    }
}
