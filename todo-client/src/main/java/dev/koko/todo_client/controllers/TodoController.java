package dev.koko.todo_client.controllers;

import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import dev.koko.todo_client.enums.Status;
import dev.koko.todo_client.forms.CreateTodoForm;
import dev.koko.todo_client.forms.EditTodoForm;
import dev.koko.todo_client.models.TodoDto;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class TodoController {
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    private final static String ENDPOINT = "http://localhost:8080/api/v1/todos";

    @GetMapping("")
    public String home(Model model) throws JsonMappingException, JsonProcessingException {
        final List<TodoDto> todos = fetchTodos();
        model.addAttribute("todos", todos);
        return "home/index";
    }

    @GetMapping("/create-form")
    public String createForm(@ModelAttribute CreateTodoForm createTodoForm, Model model)
            throws JsonMappingException, JsonProcessingException {
        model.addAttribute("statuses", Status.values());
        return "createForm/index";
    }

    @PostMapping("/create-todo")
    public String createTodo(@ModelAttribute CreateTodoForm createTodoForm, Model model)
            throws JsonMappingException, JsonProcessingException {
        restTemplate.postForObject(ENDPOINT, createTodoForm, TodoDto.class);
        return "redirect:/";
    }

    @GetMapping("/edit-form/{id}")
    public String editForm(@PathVariable("id") String id, @ModelAttribute EditTodoForm editTodoForm, Model model)
            throws JsonMappingException, JsonProcessingException {
        model.addAttribute("editTodoForm",
                restTemplate.getForObject(ENDPOINT.concat("/").concat(id), EditTodoForm.class));
        model.addAttribute("statuses", Status.values());
        return "editForm/index";
    }

    @PostMapping("/edit-todo/{id}")
    public String editTodo(@PathVariable("id") String id, @ModelAttribute EditTodoForm editTodoForm, Model model)
            throws JsonMappingException, JsonProcessingException {
        restTemplate.patchForObject(ENDPOINT.concat("/").concat(id), editTodoForm,
                TodoDto.class);
        return "redirect:/";
    }

    @PostMapping("/delete-todo/{id}")
    public String deleteTodo(@PathVariable("id") String id)
            throws JsonMappingException, JsonProcessingException {
        restTemplate.delete(ENDPOINT.concat("/").concat(id));
        return "redirect:/";
    }

    private List<TodoDto> fetchTodos() throws JsonMappingException, JsonProcessingException {
        final String todosJson = restTemplate.getForObject(ENDPOINT, String.class);
        return objectMapper.readValue(todosJson, new TypeReference<List<TodoDto>>() {
        });
    }
}
