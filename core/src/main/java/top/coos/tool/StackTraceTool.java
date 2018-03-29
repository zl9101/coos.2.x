package top.coos.tool;

public class StackTraceTool {

	public static StackTraceElement get() {

		StackTraceElement[] stacks = new Throwable().getStackTrace();
		return stacks[1];
	}

	public static StackTraceElement getParent() {

		StackTraceElement[] stacks = new Throwable().getStackTrace();
		return stacks[2];
	}

	public static StackTraceElement getFrist() {

		StackTraceElement[] elements = new Throwable().getStackTrace();
		return elements[elements.length - 1];
	}
}
