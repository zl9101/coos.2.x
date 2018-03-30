package top.coos.poi.exceptions;

import top.coos.core.exceptions.ExceptionUtil;
import top.coos.util.StrUtil;

/**
 * POI异常

 */
public class POIException extends RuntimeException{
	private static final long serialVersionUID = 2711633732613506552L;

	public POIException(Throwable e) {
		super(ExceptionUtil.getMessage(e), e);
	}
	
	public POIException(String message) {
		super(message);
	}
	
	public POIException(String messageTemplate, Object... params) {
		super(StrUtil.format(messageTemplate, params));
	}
	
	public POIException(String message, Throwable throwable) {
		super(message, throwable);
	}
	
	public POIException(Throwable throwable, String messageTemplate, Object... params) {
		super(StrUtil.format(messageTemplate, params), throwable);
	}
}
