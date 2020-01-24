package by.bsac.debug.tests;

import by.bsac.aspects.debug.MethodExecutionTimeAspect;
import by.bsac.debug.DebugAspectsConfiguration;
import by.bsac.spring.beans.Foo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = DebugAspectsConfiguration.class)
public class MethodExecutionTimeAspectIntegrationTest {

    //Logger
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodExecutionTimeAspectIntegrationTest.class);
    //Spring beans
    @Autowired
    private MethodExecutionTimeAspect ASPECT;
    @Autowired
    private Foo foo;

    @BeforeEach
    void setUpBeforeEach() {
        this.ASPECT.disable();
    }

    @Test
    void sayFoo_aspectIsEnable_shouldDisplayLog() {

        this.ASPECT.enable();

        this.foo.sayFoo("Hello world!");
    }

    @Test
    void sayFoo_aspectIsDisabled_shouldNotDisplayLog() {

        this.foo.sayFoo("Hello world!");
    }
}
