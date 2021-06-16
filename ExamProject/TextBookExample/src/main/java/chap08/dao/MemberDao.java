package chap08.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import chap08.member.Member;

public class MemberDao {
	private JdbcTemplate jdbcTemplate;
	
	public MemberDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public Member selectByEmail(String email) {
		List<Member> result = jdbcTemplate.query("select * from MEMBER where EMAIL=?;",  new RowMapper<Member>() {
			@Override
			public Member mapRow(ResultSet rs, int rowNum) throws SQLException{
					Member member = new Member(
							rs.getString("EMAIL"),
							rs.getString("PASSWORD"),
							rs.getString("NAME"),
							rs.getTimestamp("REGDATE").toLocalDateTime());
					member.setId(rs.getLong("ID"));
					return member;
			}
		}, email);
		/*
		result = jdbcTemplate.query("select * from MEMBER where EMAIL=?",  (ResultSet rs, int rowNum)->{
			Member member = new Member(
					rs.getString("EMAIL"),
					rs.getString("PASSWORD"),
					rs.getString("PASSWORD"),
					rs.getTimestamp("REGDATE").toLocalDateTime());
			return member;
		}, email);
		*/	//람다식을 이용한 쿼리
		return result.isEmpty()? null:result.get(0);
	}

	public List<Member> selectAll() {
		List<Member> result = jdbcTemplate.query("select * from MEMBER", new RowMapper<Member>() {
			@Override
			public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
				Member member = new Member(
						rs.getString("EMAIL"),
						rs.getString("PASSWORD"),
						rs.getString("NAME"),
						rs.getTimestamp("REGDATE").toLocalDateTime());
				member.setId(rs.getLong("ID"));
				return member;
			}
		});
		return result;
	}
	
	public int count() {
		Integer count =jdbcTemplate.queryForObject("select count(*) from MEMBER", Integer.class);
		return count;
		/*
		List<Integer> results = jdbcTemplate.query("select count(*) from MEMBER", 
				new RowMapper<Integer>() {
			@Override
			public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getInt(1);
			}
		});
		return results.get(0);*/
	}
	
	public void insert(Member member) {
		// KeyHolder는 Auto Increment같은 자동증가 칼럼의 값을 구할때 사용
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement pstmt = con.prepareStatement("insert into MEMBER (EMAIL, PASSWORD, NAME, REGDATE) values(?, ?, ?, ?)", new String[] {"ID"});

				pstmt.setString(1, member.getEmail());
				pstmt.setString(2, member.getPassword());
				pstmt.setString(3, member.getName());
				pstmt.setTimestamp(4, Timestamp.valueOf(member.getRegisterDateTime()));
				return pstmt;
			}
		}, keyHolder);
		Number keyValue = keyHolder.getKey();
		member.setId(keyValue.longValue());	// 멤버에 key값이 세팅됐다.
		
	}
	public void update(Member member) {
		jdbcTemplate.update("update MEMBER set NAME=?, PASSWORD = ? where EMAIL=?",
				member.getName(), member.getPassword(), member.getEmail());
	}

}
