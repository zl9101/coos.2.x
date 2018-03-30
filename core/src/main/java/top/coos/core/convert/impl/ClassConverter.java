package top.coos.core.convert.impl;

import top.coos.core.convert.AbstractConverter;
import top.coos.util.ClassUtil;

/**
 * 类转换器<br>
 * 将类名转换为类

 *
 */
public class ClassConverter extends AbstractConverter<Class<?>>{
	
	@Override
	protected Class<?> convertInternal(Object value) {
		String valueStr = convertToStr(value);
		try {
			return ClassUtil.getClassLoader().loadClass(valueStr);
		} catch (Exception e) {
			// Ignore Exception
		}
		return null;
	}

}
