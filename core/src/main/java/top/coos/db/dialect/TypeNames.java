package top.coos.db.dialect;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import top.coos.util.StrUtil;

public class TypeNames {

	private Map<Integer, Map<Integer, String>> weighted = new HashMap<Integer, Map<Integer, String>>();
	private Map<Integer, String> defaults = new HashMap<Integer, String>();

	public String get(int typecode) throws Exception {

		String result = (String) defaults.get(new Integer(typecode));
		if (result == null)
			throw new Exception("No Dialect mapping for JDBC type: " + typecode);
		return result;
	}

	public String get(int typecode, int size, int precision, int scale) throws Exception {

		Map<Integer, String> map = weighted.get(new Integer(typecode));
		if (map != null && map.size() > 0) {
			// iterate entries ordered by capacity to find first fit
			Iterator<Map.Entry<Integer, String>> entries = map.entrySet().iterator();
			while (entries.hasNext()) {
				Map.Entry<Integer, String> entry = (Map.Entry<Integer, String>) entries.next();
				if (size <= ((Integer) entry.getKey()).intValue()) {
					return replace((String) entry.getValue(), size, precision, scale);
				}
			}
		}
		return replace(get(typecode), size, precision, scale);
	}

	private static String replace(String type, int size, int precision, int scale) {

		type = StrUtil.replace(type, "$s", Integer.toString(scale));
		type = StrUtil.replace(type, "$l", Integer.toString(size));
		return StrUtil.replace(type, "$p", Integer.toString(precision));
	}

	public void put(int typecode, int capacity, String value) {

		Map<Integer, String> map = weighted.get(new Integer(typecode));
		if (map == null) {
			map = new TreeMap<Integer, String>();
			weighted.put(new Integer(typecode), map);
		}
		map.put(new Integer(capacity), value);
	}

	public void put(int typecode, String value) {

		defaults.put(new Integer(typecode), value);
	}
}
