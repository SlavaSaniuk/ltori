package by.bsac.aspects;

/**
 * Interface define methods that's describe aspects work.
 */
public interface ConfigurableAspects {

    /**
     * Implement this method in target aspect class to be able enable aspect.
     */
    void enable();

    /**
     * Implement this method in target aspect class to be able disable aspect.
     */
    void disable();

    /**
     * Implement this method to get's aspect state (Enabled/Disabled);
     * @return - true - if aspect enabled.
     */
    boolean isEnabled();

}
