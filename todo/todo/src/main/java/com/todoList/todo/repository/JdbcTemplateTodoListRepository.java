package com.todoList.todo.repository;

import com.todoList.todo.dto.TodoItem;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class JdbcTemplateTodoListRepository implements TodoListRepository {
    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateTodoListRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public TodoItem save(TodoItem todoItem) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("todoList").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("subject", todoItem.getSubject());
        parameters.put("content", todoItem.getContent());
        parameters.put("createDate", todoItem.getCreateDate());
        parameters.put("fin", todoItem.isFin());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        todoItem.setId(key.longValue());
        return todoItem;
    }

    @Override
    public TodoItem update(TodoItem todoItem) {
        System.out.println(jdbcTemplate.update("update todoList set fin = ? where id = ?",todoItem.isFin(), todoItem.getId()));
//        jdbcTemplate.update(new PreparedStatementCreator() {
//            @Override
//            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
//                PreparedStatement pstmt = con.prepareStatement(
//                        "update todoList set fin = ? where id = ?"
//                );
//                pstmt.setBoolean(1, todoItem.isFin());
//                pstmt.setLong(2, todoItem.getId());
//
//                return pstmt;
//            }
//        });
        return todoItem;
    }

    @Override
    public Optional<TodoItem> findById(Long id) {
        List<TodoItem> result = jdbcTemplate.query("select * from todoList where id = ?", todoRowMapper(), id);
        return result.stream().findAny();
    }


    @Override
    public List<TodoItem> findAll() {
        List<TodoItem> result = jdbcTemplate.query("select * from todoList", todoRowMapper());
        return result;
    }

    @Override
    public Boolean delete(Long id) {
        long result = jdbcTemplate.update(con -> {
            PreparedStatement pstmt = con.prepareStatement(
                    "delete from todoList where id = ?"
            );
            pstmt.setLong(1, id);

            return pstmt;
        });
        return result>0;
    }

    private RowMapper<TodoItem> todoRowMapper() {
        return new RowMapper<TodoItem>() {
            @Override
            public TodoItem mapRow(ResultSet rs, int rowNum) throws SQLException {
                TodoItem todoItem = new TodoItem();
                todoItem.setId(rs.getLong("id"));
                todoItem.setSubject(rs.getString("subject"));
                todoItem.setContent(rs.getString("content"));
                todoItem.setCreateDate(rs.getDate("createDate"));
                todoItem.setFin(rs.getBoolean("fin"));
                return todoItem;
            }
        };
    }
}
