package com.todoList.todo.controller;

import com.todoList.todo.dto.TodoItem;
import com.todoList.todo.service.TodoListService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("todo")
public class TodoController {
    private final TodoListService todoService;

    public TodoController(TodoListService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    public List<TodoItem> getTodoList(){
        return todoService.findTodoItems();
    }
    @PostMapping
    public TodoItem saveTodo(@RequestBody TodoItem todoItem){
        return todoService.join(todoItem);
    }
}
