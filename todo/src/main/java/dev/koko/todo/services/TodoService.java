package dev.koko.todo.services;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import dev.koko.todo.dtos.CreateTodoDto;
import dev.koko.todo.dtos.TodoDto;
import dev.koko.todo.enums.Status;
import lombok.NonNull;

@Service
public class TodoService {

    private static final List<TodoDto> todos = new ArrayList<>() {
        {
            add(new TodoDto(UUID.randomUUID(), "todo1", Status.NOT_STARTED, OffsetDateTime.now(),
                    OffsetDateTime.now()));
            add(new TodoDto(UUID.randomUUID(), "todo2", Status.IN_PROGRESS, OffsetDateTime.now(),
                    OffsetDateTime.now()));
            add(new TodoDto(UUID.randomUUID(), "todo3", Status.COMPLETED, OffsetDateTime.now(), OffsetDateTime.now()));
            add(new TodoDto(UUID.randomUUID(), "todo4", Status.NOT_STARTED, OffsetDateTime.now(),
                    OffsetDateTime.now()));
        }
    };

    public List<TodoDto> findTodos() {
        return todos;
    }

    public TodoDto findTodoById(@NonNull UUID id) {
        final TodoDto found = todos.stream()
                .filter(todo -> todo.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Not found.")); // TODO: Error Handling

        return found;
    }

    public TodoDto saveTodo(@NonNull CreateTodoDto dto) {
        final UUID id = UUID.randomUUID();
        final OffsetDateTime now = OffsetDateTime.now();
        final TodoDto saved = TodoDto.builder()
                .id(id)
                .title(dto.getTitle())
                .status(dto.getStatus())
                .createdAt(now)
                .updatedAt(now)
                .build();
        todos.add(saved);

        return saved;
    }

    public void removeTodoById(@NonNull UUID id) {
        todos.remove(findTodoById(id));
    }

}
