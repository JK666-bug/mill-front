package Util;

/**
 * Method Util
 *
 * @author JK
 */
public class MethodUtil {
    public static void runWithThread(Runnable runnable) {
        new Thread(runnable).start();
    }
}
