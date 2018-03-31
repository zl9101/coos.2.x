package top.coos.logger;

import org.slf4j.Logger;
import org.slf4j.Marker;

public class Log implements Logger {

	private Logger logger;

	public Log(Logger logger) {

		this.logger = logger;
	}

	private void initTrace() {

	}

	public void info(String message) {

		this.initTrace();
		logger.info(message);
	}

	public void info(String message, Throwable throwable) {

		this.initTrace();
		logger.info(message, throwable);
	}

	public void warn(String message) {

		this.initTrace();
		logger.warn(message);
	}

	public void warn(String message, Throwable throwable) {

		this.initTrace();
		logger.warn(message, throwable);
	}

	public void error(String message) {

		this.initTrace();
		logger.error(message);
	}

	public void error(String message, Throwable throwable) {

		this.initTrace();
		logger.error(message, throwable);
	}

	public void debug(String message) {

		this.initTrace();
		logger.debug(message);
	}

	public void debug(String message, Throwable throwable) {

		this.initTrace();
		logger.debug(message, throwable);
	}

	public void trace(String message) {

		this.initTrace();
		logger.trace(message);
	}

	public void trace(String message, Throwable throwable) {

		this.initTrace();
		logger.trace(message, throwable);
	}

	@Override
	public String getName() {

		return logger.getName();
	}

	@Override
	public boolean isTraceEnabled() {

		return logger.isTraceEnabled();
	}

	@Override
	public void trace(String format, Object arg) {

		this.initTrace();
		logger.trace(format, arg);
	}

	@Override
	public void trace(String format, Object arg1, Object arg2) {

		this.initTrace();
		logger.trace(format, arg1, arg2);
	}

	@Override
	public void trace(String format, Object... arguments) {

		this.initTrace();
		logger.trace(format, arguments);

	}

	@Override
	public boolean isTraceEnabled(Marker marker) {

		this.initTrace();
		return logger.isTraceEnabled(marker);
	}

	@Override
	public void trace(Marker marker, String msg) {

		this.initTrace();
		logger.trace(marker, msg);

	}

	@Override
	public void trace(Marker marker, String format, Object arg) {

		this.initTrace();
		logger.trace(marker, format, arg);

	}

	@Override
	public void trace(Marker marker, String format, Object arg1, Object arg2) {

		this.initTrace();
		logger.trace(marker, format, arg1, arg2);

	}

	@Override
	public void trace(Marker marker, String format, Object... argArray) {

		this.initTrace();
		logger.trace(marker, format, argArray);

	}

	@Override
	public void trace(Marker marker, String msg, Throwable t) {

		this.initTrace();
		logger.trace(marker, msg, t);

	}

	@Override
	public boolean isDebugEnabled() {

		return logger.isDebugEnabled();
	}

	@Override
	public void debug(String format, Object arg) {

		this.initTrace();
		logger.debug(format, arg);

	}

	@Override
	public void debug(String format, Object arg1, Object arg2) {

		this.initTrace();
		logger.debug(format, arg1, arg2);

	}

	@Override
	public void debug(String format, Object... arguments) {

		this.initTrace();
		logger.debug(format, arguments);

	}

	@Override
	public boolean isDebugEnabled(Marker marker) {

		return logger.isDebugEnabled(marker);
	}

	@Override
	public void debug(Marker marker, String msg) {

		this.initTrace();
		logger.debug(marker, msg);

	}

	@Override
	public void debug(Marker marker, String format, Object arg) {

		this.initTrace();
		logger.debug(marker, format, arg);
	}

	@Override
	public void debug(Marker marker, String format, Object arg1, Object arg2) {

		this.initTrace();
		logger.debug(marker, format, arg1, arg2);

	}

	@Override
	public void debug(Marker marker, String format, Object... arguments) {

		this.initTrace();
		logger.debug(marker, format, arguments);

	}

	@Override
	public void debug(Marker marker, String msg, Throwable t) {

		this.initTrace();
		logger.debug(marker, msg, t);

	}

	@Override
	public boolean isInfoEnabled() {

		return logger.isInfoEnabled();
	}

	@Override
	public void info(String format, Object arg) {

		this.initTrace();
		logger.info(format, arg);

	}

	@Override
	public void info(String format, Object arg1, Object arg2) {

		this.initTrace();
		logger.info(format, arg1, arg2);

	}

	@Override
	public void info(String format, Object... arguments) {

		this.initTrace();
		logger.info(format, arguments);

	}

	@Override
	public boolean isInfoEnabled(Marker marker) {

		return logger.isInfoEnabled(marker);
	}

	@Override
	public void info(Marker marker, String msg) {

		this.initTrace();
		logger.info(marker, msg);

	}

	@Override
	public void info(Marker marker, String format, Object arg) {

		this.initTrace();
		logger.info(marker, format, arg);

	}

	@Override
	public void info(Marker marker, String format, Object arg1, Object arg2) {

		this.initTrace();
		logger.info(marker, format, arg1, arg2);
	}

	@Override
	public void info(Marker marker, String format, Object... arguments) {

		this.initTrace();
		logger.info(marker, format, arguments);
	}

	@Override
	public void info(Marker marker, String msg, Throwable t) {

		this.initTrace();
		logger.info(marker, msg, t);
	}

	@Override
	public boolean isWarnEnabled() {

		return logger.isWarnEnabled();
	}

	@Override
	public void warn(String format, Object arg) {

		this.initTrace();
		logger.warn(format, arg);

	}

	@Override
	public void warn(String format, Object arg1, Object arg2) {

		this.initTrace();
		logger.warn(format, arg1, arg2);

	}

	@Override
	public void warn(String format, Object... arguments) {

		this.initTrace();
		logger.warn(format, arguments);

	}

	@Override
	public boolean isWarnEnabled(Marker marker) {

		return logger.isWarnEnabled(marker);
	}

	@Override
	public void warn(Marker marker, String msg) {

		this.initTrace();
		logger.warn(marker, msg);

	}

	@Override
	public void warn(Marker marker, String format, Object arg) {

		this.initTrace();
		logger.warn(marker, format, arg);
	}

	@Override
	public void warn(Marker marker, String format, Object arg1, Object arg2) {

		this.initTrace();
		logger.warn(marker, format, arg1, arg2);

	}

	@Override
	public void warn(Marker marker, String format, Object... arguments) {

		this.initTrace();
		logger.warn(marker, format, arguments);

	}

	@Override
	public void warn(Marker marker, String msg, Throwable t) {

		this.initTrace();
		logger.warn(marker, msg, t);

	}

	@Override
	public boolean isErrorEnabled() {

		return logger.isErrorEnabled();
	}

	@Override
	public void error(String format, Object arg) {

		this.initTrace();
		logger.error(format, arg);

	}

	@Override
	public void error(String format, Object arg1, Object arg2) {

		this.initTrace();
		logger.error(format, arg1, arg2);

	}

	@Override
	public void error(String format, Object... arguments) {

		this.initTrace();
		logger.error(format, arguments);

	}

	@Override
	public boolean isErrorEnabled(Marker marker) {

		return logger.isErrorEnabled(marker);
	}

	@Override
	public void error(Marker marker, String msg) {

		this.initTrace();
		logger.error(marker, msg);

	}

	@Override
	public void error(Marker marker, String format, Object arg) {

		this.initTrace();
		logger.error(marker, format, arg);
	}

	@Override
	public void error(Marker marker, String format, Object arg1, Object arg2) {

		this.initTrace();
		logger.error(marker, format, arg1, arg2);

	}

	@Override
	public void error(Marker marker, String format, Object... arguments) {

		this.initTrace();
		logger.error(marker, format, arguments);

	}

	@Override
	public void error(Marker marker, String msg, Throwable t) {

		this.initTrace();
		logger.error(marker, msg, t);

	}

}
