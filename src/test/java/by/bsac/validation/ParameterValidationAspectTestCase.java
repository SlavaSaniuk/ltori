package by.bsac.validation;

import by.bsac.annotations.debug.MethodCall;
import by.bsac.annotations.debug.MethodExecutionTime;
import by.bsac.core.validation.exceptions.NoValidParameterException;
import by.bsac.spring.AspectsConfiguration;
import by.bsac.spring.BeansConfiguration;
import by.bsac.spring.beans.Lou;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {BeansConfiguration.class, AspectsConfiguration.class})
public class ParameterValidationAspectTestCase {

    @Autowired
    private Lou lou;

    @Test
    public void sayLou_annotatedMethod_shouldDisplayLogBefore() {
        this.lou.sayLou("LOU!", new StringBuilder());
    }

    @Test
    @MethodExecutionTime(inMicros = true, inMillis = true)
    @MethodCall
    public void sayHello_validParameter_shouldDisplayLogs() {
        this.lou.sayHello("Hello world!");
    }

    @Test
    @MethodExecutionTime(inMicros = true, inMillis = true)
    @MethodCall
    public void sayHello_notValidParameter_shouldThrowNoValidParameterException() {
        Assertions.assertThrows(NoValidParameterException.class, () -> this.lou.sayHello("Hi world!"));
    }

}
