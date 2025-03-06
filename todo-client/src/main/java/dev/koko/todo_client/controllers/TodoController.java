package dev.koko.todo_client.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import dev.koko.todo_client.models.Todo;

@Controller
public class TodoController {
    @GetMapping("")
    public String index() throws JsonMappingException, JsonProcessingException {
        List<Todo> result = http();
        return "index";
    }

    private List<Todo> http() throws JsonMappingException, JsonProcessingException {
        RestTemplate template = new RestTemplate();
        String todosJson = template.getForObject("http://localhost:8080/api/v1/todos", String.class);
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule()); 
        TypeReference<List<Todo>> type = new TypeReference<List<Todo>>(){};
        return mapper.readValue(todosJson, type);
    }
}
