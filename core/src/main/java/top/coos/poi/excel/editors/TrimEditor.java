package top.coos.poi.excel.editors;

import org.apache.poi.ss.usermodel.Cell;

import top.coos.poi.excel.CellEditor;
import top.coos.util.StrUtil;

/**
 * 去除String类型的单元格值两边的空格

 *
 */
public class TrimEditor implements CellEditor{

	@Override
	public Object edit(Cell cell, Object value) {
		if(value instanceof String) {
			return StrUtil.trim((String)value);
		}
		return value;
	}

}
