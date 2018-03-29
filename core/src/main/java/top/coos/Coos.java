package top.coos;

import java.io.Serializable;

public class Coos implements Serializable, Cloneable {

	private static final long serialVersionUID = 8782856064271640724L;

	@Override
	protected Object clone() {

		try {
			return super.clone();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	protected StackTraceElement getStackTrace() {

		StackTraceElement[] stacks = new Throwable().getStackTrace();
		return stacks[1];
	}

	protected StackTraceElement getParentStackTrace() {

		StackTraceElement[] stacks = new Throwable().getStackTrace();
		return stacks[2];
	}

	protected StackTraceElement getFristStackTrace() {

		StackTraceElement[] elements = new Throwable().getStackTrace();
		return elements[elements.length - 1];
	}

}
