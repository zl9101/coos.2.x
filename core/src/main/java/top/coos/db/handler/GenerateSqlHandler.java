package top.coos.db.handler;

import top.coos.db.dialect.Dialect;
import top.coos.db.meta.Table;

/**
 * 
 * 
 */
public class GenerateSqlHandler {

	private final Dialect dialect;

	public GenerateSqlHandler(Dialect dialect) {

		this.dialect = dialect;
	}

	public static GenerateSqlHandler create(Dialect dialect) {

		return new GenerateSqlHandler(dialect);
	}

	public String createTable(Table table) throws Exception {

		StringBuffer sql = new StringBuffer();
		sql.append(dialect.sqlForCreateTable(table));
		return sql.toString();
	}
}
