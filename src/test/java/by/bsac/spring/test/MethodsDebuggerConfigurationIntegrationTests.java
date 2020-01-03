package by.bsac.spring.test;

import by.bsac.spring.BeansConfiguration;
import by.bsac.spring.LibraryConfiguration;
import by.bsac.spring.beans.Bar;
import by.bsac.spring.beans.Foo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {BeansConfiguration.class, LibraryConfiguration.class})
public class MethodsDebuggerConfigurationIntegrationTests {

    @Autowired
    private Foo foo;

    @Autowired
    private Bar bar;

    @Test
    public void fullLog_methodDebuggerBean_shouldDisplayMethodCallLog() {
        foo.fullLog("Hello from test!");
    }

    @Test
    public void execTimeInMillis_methodDebuggerBean_shouldDisplayMethodExecutionTimeLog() throws InterruptedException {
        bar.execTimeInMillis("Hello for test!");
    }

}
