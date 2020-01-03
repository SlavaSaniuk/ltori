package by.bsac.logging;

import by.bsac.spring.BeansConfiguration;
import by.bsac.spring.beans.Bar;
import by.bsac.spring.beans.Foo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static by.bsac.core.logging.SpringCommonLogging.*;

@SuppressWarnings("AccessStaticViaInstance")
public class SpringCommonLoggingTestCase {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpringCommonLoggingTestCase.class);

    /*
    **** BeanDefinition test cases *****
     */

    @Test
    void of_beanClass_shouldReturnBeanName() {

        String expected_name = String.format("Bean of class [%s]", Foo.class.getCanonicalName());
        String actual_name = BeanDefinition.of(Foo.class).getBeanName();

        Assertions.assertEquals(expected_name, actual_name);
        LOGGER.debug("Actual bean name: " +actual_name);
    }

    @Test
    void of_beanName_shouldReturnBeanName() {

        String expected_name = String.format("Bean with name \"%s\"", "FooBean");
        String actual_name = BeanDefinition.of("FooBean").getBeanName();

        Assertions.assertEquals(expected_name, actual_name);
        LOGGER.debug("Actual bean name: " +actual_name);
    }

    @Test
    void of_beanNameAndOfClass_shouldReturnBeanName() {

        String expected_name = String.format("Bean with name \"%s\" of class [%s]", "FooBean", Foo.class.getCanonicalName());
        String actual_name = BeanDefinition.of("FooBean").ofClass(Foo.class).getBeanName();

        Assertions.assertEquals(expected_name, actual_name);
        LOGGER.debug("Actual bean name: " +actual_name);
    }

    @Test
    void of_beanClassAndWithName_shouldReturnBeanName() {

        String expected_name = String.format("Bean of class [%s] with name \"%s\"", Foo.class.getCanonicalName(),"FooBean");
        String actual_name = BeanDefinition.of(Foo.class).withName("FooBean").getBeanName();

        Assertions.assertEquals(expected_name, actual_name);
        LOGGER.debug("Actual bean name: " +actual_name);
    }

    @Test
    void of_beanClassForGenericType_shouldReturnBeanName() {

        String expected_name = String.format("Bean of class [%s] for generic type [%s]", Foo.class.getCanonicalName(),Bar.class.getCanonicalName());
        String actual_name = BeanDefinition.of(Foo.class).forGenericType(Bar.class).getBeanName();

        Assertions.assertEquals(expected_name, actual_name);
        LOGGER.debug("Actual bean name: " +actual_name);
    }

    @Test
    void of_beanClassForProfile_shouldReturnBeanName() {

        String expected_name = String.format("Bean of class [%s] for profile [%s]", Foo.class.getCanonicalName(),"TEST");
        String actual_name = BeanDefinition.of(Foo.class).forProfile("TEST").getBeanName();

        Assertions.assertEquals(expected_name, actual_name);
        LOGGER.debug("Actual bean name: " +actual_name);
    }

    @Test
    void of_AllAvailableBeansOption_shouldReturnBeanMessageLog() {

        String expected_name = String.format("Bean of class [%s] with name \"%s\" for generic type [%s] for profile [%s]", Foo.class.getCanonicalName(), "FooBean", Bar.class.getCanonicalName(), "TEST");
        String actual_name = BeanDefinition.of(Foo.class).withName("FooBean").forGenericType(Bar.class).forProfile("TEST").getBeanName();

        Assertions.assertEquals(expected_name, actual_name);
        LOGGER.debug("Actual bean name: " +actual_name);
    }

    /*
    ***** CREATION section test cases *****
     */

    @Test
    void startCreateBean_beanDefinition_shouldReturnLogMessage() {

        String expected_message = String.format("Start of create [Bean with name \"%s\" of class [%s]];", "FooBean", Foo.class.getCanonicalName());
        String actual_message = CREATION.startCreateBean(BeanDefinition.of("FooBean").ofClass(Foo.class));

        Assertions.assertEquals(expected_message, actual_message);
        LOGGER.debug("Actual log message: " +actual_message);
    }

    @Test
    void endCreateBean_beanDefinition_shouldReturnLogMessage() {

        String expected_message = String.format("End of create [Bean with name \"%s\" of class [%s]];", "FooBean", Foo.class.getCanonicalName());
        String actual_message = CREATION.endCreateBean(BeanDefinition.of("FooBean").ofClass(Foo.class));

        Assertions.assertEquals(expected_message, actual_message);
        LOGGER.debug("Actual log message: " +actual_message);
    }

    //Throws exception
    @Test
    void creationThrowException_exceptionClass_shouldReturnMessageLog() {

        String expected_message = String.format("Creation of [Bean with name \"%s\" of class [%s]] throw exception [%s];", "FooBean", Foo.class.getCanonicalName(), IllegalArgumentException.class.getCanonicalName());
        String actual_message = CREATION.creationThrowException(BeanDefinition.of("FooBean").ofClass(Foo.class), IllegalArgumentException.class);

        Assertions.assertEquals(expected_message, actual_message);
        LOGGER.debug("Actual log message: " +actual_message);

    }

    @Test
    void creationThrowException_exceptionObject_shouldReturnMessageLog() {

        IllegalArgumentException exc_obj = new IllegalArgumentException("invalid_argument_message");
        String expected_message = String.format("Creation of [Bean with name \"%s\" of class [%s]] throw exception [%s];", "FooBean", Foo.class.getCanonicalName(), exc_obj.getClass().getCanonicalName());
        String actual_message = CREATION.creationThrowException(BeanDefinition.of("FooBean").ofClass(Foo.class), exc_obj);

        Assertions.assertEquals(expected_message, actual_message);
        LOGGER.debug("Actual log message: " +actual_message);

    }

    @Test
    void creationThrowExceptionWithMessage_exceptionObject_shouldReturnMessageLog() {

        IllegalArgumentException exc_obj = new IllegalArgumentException("invalid_argument_message");
        String expected_message = String.format("Creation of [Bean with name \"%s\" of class [%s]] throw exception [%s] with message: %s;",
                "FooBean", Foo.class.getCanonicalName(), exc_obj.getClass().getCanonicalName(), exc_obj.getMessage());
        String actual_message = CREATION.creationThrowExceptionWithMessage(BeanDefinition.of("FooBean").ofClass(Foo.class), exc_obj);

        Assertions.assertEquals(expected_message, actual_message);
        LOGGER.debug("Actual log message: " +actual_message);

    }

    /*
    ***** INITIALIZATION section
     */
    @Test
    void startInitializeConfiguration_configurationClass_shouldReturnMessageLog() {
        String expected_message = String.format("Start of initialization [%s] configuration class;", BeansConfiguration.class.getCanonicalName());
        String actual_message = INITIALIZATION.startInitializeConfiguration(BeansConfiguration.class);

        Assertions.assertEquals(expected_message, actual_message);
        LOGGER.debug("Actual message: " +actual_message);
    }

    @Test
    void endInitializeConfiguration_configurationClass_shouldReturnMessageLog() {
        String expected_message = String.format("End of initialization [%s] configuration class;", BeansConfiguration.class.getCanonicalName());
        String actual_message = INITIALIZATION.endInitializeConfiguration(BeansConfiguration.class);

        Assertions.assertEquals(expected_message, actual_message);
        LOGGER.debug("Actual message: " +actual_message);
    }





}
