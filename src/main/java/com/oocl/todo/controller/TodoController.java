package com.oocl.todo.controller;

import com.oocl.todo.entity.Todo;
import com.oocl.todo.service.TodoService;
import com.sun.istack.NotNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("todos")
@CrossOrigin
public class TodoController {
    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    public List<Todo> getAll(){
        return todoService.getAll();
    }

    @DeleteMapping("/{id}")
    public Boolean deleteTodo(@PathVariable("id") @NotNull Integer todoId){
        return todoService.deleteTodo(todoId);
    }


}
