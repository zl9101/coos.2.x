package top.coos.tool;

import top.coos.Coos;

public class BaseTool extends Coos {

	private static final long serialVersionUID = 7780447215007777421L;

	public void a() {

		StackTraceElement element = StackTraceTool.getParent();
		System.out.println(element.getClassName());
		System.out.println(element.getFileName());
		System.out.println(element.getLineNumber());
		System.out.println(element.getMethodName());
	}
}
