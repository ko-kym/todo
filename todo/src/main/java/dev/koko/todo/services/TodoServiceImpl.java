package dev.koko.todo.services;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import dev.koko.todo.dtos.CreateTodoDto;
import dev.koko.todo.dtos.TodoDto;
import dev.koko.todo.dtos.UpdateTodoDto;
import dev.koko.todo.entity.Todo;
import dev.koko.todo.exceptions.NotFoundException;
import dev.koko.todo.repository.TodoRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@Primary
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;

    @Override
    public List<TodoDto> findTodos() {
        return todoRepository.findAll().stream()
                .map(e -> _toDto(e))
                .toList();
    }

    @Override
    public TodoDto findTodoById(@NonNull String id) {
        return _toDto(_findTodoById(id));
    }

    @Override
    public TodoDto saveTodo(@NonNull CreateTodoDto dto) {
        final String id = UUID.randomUUID().toString();
        // TODO: Time zone 設定
        final OffsetDateTime now = OffsetDateTime.now();
        final Todo saved = Todo.builder()
                .id(id)
                .title(dto.getTitle())
                .status(dto.getStatus())
                .createdAt(now)
                .updatedAt(now)
                .build();
        todoRepository.save(saved);

        return _toDto(saved);
    }

    @Override
    public void updateTodo(@NonNull String id, @NonNull UpdateTodoDto dto) {
        final Todo found = _findTodoById(id);

        // TODO: dtoクラス変数増減考慮
        if (Objects.nonNull(dto.getTitle())) {
            found.setTitle(dto.getTitle());
        }
        if (Objects.nonNull(dto.getStatus())) {
            found.setStatus(dto.getStatus());
        }
        found.setUpdatedAt(OffsetDateTime.now());

        todoRepository.update(found);
    }

    @Override
    public void removeTodoById(@NonNull String id) {
        todoRepository.removeById(_findTodoById(id).getId());
    }

    private TodoDto _toDto(@NonNull Todo e) {
        return TodoDto.builder()
                .id(e.getId())
                .title(e.getTitle())
                .status(e.getStatus())
                .createdAt(e.getCreatedAt())
                .updatedAt(e.getUpdatedAt())
                .build();
    }

    private Todo _findTodoById(@NonNull String id) {
        return Optional.ofNullable(todoRepository.findById(id))
                .orElseThrow(() -> new NotFoundException("Not Found : ".concat(id)));
    }
}
