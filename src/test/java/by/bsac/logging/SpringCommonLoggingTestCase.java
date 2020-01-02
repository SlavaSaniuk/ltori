package by.bsac.logging;

import by.bsac.core.logging.SpringCommonLogging;
import by.bsac.spring.beans.Bar;
import by.bsac.spring.beans.Foo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("AccessStaticViaInstance")
public class SpringCommonLoggingTestCase {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpringCommonLoggingTestCase.class);

    @Test
    public void startCreateBean_simpleBean_shouldReturnMessageLog() {

        String expect = String.format("Start create bean [%s];", Foo.class.getCanonicalName());
        String actual = SpringCommonLogging.CREATION.startCreateBean(Foo.class).log();

        Assertions.assertEquals(expect, actual);
        LOGGER.debug("ACTUAL LOG: " +actual);
    }

    @Test
    public void startCreateBean_beanName_shouldReturnMessageLog() {

        String expect = String.format("Start creating bean \"%s\";", "Foo");
        String actual = SpringCommonLogging.CREATION.startCreateBean("Foo").log();

        Assertions.assertEquals(expect, actual);
        LOGGER.debug("ACTUAL LOG: " +actual);
    }

    @Test
    public void startCreateBean_forGenericType_shouldReturnMessageLog() {

        String expect = String.format("Start create bean [%s] for generic type [%s];",
                Foo.class.getCanonicalName(), Bar.class.getCanonicalName());
        String actual = SpringCommonLogging.CREATION.startCreateBean(Foo.class).forGenericType(Bar.class).log();

        Assertions.assertEquals(expect, actual);
        LOGGER.debug("ACTUAL LOG: " +actual);
    }

    @Test
    public void startCreateBean_ofType_shouldReturnMessageLog() {

        String expect = String.format("Start creating bean \"%s\" of type [%s];",
                "Foo", Bar.class.getCanonicalName());
        String actual = SpringCommonLogging.CREATION.startCreateBean("Foo").ofType(Bar.class).log();

        Assertions.assertEquals(expect, actual);
        LOGGER.debug("ACTUAL LOG: " +actual);
    }

    @Test
    public void startCreateBean_forProfile_shouldReturnMessageLog() {

        String expect = String.format("Start creating bean \"%s\" for profile [%s];",
                "Foo", "Test");
        String actual = SpringCommonLogging.CREATION.startCreateBean("Foo").forProfile("Test").log();

        Assertions.assertEquals(expect, actual);
        LOGGER.debug("ACTUAL LOG: " +actual);
    }


}
