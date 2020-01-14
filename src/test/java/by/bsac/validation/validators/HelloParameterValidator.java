package by.bsac.validation.validators;

import by.bsac.core.validation.ParameterValidator;

import java.util.List;

public class HelloParameterValidator implements ParameterValidator {


    @Override
    public boolean validate(List<Object> parameters) {
        String hello_msg = (String) parameters.get(0);
        return hello_msg.startsWith("Hello ");
    }

}
