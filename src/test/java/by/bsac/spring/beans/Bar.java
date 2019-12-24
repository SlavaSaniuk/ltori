package by.bsac.spring.beans;

import by.bsac.annotations.MethodCall;
import by.bsac.annotations.MethodExecutionTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class Bar {

    private static final Logger LOGGER = LoggerFactory.getLogger(Bar.class);

    @MethodCall(withStartTime = true, withArgs = true, withReturnType = true)
    @MethodExecutionTime
    public String execTime(String msg) {
        LOGGER.debug(msg);
        return msg;
    }
}
