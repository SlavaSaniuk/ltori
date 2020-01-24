package by.bsac.spring;

import by.bsac.spring.beans.Foo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static by.bsac.core.logging.SpringCommonLogging.*;

@Configuration
public class BeansConfiguration {

    //Logger
    private static final Logger LOGGER = LoggerFactory.getLogger(BeansConfiguration.class);

    @Bean(name = "Foo")
    public Foo getFoo() {
        LOGGER.debug(CREATION.startCreateBean(BeanDefinition.of(Foo.class)));
        Foo foo = new Foo();

        LOGGER.debug(CREATION.endCreateBean(BeanDefinition.of(Foo.class)));
        return foo;
    }
}
