package by.bsac.spring.beans;

import by.bsac.annotations.debug.MethodCall;
import by.bsac.annotations.debug.MethodExecutionTime;
import org.springframework.stereotype.Component;



@Component
public class Bar {

    @MethodCall(withArgs = true, withReturnType = true, withStartTime = true)
    @MethodExecutionTime
    public String execTime(String msg) throws InterruptedException {
        System.out.println(msg);
        Thread.sleep(1968);
        return msg;
    }

    @MethodCall(withArgs = true, withReturnType = true, withStartTime = true)
    @MethodExecutionTime(inNanos = false, inMicros = true)
    public String execTimeInMicros(String msg) throws InterruptedException {
        System.out.println(msg);
        Thread.sleep(1968);
        return msg;
    }

    @MethodCall(withArgs = true, withReturnType = true, withStartTime = true)
    @MethodExecutionTime(inNanos = false, inMillis = true)
    public String execTimeInMillis(String msg) throws InterruptedException {
        System.out.println(msg);
        Thread.sleep(1968);
        return msg;
    }

    @MethodCall(withArgs = true, withReturnType = true, withStartTime = true)
    @MethodExecutionTime(inNanos = false, inSeconds = true)
    public String execTimeInSeconds(String msg) throws InterruptedException {
        System.out.println(msg);
        Thread.sleep(1968);
        return msg;
    }

    @MethodCall(withArgs = true, withReturnType = true, withStartTime = true)
    @MethodExecutionTime(inNanos = false, inMinutes = true)
    public String execTimeInMinutes(String msg) throws InterruptedException {
        System.out.println(msg);
        Thread.sleep(1968);
        return msg;
    }

    @MethodCall(withArgs = true, withReturnType = true, withStartTime = true)
    @MethodExecutionTime(inNanos = false)
    public String execTimeWithoutAll(String msg) throws InterruptedException {
        System.out.println(msg);
        Thread.sleep(1968);
        return msg;
    }

    @MethodCall(withArgs = true, withReturnType = true, withStartTime = true)
    @MethodExecutionTime(inNanos = true, inMillis = true, inMicros = true, inMinutes = true, inSeconds = true)
    public String execTimeWithAll(String msg) throws InterruptedException {
        System.out.println(msg);
        Thread.sleep(1968);
        return msg;
    }

}
