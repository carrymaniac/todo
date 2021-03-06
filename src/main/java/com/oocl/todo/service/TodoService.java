package com.oocl.todo.service;

import com.oocl.todo.entity.Todo;

import java.util.List;

public interface TodoService {
    List<Todo> getAll();

    Todo addTodo(Todo todo);

    Boolean deleteTodo(Integer todoId);

    Todo updateTodo(Todo todo);

}
