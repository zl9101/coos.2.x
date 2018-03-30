package top.coos.core.convert.impl;

import java.util.TimeZone;

import top.coos.core.convert.AbstractConverter;

/**
 * TimeZone转换器

 *
 */
public class TimeZoneConverter extends AbstractConverter<TimeZone>{

	@Override
	protected TimeZone convertInternal(Object value) {
		return TimeZone.getTimeZone(convertToStr(value));
	}

}
