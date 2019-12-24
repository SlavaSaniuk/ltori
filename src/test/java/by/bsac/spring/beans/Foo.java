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

    @MethodCall(withArgs = true)
    public void sayHello(String name, int age) {
        LOGGER.debug(String.format("Hello! My name is %s. I am %d years old.", name, age));
    }

    @MethodCall(withReturnType = true)
    public String returnType(String msg) {
        LOGGER.debug(msg);
        return msg;
    }

    @MethodCall(withStartTime= true)
    public void startTime(String msg) {
        LOGGER.debug(msg);
    }

    @MethodCall(withArgs = true, withReturnType = true, withStartTime = true)
    public void fullLog(String msg) {
        LOGGER.debug(msg);
    }
}
