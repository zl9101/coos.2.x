package top.coos.db.dialect.impl;

import top.coos.db.dialect.DialectName;
import top.coos.db.sql.Wrapper;

/**
 * SqlLite3方言

 *
 */
public class Sqlite3Dialect extends AnsiSqlDialect{
	public Sqlite3Dialect() {
		wrapper = new Wrapper('[', ']');
	}
	
	@Override
	public DialectName dialectName() {
		return DialectName.SQLITE3;
	}
}
