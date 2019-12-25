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
    void execTime_annotatedMethodSleep1938millis_shouldDisplayLogAboutMethodExecutionTime() throws InterruptedException {
        final String msg = "Bar!";
        String result = bar.execTime(msg);
        Assertions.assertEquals(msg, result);
    }

    @Test
    void execTimeInMicros_annotatedMethodSleep1938millis_shouldDisplayLogAboutMethodExecutionTimeInMicros() throws InterruptedException {
        final String msg = "Bar!";
        String result = bar.execTimeInMicros(msg);
        Assertions.assertEquals(msg, result);
    }

    @Test
    void execTimeInMillis_annotatedMethodSleep1938millis_shouldDisplayLogAboutMethodExecutionTimeInMillis() throws InterruptedException {
        final String msg = "Bar!";
        String result = bar.execTimeInMillis(msg);
        Assertions.assertEquals(msg, result);
    }

    @Test
    void execTimeInSeconds_annotatedMethodSleep1938millis_shouldDisplayLogAboutMethodExecutionTimeInSeconds() throws InterruptedException {
        final String msg = "Bar!";
        String result = bar.execTimeInSeconds(msg);
        Assertions.assertEquals(msg, result);
    }

    @Test
    void execTimeInMinutes_annotatedMethodSleep1938millis_shouldDisplayLogAboutMethodExecutionTimeInMinutes() throws InterruptedException {
        final String msg = "Bar!";
        String result = bar.execTimeInMinutes(msg);
        Assertions.assertEquals(msg, result);
    }

    @Test
    void execTimeWithoutAll_annotatedMethodSleep1938millis_shouldDisplayLogAboutMethodExecutionTimeWithoutTime() throws InterruptedException {
        final String msg = "Bar!";
        String result = bar.execTimeWithoutAll(msg);
        Assertions.assertEquals(msg, result);
    }

    @Test
    void execTimeWithAll_annotatedMethodSleep1938millis_shouldDisplayLogAboutMethodExecutionTimeInAllDecisions() throws InterruptedException {
        final String msg = "Bar!";
        String result = bar.execTimeWithAll(msg);
        Assertions.assertEquals(msg, result);
    }

}
