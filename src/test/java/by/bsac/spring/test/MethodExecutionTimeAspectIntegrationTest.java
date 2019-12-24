package by.bsac.spring.test;

import by.bsac.spring.AspectsConfiguration;
import by.bsac.spring.BeansConfiguration;
import by.bsac.spring.beans.Bar;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("DEBUG")
@SpringBootTest(classes = {BeansConfiguration.class, AspectsConfiguration.class})
public class MethodExecutionTimeAspectIntegrationTest {

    @Autowired
    private Bar bar;

    @Test
    void execTime_annotatedMethod_shouldLogMethodExecutionTime() {
        String msg = "Bar!";
        String result = this.bar.execTime(msg);
        Assertions.assertEquals(msg, result);
    }
}
