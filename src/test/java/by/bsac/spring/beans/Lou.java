package by.bsac.spring.beans;

import by.bsac.annotations.validation.ParameterValidation;
import by.bsac.validation.validators.SimpleValidator;

public class Lou {

    @ParameterValidation(value = SimpleValidator.class, parametersClasses = {String.class})
    public void sayLou(String lou, StringBuilder sb) {
        System.out.println(lou);
    }
}
