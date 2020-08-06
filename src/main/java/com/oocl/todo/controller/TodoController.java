package com.oocl.todo.controller;

import com.oocl.todo.entity.Todo;
import com.oocl.todo.service.TodoService;
import javax.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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

    @PutMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Object addTodo(@RequestBody @Validated Todo todo, BindingResult result){
        if(result.hasErrors()){
            return result.getFieldError().getDefaultMessage();
        }
        return todoService.addTodo(todo);
    }
    @PostMapping()
    public Object updateTodo(@RequestBody @Validated Todo todo, BindingResult result){
        if(result.hasErrors()){
            return result.getFieldError().getDefaultMessage();
        }
        return todoService.updateTodo(todo);
    }


}
