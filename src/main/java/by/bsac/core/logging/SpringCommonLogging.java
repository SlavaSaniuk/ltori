package by.bsac.core.logging;

public class SpringCommonLogging {

    public static class CREATION {

        private static String log_msg = "";

        private CREATION(String a_log_msg) {
            log_msg = a_log_msg;
        }

        public static CREATION startCreateBean(Class<?> bean_class) {
            final String START_CREATING_BEAN = "Start creating bean [%s]";
            return new CREATION(String.format("Start create bean [%s]", bean_class.getCanonicalName()));
        }

        public static CREATION startCreateBean(String bean_name) {
            final String START_CREATING_BEAN = "Start creating bean \"%s\"";
            return new CREATION(String.format(START_CREATING_BEAN, bean_name));
        }

        public static CREATION forGenericType(Class<?> generic_class) {
            final String FOR_GENERIC_TYPE_MSG = " for generic type [%s]";
            return new CREATION(String.format(log_msg + FOR_GENERIC_TYPE_MSG, generic_class.getCanonicalName()));
        }

        public static CREATION ofType(Class<?> bean_type) {
            final String OF_TYPE_MSG = " of type [%s]";
            return new CREATION(String.format(log_msg + OF_TYPE_MSG, bean_type.getCanonicalName()));
        }

        public static CREATION forProfile(String profile) {
            final String FOR_PROFILE_MSG = " for profile [%s]";
            return new CREATION(String.format(log_msg + FOR_PROFILE_MSG, profile));
        }



        public static String log() {
            return log_msg +";";
        }

    }


}
