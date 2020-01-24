package by.bsac.aspects.logging;

import by.bsac.annotations.logging.BeforeLog;
import by.bsac.aspects.ConfigurableAspects;
import by.bsac.collections.SetUtils;
import by.bsac.core.AspectsRegistry;
import by.bsac.core.debugging.DefaultConfigurableLoggingAspect;
import by.bsac.core.debugging.LoggerLevel;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

/**
 *  {@link BeforeLogAspect} call {@link BeforeLogAspect#logOnBeforeLogAnnotationAnnotatedMethodExecuted(JoinPoint)}
 * method everytime when method annotated with {@link BeforeLog} annotation executed.
 *  Aspect implements {@link ConfigurableAspects} interface so yon need enable aspect before work.
 * Use {@link BeforeLogAspect#enable()} or {@link AspectsRegistry#enableAspect(Class)} to enable aspect.
 */
@Aspect
public class BeforeLogAspect implements ConfigurableAspects {

    //Logger
    private static final Logger LOGGER = LoggerFactory.getLogger(BeforeLogAspect.class);
    //Class variables
    private static final AspectsRegistry REGISTRY = AspectsRegistry.getInstance();
    private static final DefaultConfigurableLoggingAspect BEFORE_LOG_LOGGER = new DefaultConfigurableLoggingAspect(LOGGER);

    @Pointcut("@annotation(by.bsac.annotations.logging.BeforeLog)")
    private void beforeLogAnnotationPointcut() {}

    /**
     * Method log message before method executed specified in {@link BeforeLog#value()} annotation parameter.
     * @param jp - annotated method.
     */
    @Before("beforeLogAnnotationPointcut() && by.bsac.aspects.CommonPointcuts.methodExecution()")
    public void logOnBeforeLogAnnotationAnnotatedMethodExecuted(JoinPoint jp) {

        //Return if aspect not enabled
        if (!this.isEnabled()) return;

        //Get method annotation values
        String LOG_MSG = ((MethodSignature) jp.getSignature()).getMethod().getAnnotation(BeforeLog.class).value();
        Class<?>[] args_classes = ((MethodSignature) jp.getSignature()).getMethod().getAnnotation(BeforeLog.class).argsClasses();

        //Get method args of required class
        Set<Object> method_args = SetUtils.asSet(jp.getArgs());
        Object[] required_args = method_args.stream().filter(obj -> SetUtils.asSet(args_classes).contains(obj.getClass())).toArray();

        //Print log message
        BEFORE_LOG_LOGGER.printMessage(String.format(LOG_MSG, required_args));
    }


    public void setLoggerLevel(LoggerLevel level) {
            BEFORE_LOG_LOGGER.setLoggerLevel(level);
    }

    @Override
    public void enable() {
        REGISTRY.enableAspect(BeforeLogAspect.class);
    }

    @Override
    public void disable() {
        REGISTRY.disableAspect(BeforeLogAspect.class);
    }

    @Override
    public boolean isEnabled() {
        return REGISTRY.isEnabled(BeforeLogAspect.class);
    }
}
