package com.todoList.todo.controller;

import com.todoList.todo.dto.TodoItem;
import com.todoList.todo.service.TodoListService;
import org.springframework.stereotype.Controller;
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
    public List<TodoItem> getTodo(){
        return todoService.findTodoItems();
    }
    @PostMapping
    public TodoItem saveTodo(@RequestBody TodoItem todoItem){
        return todoService.join(todoItem);
    }
    @PutMapping
    public TodoItem updateTodo(@RequestBody TodoItem todoItem){
        return todoService.update(todoItem);
    }
    @DeleteMapping("/{id}")
    public Boolean deleteTodo(@PathVariable("id") Long id){
        System.out.println(id);
        return todoService.delete(id);
    }
}
