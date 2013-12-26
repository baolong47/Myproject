package cn.xiaoqiang.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class JdbcConTest {
	public void testJdbc(){
	        ResultSet rs = null;
	        Statement stmt = null;
	        Connection conn = null;
	        try {
	            Class.forName("oracle.jdbc.driver.OracleDriver");
	            //new oracle.jdbc.driver.OracleDriver();
	            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ora10g", "scott", "tiger");
	            stmt = conn.createStatement();
	            rs = stmt.executeQuery("select * from EMP");
	            //rs = stmt.executeQuery("select * from (select t.*,rownum as rn from  szqs.pub_t_division t) rol  where rol.rn>0 and rol.rn<=10");
	            while(rs.next()) {
	                System.out.println(rs.getString("ENAME"));
	            }
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            try {
	                if(rs != null) {
	                    rs.close();
	                    rs = null;
	                }
	                if(stmt != null) {
	                    stmt.close();
	                    stmt = null;
	                }
	                if(conn != null) {
	                    conn.close();
	                    conn = null;
	                }
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }
}

