package by.bsac.core.logging;

/**
 * Commons logging strings messages for Spring framework.
 * Include log messages for beans creation, dependency management, configuration initialization and etc.
 */
public class SpringCommonLogging {

    /**
     * Class has a methods to gets logs messages
     * for spring bean's creation process.
     */
    @SuppressWarnings("AccessStaticViaInstance")
    public static class CREATION {

        private static final String START_MSG_LOG = "Start of create [%s];";
        private static final String END_MSG_LOG = "End of create [%s];";
        private static final String EXCEPTION_MSG_LOG = "Creation of [%s] throw exception [%s]";

        /**
         * Method return "Start of create[BEAN_DEFINITION];" log message.
         * @param bean_definition - {@link BeanDefinition} custom bean definition.
         * @return - {@link String} log message.
         */
        public static String startCreateBean(BeanDefinition bean_definition) {
            return String.format(START_MSG_LOG, bean_definition.getBeanName());
        }

        /**
         * Method return "End of create[BEAN_DEFINITION];" log message.
         * @param bean_definition - {@link BeanDefinition} custom bean definition.
         * @return - {@link String} log message.
         */
        public static String endCreateBean(BeanDefinition bean_definition) {
            return String.format(END_MSG_LOG, bean_definition.getBeanName());
        }

        /**
         * Method return "Creation of [BEAN_DEFINITION] throw exception [EXCEPTION_CLASS];" log message.
         * @param bean_definition - {@link BeanDefinition} custom bean definition.
         * @param exc_class - {@link Exception} class.
         * @return - {@link String} log message.
         */
        public static String creationThrowException(BeanDefinition bean_definition, Class<? extends Exception> exc_class) {
            return String.format(EXCEPTION_MSG_LOG +';', bean_definition.getBeanName(), exc_class.getCanonicalName());
        }

        /**
         * Method return "Creation of [BEAN_DEFINITION] throw exception [EXCEPTION_CLASS];" log message.
         * @param bean_definition - {@link BeanDefinition} custom bean definition.
         * @param exc - {@link Exception} object.
         * @return - {@link String} log message.
         */
        public static String creationThrowException(BeanDefinition bean_definition, Exception exc) {
            return String.format(EXCEPTION_MSG_LOG +';', bean_definition.getBeanName(), exc.getClass().getCanonicalName());
        }

        /**
         * Method return "Creation of [BEAN_DEFINITION] throw exception [EXCEPTION_CLASS] with message: EXCEPTION_MESSAGE;" log message.
         * @param bean_definition - {@link BeanDefinition} custom bean definition.
         * @param exc - {@link Exception} class.
         * @return - {@link String} log message.
         */
        public static String creationThrowExceptionWithMessage(BeanDefinition bean_definition, Exception exc) {
            return String.format(EXCEPTION_MSG_LOG +" with message: %s;", bean_definition.getBeanName(), exc.getClass().getCanonicalName(), exc.getMessage());
        }

    }

    /**
     * Class has methods to create custom log strings which describe concretes spring beans.
     */
    public static class BeanDefinition {

        private static String bean_name = "Bean"; //Default bean name
        private static final String OF_CLASS = " of class [%s]";
        private static final String WITH_NAME = " with name \"%s\"";
        private static final String FOR_GENERIC_TYPE_MSG = " for generic type [%s]";

        /*
            Private constructor for BeanDefinition static methods.
         */
        private BeanDefinition(String a_bean_name) {
            bean_name = a_bean_name;
        }

        /**
         * Method create new {@link BeanDefinition} based on spring bean class.
         * @param bean_class - bean {@link Class};
         * @return - "Bean of class [BEAN_CLASS]"
         */
        public static BeanDefinition of(Class<?> bean_class) {
            return new BeanDefinition(String.format(bean_name + OF_CLASS, bean_class.getCanonicalName()));
        }

        /**
         * Method create new {@link BeanDefinition} based on spring bean name.
         * @param a_bean_name - bean {@link String} name;
         * @return - "Bean of class [BEAN_CLASS]"
         */
        public static BeanDefinition of(String a_bean_name) {
            return new BeanDefinition(String.format(bean_name +WITH_NAME, a_bean_name));
        }

        /**
         * Method used to complement custom {@link BeanDefinition} with bean class.
         * Note: To create {@link BeanDefinition} use {@link BeanDefinition#of(Class) or {@link BeanDefinition#of(String)}} methods.
         * @param bean_class - bean {@link Class};
         * @return - Complement existing {@link BeanDefinition} with "of class [BEAN_CLASS]" message log.
         */
        public static BeanDefinition ofClass(Class<?> bean_class) {
            return new BeanDefinition(String.format(bean_name + OF_CLASS, bean_class.getCanonicalName()));
        }

        /**
         * Method used to complement custom {@link BeanDefinition} with bean name option.
         * Note: To create {@link BeanDefinition} use {@link BeanDefinition#of(Class) or {@link BeanDefinition#of(String)}} methods.
         * @param a_bean_name - bean {@link String} name;
         * @return - Complement existing {@link BeanDefinition} with "with  name [BEAN_NAME]" message log.
         */
        public static BeanDefinition withName(String a_bean_name) {
            final String WITH_NAME = " with name \"%s\"";
            return new BeanDefinition(String.format(bean_name +WITH_NAME, a_bean_name));
        }

        /**
         * Method used to complement custom {@link BeanDefinition} with bean generic type option.
         * Note: To create {@link BeanDefinition} use {@link BeanDefinition#of(Class) or {@link BeanDefinition#of(String)}} methods.
         * @param generic_class - bean {@link Class} generic type;
         * @return - Complement existing {@link BeanDefinition} with "for generic type [GENERIC_TYPE_CLASS]" message log.
         */
        public static BeanDefinition forGenericType(Class<?> generic_class) {
            return new BeanDefinition(String.format(bean_name + FOR_GENERIC_TYPE_MSG, generic_class.getCanonicalName()));
        }

        /**
         * Method used to complement custom {@link BeanDefinition} with bean profile option.
         * Note: To create {@link BeanDefinition} use {@link BeanDefinition#of(Class) or {@link BeanDefinition#of(String)}} methods.
         * @param profile - bean {@link String} profile.
         * @return - Complement existing {@link BeanDefinition} with "for profile [APPLICATION_PROFILE]" message log.
         */
        public static BeanDefinition forProfile(String profile) {
            final String FOR_PROFILE_MSG = " for profile [%s]";
            return new BeanDefinition(String.format(bean_name + FOR_PROFILE_MSG, profile));
        }

        /**
         * Method return {@link BeanDefinition} message log string.
         * @return - {@link BeanDefinition} message log string.
         */
        public static String getBeanName() {
            String result = bean_name;
            bean_name = "Bean";
            return result;
        }

    }

    /**
     * Class has methods that's describe initialization of Spring configuration classes.
     */
    public static class INITIALIZATION {

        /**
         * Method return message string log about start of configuration class initialization .
         * @param configuration_class - {@link Class} configuration class.
         * @return - {@link String} "Start of initialization [CONFIGURATION_CLASS] configuration class";
         */
        public static String startInitializeConfiguration(Class<?> configuration_class) {
            return String.format("Start of initialization [%s] configuration class;", configuration_class.getCanonicalName());
        }

        /**
         * Method return message string log about end of configuration class initialization .
         * @param configuration_class - {@link Class} configuration class.
         * @return - {@link String} "End of initialization [CONFIGURATION_CLASS] configuration class";
         */
        public static String endInitializeConfiguration(Class<?> configuration_class) {
            return String.format("End of initialization [%s] configuration class;", configuration_class.getCanonicalName());
        }

    }

    @SuppressWarnings("AccessStaticViaInstance")
    public static class DependencyManagement {

        private static final String AUTOWIRE_VIA_MSG = "Autowire [%s] to [%s] via [%s] parameter;";
        private static final String SET_VIA_MSG = "Set [%s] to [%s] via [%s] parameter;";

        public static String autowireViaSetter(BeanDefinition of, Class<?> to) {
            return String.format(AUTOWIRE_VIA_MSG, of.getBeanName(), to.getCanonicalName(), "SETTER");
        }

        public static String autowireViaConstructor(BeanDefinition of, Class<?> to) {
            return String.format(AUTOWIRE_VIA_MSG, of.getBeanName(), to.getCanonicalName(), "CONSTRUCTOR");
        }

        public static String setViaSetter(BeanDefinition of, Class<?> to) {
            return String.format(SET_VIA_MSG, of.getBeanName(), to.getCanonicalName(), "SETTER");
        }

        public static String setViaConstructor(BeanDefinition of, Class<?> to) {
            return String.format(SET_VIA_MSG, of.getBeanName(), to.getCanonicalName(), "CONSTRUCTOR");
        }

        public static class Exceptions {

            private static final String NULL_PROPERTY_EXCEPTION_MSG = "Spring bean dependency of [%s] bean is null;";

            public static String nullProperty( Class<?> bean_class) {
                return String.format(NULL_PROPERTY_EXCEPTION_MSG, bean_class.getCanonicalName());
            }
            
        }

    }

}
