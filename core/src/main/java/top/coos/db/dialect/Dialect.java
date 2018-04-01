package top.coos.db.dialect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Collection;

import top.coos.db.Entity;
import top.coos.db.dialect.impl.MysqlDialect;
import top.coos.db.meta.Column;
import top.coos.db.meta.Table;
import top.coos.db.sql.Query;
import top.coos.db.sql.Wrapper;

/**
 * SQL方言，不同的数据库由于在某些SQL上有所区别，故为每种数据库配置不同的方言。<br>
 * 由于不同数据库间SQL语句的差异，导致无法统一拼接SQL，<br>
 * Dialect接口旨在根据不同的数据库，使用不同的方言实现类，来拼接对应的SQL，并将SQL和参数放入PreparedStatement中
 * 
 * 
 *
 */
public abstract class Dialect {

	private final TypeNames typeNames = new TypeNames();
	protected Wrapper wrapper = new Wrapper();

	public Dialect() {

		registerColumnType(Types.BIT, "bit");
		registerColumnType(Types.BIGINT, "longint");
		registerColumnType(Types.SMALLINT, "smallint");
		registerColumnType(Types.TINYINT, "tinyint");
		registerColumnType(Types.INTEGER, "integer");
		registerColumnType(Types.CHAR, "char(1)");
		registerColumnType(Types.VARCHAR, "varchar($l)");
		registerColumnType(Types.FLOAT, "float");
		registerColumnType(Types.DOUBLE, "double precision");
		registerColumnType(Types.DATE, "date");
		registerColumnType(Types.TIME, "time");
		registerColumnType(Types.TIMESTAMP, "timestamp");
		registerColumnType(Types.VARBINARY, "bit varying($l)");
		registerColumnType(Types.NUMERIC, "numeric($p,$s)");
		registerColumnType(Types.BLOB, "blob");
		registerColumnType(Types.CLOB, "clob");
	}

	public Wrapper getWrapper() {

		return this.wrapper;
	}

	public void setWrapper(Wrapper wrapper) {

		this.wrapper = wrapper;
	}

	// -------------------------------------------- Execute
	/**
	 * 构建用于插入的PreparedStatement
	 * 
	 * @param conn
	 *            数据库连接对象
	 * @param entity
	 *            数据实体类（包含表名）
	 * @return PreparedStatement
	 * @throws SQLException
	 *             SQL执行异常
	 */
	public abstract PreparedStatement psForInsert(Connection conn, Entity entity) throws SQLException;

	/**
	 * 构建用于批量插入的PreparedStatement
	 * 
	 * @param conn
	 *            数据库连接对象
	 * @param entities
	 *            数据实体，实体的结构必须全部一致，否则插入结果将不可预知
	 * @return PreparedStatement
	 * @throws SQLException
	 *             SQL执行异常
	 */
	public abstract PreparedStatement psForInsertBatch(Connection conn, Entity... entities) throws SQLException;

	/**
	 * 构建用于删除的PreparedStatement
	 * 
	 * @param conn
	 *            数据库连接对象
	 * @param query
	 *            查找条件（包含表名）
	 * @return PreparedStatement
	 * @throws SQLException
	 *             SQL执行异常
	 */
	public abstract PreparedStatement psForDelete(Connection conn, Query query) throws SQLException;

	/**
	 * 构建用于更新的PreparedStatement
	 * 
	 * @param conn
	 *            数据库连接对象
	 * @param entity
	 *            数据实体类（包含表名）
	 * @param query
	 *            查找条件（包含表名）
	 * @return PreparedStatement
	 * @throws SQLException
	 *             SQL执行异常
	 */
	public abstract PreparedStatement psForUpdate(Connection conn, Entity entity, Query query) throws SQLException;

	// -------------------------------------------- Query
	/**
	 * 构建用于获取多条记录的PreparedStatement
	 * 
	 * @param conn
	 *            数据库连接对象
	 * @param query
	 *            查询条件（包含表名）
	 * @return PreparedStatement
	 * @throws SQLException
	 *             SQL执行异常
	 */
	public abstract PreparedStatement psForFind(Connection conn, Query query) throws SQLException;

	/**
	 * 构建用于分页查询的PreparedStatement
	 * 
	 * @param conn
	 *            数据库连接对象
	 * @param query
	 *            查询条件（包含表名）
	 * @return PreparedStatement
	 * @throws SQLException
	 *             SQL执行异常
	 */
	public abstract PreparedStatement psForPage(Connection conn, Query query) throws SQLException;

	/**
	 * 构建用于查询行数的PreparedStatement
	 * 
	 * @param conn
	 *            数据库连接对象
	 * @param query
	 *            查询条件（包含表名）
	 * @return PreparedStatement
	 * @throws SQLException
	 *             SQL执行异常
	 */
	public abstract PreparedStatement psForCount(Connection conn, Query query) throws SQLException;

	/**
	 * 方言名
	 * 
	 * @return 方言名
	 */
	public abstract DialectName dialectName();

	public String getAddColumnString() {

		return "add column";
	}

	/**
	 * 映射数据库布尔值的SQL值。
	 */
	public String toBooleanValueString(boolean bool) {

		return bool ? "1" : "0";
	}

	public String getTypeName(int code) throws Exception {

		String result = typeNames.get(code);
		if (result == null) {
			throw new Exception("No default type mapping for (java.sql.Types) " + code);
		}
		return result;
	}

	public String getTypeName(int code, int length, int precision, int scale) throws Exception {

		String result = typeNames.get(code, length, precision, scale);
		if (result == null) {
			throw new Exception(
					"No type mapping for java.sql.Types code: " +
							code +
							", length: " +
							length);
		}
		return result;
	}

	protected void registerColumnType(int code, int capacity, String name) {

		typeNames.put(code, capacity, name);
	}

	protected void registerColumnType(int code, String name) {

		typeNames.put(code, name);
	}

	public String createTableString() {

		return "create table ";
	}

	public String sqlForCreateTable(Table table) throws Exception {

		StringBuffer sql = new StringBuffer();
		sql.append(createTableString());
		sql.append(wrapper.getPreWrapQuote());
		sql.append(table.getTableName());
		sql.append(wrapper.getSufWrapQuote());
		sql.append("(");
		Collection<Column> cs = table.values();
		if (cs != null) {
			Column[] columns = new Column[cs.size()];
			cs.toArray(columns);
			for (int i = 0; i < columns.length; i++) {
				Column column = columns[i];
				sql.append(wrapper.getPreWrapQuote());
				sql.append(column.getName());
				sql.append(wrapper.getSufWrapQuote());
				sql.append(" ");
				String typeName = getTypeName(column.getType(), column.getSize(), column.getPrecision(), column.getScale());
				sql.append(typeName);
				sql.append(" ");

				if (i < columns.length - 1) {
					sql.append(",");
				}
			}
		}

		sql.append(")");
		return sql.toString();
	}

	public static void main(String[] args) throws Exception {

		Table table = new Table("abc");

		Column column = new Column();
		column.setComment("备注");
		column.setName("name");
		column.setNullable(true);
		column.setSize(20);
		column.setType(Types.VARCHAR);
		table.setColumn(column);

		Dialect dialect = new MysqlDialect();
		System.out.println(dialect.sqlForCreateTable(table));
	}

}
