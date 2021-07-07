package com.todoList.todo.controller;

import com.todoList.todo.dto.TodoItem;
import com.todoList.todo.service.TodoListService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("todo-list")
public class TodoListController {
    private final TodoListService todoService;

    public TodoListController(TodoListService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    public List<TodoItem> getTodoList(){
        return todoService.findTodoItems();
    }
}
