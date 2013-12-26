package cn.xiaoqiang.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 数据库管理类
 * 
 */
public final class JDBCManager {
	/**
	 * 数据库连接对象
	 */
	private Connection dbConnection = null;

	/**
	 * 数据库命令执行对象
	 */
	private PreparedStatement preStatement = null;

	/**
	 * 结果集对象
	 */
	private ResultSet rsSet = null;

	/**
	 * 数据库驱动版本号
	 */
	private static String driverVersion = null;

	/**
	 * 数据库服务器登录用户名和密码字符串常量(默认值均 为'sa')
	 */
	private static String databaseUser = "sa";

	private static String databasePassword = "sa";

	/**
	 * 数据库驱动完整类名字符串常量
	 */
	private static final String DRIVER_CLASS_SQLSERVER2000 = "com.microsoft.jdbc.sqlserver.SQLServerDriver"; // SQL2000

	private static final String DRIVER_CLASS_SQLSERVER2005 = "com.microsoft.sqlserver.jdbc.SQLServerDriver"; // SQL2005

	private static final String DRIVER_CLASS_BRIDGECONNECT = "sun.jdbc.odbc.JdbcOdbcDriver";

	// ODBC 桥连
	/**
	 * 数据库连接字符串常量
	 */
	private static final String DATABASE_URL_SQLSERVER2000 = "jdbc:microsoft:sqlserver://127.0.0.1:1433;DatabaseName=stuDB"; // SQL

	// Server
	// 2000
	// 直连
	private static final String DATABASE_URL_SQLSERVER2005 = "jdbc:sqlserver://127.0.0.1:1433;DatabaseName=stuDB";

	// SQL Server 2005 直连
	private static final String DATABASE_URL_BRIDGECONNECT = "jdbc:odbc:stuDBSource";

	// ODBC 桥连
	/**
	 * 定义类自身的实例静态变量(作用于单例[件]模式的应用)
	 */
	private static JDBCManager connectionManager = null;

	/**
	 * 私有化默认构造(作用于单例[件]模式的应用，防止类被直 接使用new关键字实例化)
	 */
	private JDBCManager() {
		super();
	}

	/**
	 * 获取数据库连接管理类实例的方法(单例[件]模式的应用)
	 * 
	 * @param version
	 *            数据库驱动版本号，取值：(version = 2000 | version = 2005 | version = odbc)
	 * @param user
	 *            数据库服务器登录用户名
	 * @param password
	 *            数据库服务器登录密码
	 * @return 数据库连接管理对象
	 * @throws Exception
	 *             参数错误异常
	 */
	public static JDBCManager getInstance(String version, String user,
			String password) throws Exception {
		if (!(version == "2000" || version == "2005" || version == "odbc")) {
			throw new Exception("数据库驱动版本号 不正确，取值只能是“2000/2005/odbc”！");
		}
		// 保存数据库驱动版本号
		driverVersion = version;
		if (user == null || user.equals("")) {
			throw new Exception("数据库服务器登录 用户名不能为空！");
		}
		// 保存数据库服务器登录用户名和密码
		databaseUser = user;
		databasePassword = password;
		// 应用单例[件]模式确保类本身只有一个实例
		if (connectionManager == null) {
			connectionManager = new JDBCManager();
		}
		// 返回类本身的实例
		return connectionManager;
	}

	/**
	 * 获取数据库连接的方法
	 * 
	 * @return 数据库连接对象
	 */
	private Connection getConnection() {
		try {
			Class.forName(driverVersion == "2000" ? DRIVER_CLASS_SQLSERVER2000
					: (driverVersion == "2005" ? DRIVER_CLASS_SQLSERVER2005
							: DRIVER_CLASS_BRIDGECONNECT));
			this.dbConnection = DriverManager
					.getConnection(
							driverVersion == "2000" ? DATABASE_URL_SQLSERVER2000
									: (driverVersion == "2005" ? DATABASE_URL_SQLSERVER2005
											: DATABASE_URL_BRIDGECONNECT),
							databaseUser, databasePassword);
		} catch (ClassNotFoundException ex) {
			System.err.println("未找到SQL Server " + driverVersion + "数据库驱动类："
					+ ex.getMessage());
			// 在控制台输出异常堆栈信息
			// ex.printStackTrace();
		} catch (Exception ex) {
			System.err.println("获取数据库连接错 误：" + ex.getMessage());
			// 在控制台输出异常堆栈信息
			// ex.printStackTrace();
		}
		// 返回数据库连接对象
		return this.dbConnection;
	}

	/**
	 * 获取数据库命令执行对象的方法
	 * 
	 * @param sql
	 *            要执行的SQL命令拼装语句字符串
	 * @return 数据库命令执行对象
	 */
	private PreparedStatement getPreparedStatement(String sql) {
		try {
			// 根据获取的数据库连接对象创建数据库 命令执行对象
			this.preStatement = getConnection().prepareStatement(sql);
		} catch (Exception ex) {
			System.err.println("获取数据库命令执 行对象错误：" + ex.getMessage());
			// 在控制台输出异常堆栈信息
			// ex.printStackTrace();
		}
		// 返回数据库命令执行对象
		return this.preStatement;
	}

	/**
	 * 执行更新语句(Insert|Update|Delete)
	 * 
	 * @param sql
	 *            要执行的SQL命令拼装语句字符串
	 * @return 受影响的行数
	 */
	public int executeUpdate(String sql) {
		try {
			// 置空结果集对象的原有内容
			this.rsSet = null;
			// 执行语句并返回受影响行数
			return this.getPreparedStatement(sql).executeUpdate();
		} catch (SQLException e) {
			System.err.println("更新数据错误：" + e.getMessage());
			return 0;
		} finally {
			// 关闭数据库连接资源
			closeDBResource();
		}
	}

	/**
	 * 执行查询语句(Select)
	 * 
	 * @param sql
	 *            要执行的SQL命令拼装语句字符串
	 * @return 查询后的结果集对象
	 */
	public ResultSet executeQuery(String sql) {
		try {
			// 置空结果集对象的原有内容
			this.rsSet = null;
			// 执行sql语句获得结果集
			this.rsSet = this.getPreparedStatement(sql).executeQuery();
		} catch (SQLException e) {
			System.err.println("查询数据错误：" + e.getMessage());
		}
		// 返回结果集对象
		return this.rsSet;
	}

	/**
	 * 获取执行指定sql语句后的返回结果集的记录条数
	 * 
	 * @param sql
	 *            要执行的SQL命令拼装语句字符串
	 * @return 查询结果得到的记录条数
	 */
	public int getResultSetCount(String sql) {
		// 保存得到指定的sql语句执行后返回记录行数的计数器变量
		int count = 0;
		try {
			// 置空结果集对象的原有内容
			this.rsSet = null;
			// 执行sql语句获得结果集
			this.rsSet = this.getPreparedStatement(sql).executeQuery();
			// 遍历结果集并累加计数器
			while (this.rsSet.next()) {
				count++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	/**
	 * 关闭数据库连接资源(包括结果集对象、命令执行对象、连 接对象)
	 */
	public void closeDBResource() {
		try {
			closeResultSet();
			closePreparedStatement();
			closeConnection();
		} catch (SQLException sqlEx) {
			System.err.println(sqlEx.getMessage());
			// 在控制台输出异常堆栈信息
			// sqlEx.printStackTrace();
		}
	}

	/**
	 * 关闭结果集对象的方法
	 * 
	 * @throws SQLException
	 */
	private void closeResultSet() throws SQLException {
		try {
			if (this.rsSet != null) {
				this.rsSet.close();
				this.rsSet = null;
			}
		} catch (SQLException sqlEx) {
			throw new SQLException("关闭结果集对 象错误：" + sqlEx.getMessage());
			// 在控制台输出异常堆栈信息
			// sqlEx.printStackTrace();
		}
	}

	/**
	 * 关闭数据库命令执行对象的方法
	 * 
	 * @throws SQLException
	 */
	private void closePreparedStatement() throws SQLException {
		try {
			if (this.preStatement != null) {
				this.preStatement.close();
				this.preStatement = null;
			}
		} catch (SQLException sqlEx) {
			throw new SQLException("关闭数据库命 令执行对象错误：" + sqlEx.getMessage());
			// 在控制台输出异常堆栈信息
			// sqlEx.printStackTrace();
		}
	}

	/**
	 * 关闭数据库连接的方法
	 * 
	 * @throws SQLException
	 */
	private void closeConnection() throws SQLException {
		try {
			if (this.dbConnection != null && (!this.dbConnection.isClosed())) {
				this.dbConnection.close();
			}
		} catch (SQLException sqlEx) {
			throw new SQLException("关闭数据库连 接错误：" + sqlEx.getMessage());
			// 在控制台输出异常堆栈信息
			// sqlEx.printStackTrace();
		}
	}
}
