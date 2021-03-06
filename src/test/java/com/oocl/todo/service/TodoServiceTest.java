package com.oocl.todo.service;

import com.oocl.todo.entity.Todo;
import com.oocl.todo.repository.TodoRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.stubbing.OngoingStubbing;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


class TodoServiceTest {
    private TodoRepository todoRepository;
    private TodoService todoService;

    @BeforeEach
    private void init(){
        todoRepository = Mockito.mock(TodoRepository.class);
        Todo todo = new Todo(1, "todo-1", true);
        when(todoRepository.findAll())
                .thenReturn(Arrays.asList(
                        new Todo(1,"todo-1",false),
                        new Todo(2,"todo-2",false),
                        new Todo(3,"todo-3",true)
                ));
        when(todoRepository.save(any())).thenReturn(todo);
        todoService = new TodoServiceImpl(todoRepository);
    }
    @Test
    void should_return_todo_list_when_get_all_given_nothing() {
        //given
        //when
        List<Todo> all = todoService.getAll();
        //then
        assertTrue(all.size()>0);
        assertEquals(1,all.get(0).getId());
        assertEquals("todo-1",all.get(0).getContent());
        assertFalse(all.get(0).getStatus());
    }

    @Test
    void should_add_todo_when_add_todo_given_todo() {
        //given
        Todo todo = new Todo(1, "todo-1", false);
        //when
        Todo saveTodo = todoService.addTodo(todo);
        //then
        assertNotNull(saveTodo);
        assertEquals(todo.getContent(),saveTodo.getContent());
    }

    @Test
    void should_delete_todo_when_delete_todo_given_todo_id() {
        //given
        Integer todoId = 1;
        //when
        todoService.deleteTodo(todoId);
        //then
        verify(todoRepository).deleteById(todoId);
    }

    @Test
    void should_update_todo_when_update_todo_given_todo() {
        //given
        Todo todo = new Todo(1, "todo-1", true);
        //when
        Todo saveTodo = todoService.updateTodo(todo);
        //then
        verify(todoRepository).save(todo);
        assertNotNull(saveTodo);
        assertEquals(todo.getStatus(),saveTodo.getStatus());
    }
}