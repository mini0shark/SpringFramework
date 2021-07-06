package com.todoList.todo.service;

import com.todoList.todo.dto.TodoItem;
import com.todoList.todo.repository.TodoListRepository;

import java.util.List;
import java.util.Optional;

public class TodoListService {
    private final TodoListRepository todoRepository;

    public TodoListService(TodoListRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public TodoItem join(TodoItem todoItem) {
        return todoRepository.save(todoItem);
    }
    public List<TodoItem> findTodoItems(){
        return todoRepository.findAll();
    }
    public Optional<TodoItem> findOne(Long todoListId){
        return todoRepository.findById(todoListId);
    }
}
