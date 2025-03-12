package dev.koko.todo_api.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import dev.koko.todo_api.dtos.CreateTodoDto;
import dev.koko.todo_api.dtos.TodoDto;
import dev.koko.todo_api.dtos.UpdateTodoDto;
import dev.koko.todo_api.services.TodoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/todos")
public class TodoController {

    @Autowired
    TodoService todoService;

    @GetMapping("")
    public ResponseEntity<List<TodoDto>> getTodos() {
        return ResponseEntity.ok(todoService.findTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TodoDto> getTodo(@PathVariable("id") String id) {
        return ResponseEntity.ok(todoService.findTodoById(id));
    }

    @PostMapping("")
    public ResponseEntity<TodoDto> createTodo(@Valid @RequestBody CreateTodoDto dto) {
        final TodoDto savedDto = todoService.saveTodo(dto);
        final URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedDto.getId()).toUri();

        return ResponseEntity.created(location).body(savedDto);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TodoDto> updateTodo(@PathVariable("id") String id, @Valid @RequestBody UpdateTodoDto dto) {        
        todoService.updateTodo(id, dto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TodoDto> deleteTodo(@PathVariable("id") String id) {
        todoService.removeTodoById(id);
        return ResponseEntity.noContent().build();
    }
}
