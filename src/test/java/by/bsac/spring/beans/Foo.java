package by.bsac.spring.beans;

import by.bsac.annotations.MethodCall;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class Foo {

    //Logger
    private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(Foo.class);

    @MethodCall
    public void sayFoo() {
        LOGGER.debug("Foo!");
    }

}
