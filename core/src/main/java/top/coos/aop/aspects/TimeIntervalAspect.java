package top.coos.aop.aspects;

import java.lang.reflect.Method;

import org.slf4j.Logger;

import top.coos.core.date.TimeInterval;
import top.coos.log.LogFactory;

/**
 * 通过日志打印方法的执行时间的切面
 * 

 *
 */
public class TimeIntervalAspect extends SimpleAspect {

	private static final Logger log = LogFactory.get();

	public TimeIntervalAspect(Object target) {

		super(target);
	}

	private TimeInterval interval = new TimeInterval();

	@Override
	public boolean before(Object target, Method method, Object[] args) {

		interval.start();
		return true;
	}

	@Override
	public boolean after(Object target, Method method, Object[] args) {

		log.info("Method [{}.{}] execute spend [{}]ms", target.getClass().getName(), method.getName(), interval.intervalMs());
		return true;
	}
}
