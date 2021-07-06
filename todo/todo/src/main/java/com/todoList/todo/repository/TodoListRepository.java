package com.todoList.todo.repository;

import com.todoList.todo.dto.TodoItem;

import java.util.List;
import java.util.Optional;

public interface TodoListRepository {
    public TodoItem save(TodoItem todoItem);
    public Optional<TodoItem> findById(Long id);
    public List<TodoItem> findAll();
}
