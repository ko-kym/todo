package dev.koko.todo_client.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import dev.koko.todo_client.forms.CreateTodoForm;
import dev.koko.todo_client.models.TodoDto;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class TodoController {
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    private final static String ENDPOINT = "http://localhost:8080/api/v1/todos";

    @GetMapping("")
    public String index(Model model) throws JsonMappingException, JsonProcessingException {
        final List<TodoDto> todos = fetchTodos();
        model.addAttribute("todos", todos);
        return "index";
    }

    @GetMapping("/todo-form")
    public String todoForm(@ModelAttribute CreateTodoForm createTodoForm, Model model)
            throws JsonMappingException, JsonProcessingException {
        return "todoForm";
    }

    @PostMapping("/creating")
    public String createTodo(@ModelAttribute CreateTodoForm createTodoForm, Model model)
            throws JsonMappingException, JsonProcessingException {
        restTemplate.postForObject(ENDPOINT, createTodoForm, TodoDto.class);
        return "redirect:";
    }

    private List<TodoDto> fetchTodos() throws JsonMappingException, JsonProcessingException {
        final String todosJson = restTemplate.getForObject(ENDPOINT, String.class);
        return objectMapper.readValue(todosJson, new TypeReference<List<TodoDto>>() {
        });
    }
}
