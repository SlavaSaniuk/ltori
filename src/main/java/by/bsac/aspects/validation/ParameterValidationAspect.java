package by.bsac.aspects.validation;

import by.bsac.annotations.validation.ParameterValidation;
import by.bsac.collections.SetUtils;
import by.bsac.core.validation.ParameterValidator;
import by.bsac.core.validation.ParameterValidatorsRegistry;
import by.bsac.core.validation.exceptions.NoParameterValidatorException;
import by.bsac.core.validation.exceptions.NoValidParameterException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * This aspect used to validate method parameter before target method executed.
 * Target method need to be marked by {@link ParameterValidation} annotation.
 */
@Aspect
public class ParameterValidationAspect {

    //Logger
    private static final Logger LOGGER = LoggerFactory.getLogger("PARAMETER_VALIDATION_ASPECT_LOG");
    //Class variables
    private static final String DEFAULT_ERROR_MESSAGE = "Invalid parameter value [%s];";
    private static final String NO_PARAMETER_VALIDATOR_EXCEPTION_MSG = "No ParameterValidator specified for method [%s] of class[%s];";
    private final ParameterValidatorsRegistry VALIDATORS_REGISTRY = ParameterValidatorsRegistry.getInstance(); //Shared ParameterValidatorRegistry

    /**
     * AspectJ {@link Pointcut}. Trigger in cases when annotated by {@link ParameterValidation} method executed.
     */
    @Pointcut("@annotation(by.bsac.annotations.validation.ParameterValidation)")
    public void parameterValidationAnnotation() {}

    /**
     * Before aspect validate method parameters by specified {@link ParameterValidator} validator implementation.
     * In cases when parameters are not valid, aspect throws {@link NoValidParameterException} exception object.
     * If {@link ParameterValidator} validator nat register in {@link ParameterValidatorsRegistry} registry method throw {@link NoParameterValidatorException} exception object.
     * Use {@link ParameterValidationAspect#addValidator(ParameterValidator)} or {@link ParameterValidatorsRegistry#addValidator(ParameterValidator)} methods to add validators.
     * @param jp - {@link JoinPoint} aspectj jp.
     */
    @Before("by.bsac.aspects.CommonPointcuts.methodExecution() && parameterValidationAnnotation()")
    public void validateParametersBeforeMethodExecuted(JoinPoint jp) {

        //Get methods parameter
        Object[] method_parameters = jp.getArgs();

        //Get annotation
        ParameterValidation annotation = ((MethodSignature) jp.getSignature()).getMethod().getAnnotation(ParameterValidation.class);
        ParameterValidator validator = this.getValidator(annotation.value()); //Validator for parameters
        if (validator == null)
            throw new NoParameterValidatorException(
                    String.format(NO_PARAMETER_VALIDATOR_EXCEPTION_MSG, jp.getSignature().getName(), jp.getSignature().getDeclaringTypeName()));
        LOGGER.debug(String.format("Use ParameterValidator of class [%s] ", validator.getClass()));

        Set<Class<?>> parameters_for_validate = SetUtils.asSet(annotation.parametersClasses()); //Parameters classes for validation
        if (parameters_for_validate.size() == 0) return;
        LOGGER.debug("Specified parameters for validate: " +parameters_for_validate.toString());

        String error_message = annotation.errorMessage().equals("")?
                String.format(DEFAULT_ERROR_MESSAGE, parameters_for_validate.toString()) : annotation.errorMessage(); //Custom error message
        LOGGER.debug(String.format("Used error message [%s]", error_message));

        //Maps method and annotation parameters
        List<Object> parameters_to_validate =
                Arrays.stream(method_parameters).filter(p -> parameters_for_validate.contains(p.getClass())).collect(Collectors.toList());
        LOGGER.debug("Parameters for validation: " +parameters_to_validate.toString());

        //Validate parameters
        boolean validation_result = validator.validate(parameters_to_validate);
        LOGGER.debug("Parameters are valid: " +validation_result);
        if (!validation_result) throw new NoValidParameterException(error_message);
    }

    public <V extends ParameterValidator> void addValidator(V validator) {
        this.VALIDATORS_REGISTRY.addValidator(validator);
    }

    private ParameterValidator getValidator(Class<? extends ParameterValidator> required_validator_class) {
        return this.VALIDATORS_REGISTRY.getParameterValidators().stream().filter(v -> v.getClass() == required_validator_class).findFirst().orElse(null);
    }

}
