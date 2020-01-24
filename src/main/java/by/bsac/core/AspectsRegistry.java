package by.bsac.core;

import by.bsac.annotations.logging.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;

@Singleton
public class AspectsRegistry {

    //Logger
    private static final Logger LOGGER = LoggerFactory.getLogger(AspectsRegistry.class);
    //Class variables
    private static final AspectsRegistry REGISTRY = new AspectsRegistry();
    private final Set<Class<?>> ENABLED_ASPECTS = new HashSet<>();

    //Private constructor
    private AspectsRegistry() {
        LOGGER.debug("Create singleton instance of AspectRegistry registry.");
    }

    /**
     * Method return singleton instance of {@link AspectsRegistry} registry.
     * @return - {@link AspectsRegistry} registry.
     */
    public static AspectsRegistry getInstance() {
        LOGGER.debug("Return shared singleton instance of AspectRegistry registry.");
        return REGISTRY;
    }

    public void enableAspect(Class<?> aspect_class) {
        LOGGER.debug(String.format("Enable [%s] aspect.", aspect_class.getCanonicalName()));

        if (this.ENABLED_ASPECTS.contains(aspect_class)) {
            LOGGER.debug(String.format("Aspect [%s] already enabled.", aspect_class.getCanonicalName()));
            return;
        }

        this.ENABLED_ASPECTS.add(aspect_class);
    }

    public void disableAspect(Class<?> aspect_class) {
        LOGGER.debug(String.format("Disable [%s] aspect.", aspect_class.getCanonicalName()));

        if (!this.ENABLED_ASPECTS.contains(aspect_class)) {
            LOGGER.debug(String.format("Aspect [%s] already disabled.", aspect_class.getCanonicalName()));
            return;
        }

        this.ENABLED_ASPECTS.remove(aspect_class);
    }

    public boolean isEnabled(Class<?> aspect_class) {
        return this.ENABLED_ASPECTS.contains(aspect_class);
    }

}
