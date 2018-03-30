package top.coos.db.dialect.impl;

import top.coos.db.Page;
import top.coos.db.dialect.DialectName;
import top.coos.db.sql.SqlBuilder;
import top.coos.db.sql.Wrapper;

/**
 * MySQL方言

 *
 */
public class MysqlDialect extends AnsiSqlDialect{
	
	public MysqlDialect() {
		wrapper = new Wrapper('`');
	}

	@Override
	protected SqlBuilder wrapPageSql(SqlBuilder find, Page page) {
		return find.append(" LIMIT ").append(page.getStartPosition()).append(", ").append(page.getPageSize());
	}
	
	@Override
	public DialectName dialectName() {
		return DialectName.MYSQL;
	}
}
