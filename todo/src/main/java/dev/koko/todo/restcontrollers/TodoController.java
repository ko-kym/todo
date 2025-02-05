package dev.koko.todo.restcontrollers;

import java.net.URI;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import dev.koko.todo.dtos.CreateTodoDto;
import dev.koko.todo.dtos.TodoDto;
import dev.koko.todo.enums.Status;

@RestController
@RequestMapping("/api/v1/todos")
public class TodoController {

    private static final List<TodoDto> todos = new ArrayList<>() {
        {
            add(new TodoDto(UUID.randomUUID(), "todo1", Status.NOT_STARTED, OffsetDateTime.now(), OffsetDateTime.now()));
            add(new TodoDto(UUID.randomUUID(), "todo2", Status.IN_PROGRESS, OffsetDateTime.now(), OffsetDateTime.now()));
            add(new TodoDto(UUID.randomUUID(), "todo3", Status.COMPLETED, OffsetDateTime.now(), OffsetDateTime.now()));
            add(new TodoDto(UUID.randomUUID(), "todo4", Status.NOT_STARTED, OffsetDateTime.now(), OffsetDateTime.now()));
        }
    };

    @GetMapping("")
    public ResponseEntity<List<TodoDto>> getTodos() {
        return ResponseEntity.ok(todos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TodoDto> getTodo(@PathVariable UUID id) {
        final TodoDto found = todos.stream()
            .filter(todo -> todo.getId().equals(id))
            .findFirst()
            .orElse(null);
        return ResponseEntity.ok(found);
    }

    @PostMapping("")
    public ResponseEntity<TodoDto> createTodo(@RequestBody CreateTodoDto createTodoDto) {
        final UUID id = UUID.randomUUID();
        final OffsetDateTime now = OffsetDateTime.now();
        final URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
            .buildAndExpand(id).toUri();
        final TodoDto savedDto = TodoDto.builder()
            .id(id)
            .title(createTodoDto.getTitle())
            .status(createTodoDto.getStatus())
            .createdAt(now)
            .updatedAt(now)
            .build(); 
        todos.add(savedDto);
        return ResponseEntity.created(location).body(savedDto);
    }
}
