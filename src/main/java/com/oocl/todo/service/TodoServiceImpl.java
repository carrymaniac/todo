package com.oocl.todo.service;

import com.oocl.todo.entity.Todo;
import com.oocl.todo.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoServiceImpl implements TodoService{
    private final TodoRepository todoRepository;

    public TodoServiceImpl(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Override
    public List<Todo> getAll() {
        return todoRepository.findAll();
    }

    @Override
    public Todo addTodo(Todo todo) {
        return todoRepository.save(todo);
    }

    @Override
    public Boolean deleteTodo(Integer todoId) {
        todoRepository.deleteById(todoId);
        Optional<Todo> todoOptional = todoRepository.findById(todoId);
        return !todoOptional.isPresent();
    }

    @Override
    public Todo updateTodo(Todo todo) {
        return todoRepository.save(todo);
    }
}
