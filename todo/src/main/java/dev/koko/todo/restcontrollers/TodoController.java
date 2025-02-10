package dev.koko.todo.restcontrollers;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import dev.koko.todo.dtos.CreateTodoDto;
import dev.koko.todo.dtos.TodoDto;
import dev.koko.todo.services.TodoService;

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
    public ResponseEntity<TodoDto> getTodo(@PathVariable UUID id) {
        return ResponseEntity.ok(todoService.findTodoById(id));
    }

    @PostMapping("")
    public ResponseEntity<TodoDto> createTodo(@RequestBody CreateTodoDto newDto) {
        final TodoDto savedDto = todoService.saveTodo(newDto);
        final URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedDto.getId()).toUri();

        return ResponseEntity.created(location).body(savedDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TodoDto> updateTodo(@PathVariable UUID id, @RequestBody TodoDto newDto) {
        final TodoDto updateDto = todoService.updateTodo(id, newDto);
        
        return ResponseEntity.ok(updateDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TodoDto> deleteTodo(@PathVariable UUID id) {
        todoService.removeTodoById(id);
        return ResponseEntity.noContent().build();
    }
}
