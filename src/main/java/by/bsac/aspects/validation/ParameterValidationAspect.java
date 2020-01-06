package by.bsac.aspects.validation;

import by.bsac.annotations.validation.ParameterValidation;
import by.bsac.core.validation.ParameterValidator;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

@Aspect
public class ParameterValidationAspect {

    //Logger
    private static final Logger LOGGER = LoggerFactory.getLogger("PARAMETER_VALIDATION_ASPECT_LOG");
    //Class variables
    private final List<ParameterValidator> validators = new ArrayList<>();

    @Pointcut("@annotation(by.bsac.annotations.validation.ParameterValidation)")
    public void parameterValidationAnnotation() {}

    @Before("by.bsac.aspects.CommonPointcuts.methodExecution() && parameterValidationAnnotation()")
    public void validateParametersBeforeMethodExecuted(JoinPoint jp) {

        Object[] method_args = jp.getArgs();

        for(Object obj : method_args) {
            LOGGER.debug("Method argument class: " +obj.getClass());
        }

        ParameterValidation annotation = ((MethodSignature) jp.getSignature()).getMethod().getAnnotation(ParameterValidation.class);
        ParameterValidator validator = this.getValidator(annotation.value());

        //Maps parameters
        List<Object> parameters_to_validate = new ArrayList();
        for (Class<?> required_parameter : annotation.parametersClasses()) {
            for (int i=0; i < annotation.parametersClasses().length; i ++) {
                if(required_parameter == method_args[i].getClass()) parameters_to_validate.add(method_args[i]);
            }
        }

        if (validator == null) throw new NullPointerException("Not registered validator of type: " +annotation.value().getCanonicalName());

        validator.validate(parameters_to_validate);
    }

    public <V extends ParameterValidator> void addValidator(V validator) {
        this.validators.add(validator);
    }

    private ParameterValidator getValidator(Class<? extends ParameterValidator> required_validator_class) {
        return this.validators.stream().filter(v -> v.getClass() == required_validator_class).findFirst().orElse(null);
    }
}
