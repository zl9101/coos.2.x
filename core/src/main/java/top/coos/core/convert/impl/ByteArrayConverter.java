package top.coos.core.convert.impl;

import top.coos.core.convert.AbstractConverter;
import top.coos.core.convert.ConverterRegistry;
import top.coos.util.ArrayUtil;

/**
 * byte 类型数组转换器

 *
 */
public class ByteArrayConverter extends AbstractConverter<byte[]>{
	
	@Override
	protected byte[] convertInternal(Object value) {
		final Byte[] result = ConverterRegistry.getInstance().convert(Byte[].class, value);
		return ArrayUtil.unWrap(result);
	}

}
