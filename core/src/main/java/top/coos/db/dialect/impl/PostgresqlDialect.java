package top.coos.db.dialect.impl;

import top.coos.db.dialect.DialectName;
import top.coos.db.sql.Wrapper;


/**
 * Postgree方言

 *
 */
public class PostgresqlDialect extends AnsiSqlDialect{
	public PostgresqlDialect() {
		wrapper = new Wrapper('"');
	}

	@Override
	public DialectName dialectName() {
		return DialectName.POSTGREESQL;
	}
}
