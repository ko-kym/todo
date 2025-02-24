package dev.koko.todo.services;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.koko.todo.dtos.CreateTodoDto;
import dev.koko.todo.dtos.TodoDto;
import dev.koko.todo.dtos.UpdateTodoDto;
import dev.koko.todo.enums.Status;
import dev.koko.todo.repository.TodoRepository;
import lombok.NonNull;

@Service
public class TodoServiceWithMock implements TodoService{

    @Autowired
    TodoRepository todoRepository;

    private static final List<TodoDto> todos = new ArrayList<>() {
        {
            add(new TodoDto(UUID.randomUUID().toString(), "todo1", Status.NOT_STARTED, OffsetDateTime.now(),
                    OffsetDateTime.now()));
            add(new TodoDto(UUID.randomUUID().toString(), "todo2", Status.IN_PROGRESS, OffsetDateTime.now(),
                    OffsetDateTime.now()));
            add(new TodoDto(UUID.randomUUID().toString(), "todo3", Status.COMPLETED, OffsetDateTime.now(), OffsetDateTime.now()));
            add(new TodoDto(UUID.randomUUID().toString(), "todo4", Status.NOT_STARTED, OffsetDateTime.now(),
                    OffsetDateTime.now()));
        }
    };

    public List<TodoDto> findTodos() {
        return todos;
    }

    public TodoDto findTodoById(@NonNull String id) {
        final TodoDto found = todos.stream()
                .filter(todo -> todo.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Not found.")); // TODO: Error Handling

        return found;
    }

    public TodoDto saveTodo(@NonNull CreateTodoDto dto) {
        final String id = UUID.randomUUID().toString();
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
    
    @Override
    public void updateTodo(@NonNull String id, @NonNull UpdateTodoDto dto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateTodo'");
    }
    
    public void removeTodoById(@NonNull String id) {
        todos.remove(findTodoById(id));
    }


}
