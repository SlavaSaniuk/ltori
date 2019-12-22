package by.bsac.spring;

import by.bsac.spring.beans.Foo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class BeansConfiguration {

    @Bean(name = "Foo")
    public Foo getFoo() {
        return new Foo();
    }

}
