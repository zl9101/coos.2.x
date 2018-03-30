package top.coos.core.convert.impl;

import java.io.File;
import java.net.URI;
import java.net.URL;

import top.coos.core.convert.AbstractConverter;

/**
 * 字符串转换器

 *
 */
public class URIConverter extends AbstractConverter<URI>{

	@Override
	protected URI convertInternal(Object value) {
		try {
			if(value instanceof File){
				return ((File)value).toURI();
			}
			
			if(value instanceof URL){
				return ((URL)value).toURI();
			}
			return new URI(convertToStr(value));
		} catch (Exception e) {
			// Ignore Exception
		}
		return null;
	}

}
