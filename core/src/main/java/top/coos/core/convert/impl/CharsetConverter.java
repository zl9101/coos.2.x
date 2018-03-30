package top.coos.core.convert.impl;

import java.nio.charset.Charset;

import top.coos.core.convert.AbstractConverter;
import top.coos.util.CharsetUtil;

/**
 * 编码对象转换器

 *
 */
public class CharsetConverter extends AbstractConverter<Charset>{

	@Override
	protected Charset convertInternal(Object value) {
		return CharsetUtil.charset(convertToStr(value));
	}

}
