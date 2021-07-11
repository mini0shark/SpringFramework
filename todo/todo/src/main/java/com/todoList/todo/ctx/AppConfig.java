package com.todoList.todo.ctx;

import com.todoList.todo.repository.JdbcTemplateTodoListRepository;
import com.todoList.todo.repository.TodoListRepository;
import com.todoList.todo.service.LoginService;
import com.todoList.todo.service.TodoListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.sql.DataSource;

@Configuration
public class AppConfig {
    private DataSource dataSource;

    @Autowired
    public AppConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public TodoListService todoService(){
        return new TodoListService(todoListRepository());
    }
    @Bean
    public LoginService loginService(){ return new LoginService(); }
    @Bean
    public CharacterEncodingFilter characterEncodingFilter(){
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        return characterEncodingFilter;
    }
    @Bean
    public TodoListRepository todoListRepository(){
        return new JdbcTemplateTodoListRepository(dataSource);
    }

}
