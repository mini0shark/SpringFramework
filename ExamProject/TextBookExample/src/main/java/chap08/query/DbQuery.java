package chap08.query;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.tomcat.jdbc.pool.DataSource;

	// 커넥션 풀은 매번 새로운 커넥션을 생성할 때마다 시간이 소모되어 미리 생성해 놓는게 커넥션 풀이다.
public class DbQuery {
	private DataSource dataSource;
	
	public DbQuery(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public int count() {
		Connection conn = null;
		try {
			conn = dataSource.getConnection();			// 커넥션을 가져옴
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
					conn.close();	// 풀에 반환 -> 커넥션이 커넥션풀에서 사용가능 상태가 됨
				}catch (SQLException e2) {
				}
		}
	}

}
