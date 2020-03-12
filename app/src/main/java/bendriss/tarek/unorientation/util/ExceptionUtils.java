package bendriss.tarek.unorientation.util;

/**
 * this class represents an exception's utils
 */
public class ExceptionUtils {

    /**
     * this function foramts an exception
     * @param exception
     * @return
     */
    public static String format(Throwable exception) {
        String exceptionStr = "";
        final String message = getThrowableMessage(exception);
        final String cause = getThrowableCause(exception);
        exceptionStr += "MessageDb[" + message + "]" + "\n";
        exceptionStr += "Cause[" + cause + "]";
        return exceptionStr;
    }

    /**
     * this function returns the exception's cause
     * @param throwable
     * @return
     */
    public static String getThrowableCause(Throwable throwable) {
        String causeStr = "";
        if (throwable != null) {
            Throwable cause = throwable.getCause();
            while (cause != null) {
                causeStr = cause + "";
                cause = cause.getCause();
            }
        }
        return causeStr;
    }

    /**
     * this function returns the exception's message
     * @param throwable
     * @return
     */
    public static String getThrowableMessage(Throwable throwable) {
        String messageStr = "";
        if (throwable != null) {
            messageStr = throwable.getMessage();
        }
        return messageStr;
    }
}
