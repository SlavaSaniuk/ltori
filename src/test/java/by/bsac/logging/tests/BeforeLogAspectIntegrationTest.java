package by.bsac.logging.tests;

import by.bsac.logging.LoggingAspectsConfiguration;
import by.bsac.spring.beans.Foo;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = LoggingAspectsConfiguration.class)
public class BeforeLogAspectIntegrationTest {

    //Logger
    private static final Logger LOGGER = LoggerFactory.getLogger(BeforeLogAspectIntegrationTest.class);
    //Spring beans
    @Autowired
    private Foo foo;

    @Test
    void sayFoo_annotatedMethod_shouldDisplayLog() {
        foo.sayFoo("Hello world!");
    }
}
