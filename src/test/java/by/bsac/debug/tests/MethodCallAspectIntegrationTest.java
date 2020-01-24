package by.bsac.debug.tests;

import by.bsac.aspects.debug.MethodCallAspect;
import by.bsac.debug.DebugAspectsConfiguration;
import by.bsac.spring.beans.Foo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = DebugAspectsConfiguration.class)
public class MethodCallAspectIntegrationTest {

    //Logger
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodCallAspectIntegrationTest.class);
    //Spring beans
    @Autowired
    private MethodCallAspect ASPECT;
    @Autowired
    private Foo foo;

    @BeforeEach
    public void setUpBeforeEach() {
        this.ASPECT.disable();
    }

    @Test
    void sayFoo_aspectIsEnabled_shouldDisplayLog() {

        this.ASPECT.enable();

        foo.sayFoo("Hello world!");
    }

    @Test
    void sayFoo_aspectIsDisable_shouldNotDisplayLog() {
        foo.sayFoo("Hello world!");
    }
}
