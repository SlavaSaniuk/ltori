package by.bsac.spring.beans;

import by.bsac.annotations.debug.MethodCall;
import by.bsac.annotations.debug.MethodExecutionTime;

public class Foo {

    @MethodCall(withReturnType = true, withStartTime = true, withArgs = true)
    @MethodExecutionTime(inMicros = true, inMillis = true)
    public String sayFoo(String msg) {
        String FOO = "FOO!" +msg;
        System.out.println(FOO);
        return FOO;
    }
}
