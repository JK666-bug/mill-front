package util;

/**
 * Method util
 *
 * @author JK
 */
public class MethodUtil {
    public static void runWithThread(Runnable runnable) {
        new Thread(runnable).start();
    }
}
