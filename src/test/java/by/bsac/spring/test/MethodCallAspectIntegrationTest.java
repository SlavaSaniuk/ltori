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

}
