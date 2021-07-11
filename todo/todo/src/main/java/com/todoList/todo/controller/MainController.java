package com.todoList.todo.controller;

import com.todoList.todo.dto.TodoItem;
import com.todoList.todo.service.TodoListService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class MainController {
    private final TodoListService todoListService;

    public MainController(TodoListService todoListService) {
        this.todoListService = todoListService;
    }

    @RequestMapping("/")
    private String test(Model model){
        List<TodoItem> todoItems = todoListService.findTodoItems();
        model.addAttribute("todos", todoItems);
        return "index";
    }

    @RequestMapping("login-page")
    private String test(){
        return "login";
    }
}
