package by.bsac.spring.beans;

import by.bsac.annotations.debug.MethodCall;
import by.bsac.annotations.debug.MethodExecutionTime;
import by.bsac.annotations.logging.BeforeLog;

public class Foo {

    @MethodCall(withReturnType = true, withStartTime = true, withArgs = true)
    @MethodExecutionTime(inMicros = true, inMillis = true)
    @BeforeLog(value = "Say foo with string: %s;", argsClasses = String.class)
    public String sayFoo(String msg) {
        String FOO = "FOO!" +msg;
        System.out.println(FOO);
        return FOO;
    }
}
