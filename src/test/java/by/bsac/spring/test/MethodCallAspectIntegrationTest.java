package by.bsac.spring.test;

import by.bsac.spring.AspectsConfiguration;
import by.bsac.spring.BeansConfiguration;
import by.bsac.spring.beans.Foo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("DEBUG")
@SpringBootTest(classes = {BeansConfiguration.class, AspectsConfiguration.class})
public class MethodCallAspectIntegrationTest {

    @Autowired
    private Foo foo;

    @Test
    void sayFoo_annotatedMethod_shouldDisplayLogBeforeMethodExecution() {
        foo.sayFoo();
    }

    @Test
    void sayHello_withArgsParameterIsTrue_shouldDisplayLogWithArgsParameter() {
        foo.sayHello("Slava", 22);
    }

    @Test
    void returnType_withReturnTypeParameterIsTrue_shouldDisplayLogWithReturnType() {
        foo.returnType("Bar!");
    }

    @Test
    void startTime_withStartTimeParameterIsTrue_shouldDisplayLogWithStartTimeParameter() {
        foo.startTime("GO!");
    }

    @Test
    void fullLog_allAnnotationParametersAreTrue_shouldDisplayLog() {
        foo.fullLog("Full!");
    }
}
