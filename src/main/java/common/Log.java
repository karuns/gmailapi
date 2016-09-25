package common;

public class Log {
	public static void pass(String message) {
		System.out.println("PASS  -"+message);
	}
	
	public static void error(String message) {
		System.out.println("ERROR  -"+message);
	}
	
	public static void fatal(String message) {
		System.out.println("FATAL  -"+message);
	}
	
	public static void info(String message) {
		System.out.println("INFO  -"+message);
	}
	
	
	public static void testPassed() {
		pass(enteringModule("Test Passed"));
    }
	
	public static void testPassed(String text) {
		pass(enteringModule("Test Passed")+" with "+text);
    }
	
	
	public static void testFailed(String text) {
		error(enteringModule("Test Failed"));
    }
	
	public static void testFailed() {
		error(enteringModule("Test Failed"));
    }
	
	private static String enteringModule(String strText) {
        StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
        StackTraceElement e = stacktrace[3];//maybe this number needs to be corrected
        return strText + ": " + e.getClassName() + "." + e.getMethodName() + " ....";
    }
}
