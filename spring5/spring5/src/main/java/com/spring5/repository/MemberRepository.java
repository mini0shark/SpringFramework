package com.spring5.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import com.spring5.domain.Member;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;


import javax.sql.DataSource;

@Repository
public class MemberRepository {
	private JdbcTemplate jdbcTemplate;
	
	public MemberRepository(DataSource dataSource) {
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
		*/	//���ٽ��� �̿��� ����
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
		// KeyHolder�� Auto Increment���� �ڵ����� Į���� ���� ���Ҷ� ���
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
		member.setId(keyValue.longValue());	// ����� key���� ���õƴ�.
		
	}
	public void update(Member member) {
		jdbcTemplate.update("update MEMBER set NAME=?, PASSWORD = ? where EMAIL=?",
				member.getName(), member.getPassword(), member.getEmail());
	}

}
