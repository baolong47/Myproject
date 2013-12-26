package cn.xiaoqiang.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * ���ݿ������
 * 
 */
public final class JDBCManager {
	/**
	 * ���ݿ����Ӷ���
	 */
	private Connection dbConnection = null;

	/**
	 * ���ݿ�����ִ�ж���
	 */
	private PreparedStatement preStatement = null;

	/**
	 * ���������
	 */
	private ResultSet rsSet = null;

	/**
	 * ���ݿ������汾��
	 */
	private static String driverVersion = null;

	/**
	 * ���ݿ��������¼�û����������ַ�������(Ĭ��ֵ�� Ϊ'sa')
	 */
	private static String databaseUser = "sa";

	private static String databasePassword = "sa";

	/**
	 * ���ݿ��������������ַ�������
	 */
	private static final String DRIVER_CLASS_SQLSERVER2000 = "com.microsoft.jdbc.sqlserver.SQLServerDriver"; // SQL2000

	private static final String DRIVER_CLASS_SQLSERVER2005 = "com.microsoft.sqlserver.jdbc.SQLServerDriver"; // SQL2005

	private static final String DRIVER_CLASS_BRIDGECONNECT = "sun.jdbc.odbc.JdbcOdbcDriver";

	// ODBC ����
	/**
	 * ���ݿ������ַ�������
	 */
	private static final String DATABASE_URL_SQLSERVER2000 = "jdbc:microsoft:sqlserver://127.0.0.1:1433;DatabaseName=stuDB"; // SQL

	// Server
	// 2000
	// ֱ��
	private static final String DATABASE_URL_SQLSERVER2005 = "jdbc:sqlserver://127.0.0.1:1433;DatabaseName=stuDB";

	// SQL Server 2005 ֱ��
	private static final String DATABASE_URL_BRIDGECONNECT = "jdbc:odbc:stuDBSource";

	// ODBC ����
	/**
	 * �����������ʵ����̬����(�����ڵ���[��]ģʽ��Ӧ��)
	 */
	private static JDBCManager connectionManager = null;

	/**
	 * ˽�л�Ĭ�Ϲ���(�����ڵ���[��]ģʽ��Ӧ�ã���ֹ�౻ֱ ��ʹ��new�ؼ���ʵ����)
	 */
	private JDBCManager() {
		super();
	}

	/**
	 * ��ȡ���ݿ����ӹ�����ʵ���ķ���(����[��]ģʽ��Ӧ��)
	 * 
	 * @param version
	 *            ���ݿ������汾�ţ�ȡֵ��(version = 2000 | version = 2005 | version = odbc)
	 * @param user
	 *            ���ݿ��������¼�û���
	 * @param password
	 *            ���ݿ��������¼����
	 * @return ���ݿ����ӹ������
	 * @throws Exception
	 *             ���������쳣
	 */
	public static JDBCManager getInstance(String version, String user,
			String password) throws Exception {
		if (!(version == "2000" || version == "2005" || version == "odbc")) {
			throw new Exception("���ݿ������汾�� ����ȷ��ȡֵֻ���ǡ�2000/2005/odbc����");
		}
		// �������ݿ������汾��
		driverVersion = version;
		if (user == null || user.equals("")) {
			throw new Exception("���ݿ��������¼ �û�������Ϊ�գ�");
		}
		// �������ݿ��������¼�û���������
		databaseUser = user;
		databasePassword = password;
		// Ӧ�õ���[��]ģʽȷ���౾��ֻ��һ��ʵ��
		if (connectionManager == null) {
			connectionManager = new JDBCManager();
		}
		// �����౾���ʵ��
		return connectionManager;
	}

	/**
	 * ��ȡ���ݿ����ӵķ���
	 * 
	 * @return ���ݿ����Ӷ���
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
			System.err.println("δ�ҵ�SQL Server " + driverVersion + "���ݿ������ࣺ"
					+ ex.getMessage());
			// �ڿ���̨����쳣��ջ��Ϣ
			// ex.printStackTrace();
		} catch (Exception ex) {
			System.err.println("��ȡ���ݿ����Ӵ� ��" + ex.getMessage());
			// �ڿ���̨����쳣��ջ��Ϣ
			// ex.printStackTrace();
		}
		// �������ݿ����Ӷ���
		return this.dbConnection;
	}

	/**
	 * ��ȡ���ݿ�����ִ�ж���ķ���
	 * 
	 * @param sql
	 *            Ҫִ�е�SQL����ƴװ����ַ���
	 * @return ���ݿ�����ִ�ж���
	 */
	private PreparedStatement getPreparedStatement(String sql) {
		try {
			// ���ݻ�ȡ�����ݿ����Ӷ��󴴽����ݿ� ����ִ�ж���
			this.preStatement = getConnection().prepareStatement(sql);
		} catch (Exception ex) {
			System.err.println("��ȡ���ݿ�����ִ �ж������" + ex.getMessage());
			// �ڿ���̨����쳣��ջ��Ϣ
			// ex.printStackTrace();
		}
		// �������ݿ�����ִ�ж���
		return this.preStatement;
	}

	/**
	 * ִ�и������(Insert|Update|Delete)
	 * 
	 * @param sql
	 *            Ҫִ�е�SQL����ƴװ����ַ���
	 * @return ��Ӱ�������
	 */
	public int executeUpdate(String sql) {
		try {
			// �ÿս���������ԭ������
			this.rsSet = null;
			// ִ����䲢������Ӱ������
			return this.getPreparedStatement(sql).executeUpdate();
		} catch (SQLException e) {
			System.err.println("�������ݴ���" + e.getMessage());
			return 0;
		} finally {
			// �ر����ݿ�������Դ
			closeDBResource();
		}
	}

	/**
	 * ִ�в�ѯ���(Select)
	 * 
	 * @param sql
	 *            Ҫִ�е�SQL����ƴװ����ַ���
	 * @return ��ѯ��Ľ��������
	 */
	public ResultSet executeQuery(String sql) {
		try {
			// �ÿս���������ԭ������
			this.rsSet = null;
			// ִ��sql����ý����
			this.rsSet = this.getPreparedStatement(sql).executeQuery();
		} catch (SQLException e) {
			System.err.println("��ѯ���ݴ���" + e.getMessage());
		}
		// ���ؽ��������
		return this.rsSet;
	}

	/**
	 * ��ȡִ��ָ��sql����ķ��ؽ�����ļ�¼����
	 * 
	 * @param sql
	 *            Ҫִ�е�SQL����ƴװ����ַ���
	 * @return ��ѯ����õ��ļ�¼����
	 */
	public int getResultSetCount(String sql) {
		// ����õ�ָ����sql���ִ�к󷵻ؼ�¼�����ļ���������
		int count = 0;
		try {
			// �ÿս���������ԭ������
			this.rsSet = null;
			// ִ��sql����ý����
			this.rsSet = this.getPreparedStatement(sql).executeQuery();
			// ������������ۼӼ�����
			while (this.rsSet.next()) {
				count++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	/**
	 * �ر����ݿ�������Դ(�����������������ִ�ж����� �Ӷ���)
	 */
	public void closeDBResource() {
		try {
			closeResultSet();
			closePreparedStatement();
			closeConnection();
		} catch (SQLException sqlEx) {
			System.err.println(sqlEx.getMessage());
			// �ڿ���̨����쳣��ջ��Ϣ
			// sqlEx.printStackTrace();
		}
	}

	/**
	 * �رս��������ķ���
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
			throw new SQLException("�رս������ �����" + sqlEx.getMessage());
			// �ڿ���̨����쳣��ջ��Ϣ
			// sqlEx.printStackTrace();
		}
	}

	/**
	 * �ر����ݿ�����ִ�ж���ķ���
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
			throw new SQLException("�ر����ݿ��� ��ִ�ж������" + sqlEx.getMessage());
			// �ڿ���̨����쳣��ջ��Ϣ
			// sqlEx.printStackTrace();
		}
	}

	/**
	 * �ر����ݿ����ӵķ���
	 * 
	 * @throws SQLException
	 */
	private void closeConnection() throws SQLException {
		try {
			if (this.dbConnection != null && (!this.dbConnection.isClosed())) {
				this.dbConnection.close();
			}
		} catch (SQLException sqlEx) {
			throw new SQLException("�ر����ݿ��� �Ӵ���" + sqlEx.getMessage());
			// �ڿ���̨����쳣��ջ��Ϣ
			// sqlEx.printStackTrace();
		}
	}
}
