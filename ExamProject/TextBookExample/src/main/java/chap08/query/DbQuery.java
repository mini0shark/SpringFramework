package chap08.query;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.tomcat.jdbc.pool.DataSource;

	// Ŀ�ؼ� Ǯ�� �Ź� ���ο� Ŀ�ؼ��� ������ ������ �ð��� �Ҹ�Ǿ� �̸� ������ ���°� Ŀ�ؼ� Ǯ�̴�.
public class DbQuery {
	private DataSource dataSource;
	
	public DbQuery(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public int count() {
		Connection conn = null;
		try {
			conn = dataSource.getConnection();			// Ŀ�ؼ��� ������
			try(Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery("select count(*) from MEMBER")) {
				rs.next();
				return rs.getInt(1);
			}
		}catch(SQLException e) {
			throw new RuntimeException();
		}finally {
			if(conn !=null)
				try {
					conn.close();	// Ǯ�� ��ȯ -> Ŀ�ؼ��� Ŀ�ؼ�Ǯ���� ��밡�� ���°� ��
				}catch (SQLException e2) {
				}
		}
	}

}
