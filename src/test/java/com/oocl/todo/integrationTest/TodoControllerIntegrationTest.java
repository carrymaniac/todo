package com.oocl.todo.integrationTest;

import com.oocl.todo.entity.Todo;
import com.oocl.todo.repository.TodoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class TodoControllerIntegrationTest {
    @Autowired
    TodoRepository todoRepository;
    @Autowired
    MockMvc mockMvc;
    @Test
    void should_return_todo_list_when_hit_get_todo_given_nothing() throws Exception {
        //given
        List<Todo> todos = Arrays.asList(new Todo(1, "todo-1", false),
                new Todo(2, "todo-2", false),
                new Todo(3, "todo-3", true));
        List<Todo> saveTodos = todoRepository.saveAll(todos);
        //when
        mockMvc.perform(get("/todos"))
        //then
                .andExpect(jsonPath("$.length()").value(saveTodos.size()))
                .andExpect(jsonPath("$[0].content").value(saveTodos.get(0).getContent()));
    }

    @Test
    void should_delete_todo_list_when_hit_delete_todo_given_todo_id() throws Exception {
        //given
        List<Todo> todos = Arrays.asList(new Todo(1, "todo-1", false),
                new Todo(2, "todo-2", false),
                new Todo(3, "todo-3", true));
        List<Todo> saveTodos = todoRepository.saveAll(todos);
        //when
        mockMvc.perform(delete("/todos/"+saveTodos.get(0).getId()))
        //then
                .andExpect(jsonPath("$").value(true));
        Optional<Todo> todoOptional = todoRepository.findById(saveTodos.get(0).getId());
        assertFalse(todoOptional.isPresent());
    }

    @Test
    void should_add_todo_when_hit_put_todo_given_todo() throws Exception {
        //given
        //when
        mockMvc.perform(put("/todos").
                contentType(MediaType.APPLICATION_JSON).
                content("{\"id\":0,\"content\":\"str\",\"status\":true}"))
        //then
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.content").value("str"))
                .andExpect(jsonPath("$.status").value(false));
    }
}
