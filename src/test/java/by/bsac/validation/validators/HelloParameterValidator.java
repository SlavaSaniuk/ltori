package by.bsac.validation.validators;

import by.bsac.core.validation.ParameterValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class HelloParameterValidator implements ParameterValidator {

    //Logger
    private static final Logger LOGGER = LoggerFactory.getLogger(HelloParameterValidator.class);

    @Override
    public boolean validate(List<Object> parameters) {
        String hello_msg = (String) parameters.get(0);
        return hello_msg.startsWith("Hello ");
    }

}
