package dev.koko.todo.restcontrollers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.koko.todo.dtos.TodoDto;

@RestController
@RequestMapping("/api/v1/todos")
public class TodoController {

    @GetMapping("")
    public ResponseEntity<List<TodoDto>> getTodos() {
        return ResponseEntity.ok(null);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TodoDto> getTodo(@PathVariable UUID id) {
        return ResponseEntity.ok(null);
    }

    @PostMapping("")
    public ResponseEntity<TodoDto> createTodo() {
        return ResponseEntity.created(null).body(null);
    }
}
