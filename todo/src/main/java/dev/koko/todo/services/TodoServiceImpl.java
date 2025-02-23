package dev.koko.todo.services;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import dev.koko.todo.dtos.CreateTodoDto;
import dev.koko.todo.dtos.TodoDto;
import dev.koko.todo.entity.Todo;
import dev.koko.todo.repository.TodoRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@Primary
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService{

    private final TodoRepository todoRepository;

    @Override
    public List<TodoDto> findTodos() {
        return todoRepository.findAll().stream()
            .map(e -> toDto(e))   
            .toList();
    }

    @Override
    public TodoDto findTodoById(@NonNull String id) {
        return Optional.ofNullable(todoRepository.findById(id))
            .map(e -> toDto(e))
            .orElseThrow(() -> new RuntimeException("Not Found"));
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

        return TodoDto.builder()
                .id(saved.getId())
                .title(saved.getTitle())
                .status(saved.getStatus())
                .createdAt(saved.getCreatedAt())
                .updatedAt(saved.getUpdatedAt())
                .build();
    }

    @Override
    public TodoDto updateTodo(@NonNull String id, TodoDto dto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateTodo'");
    }

    @Override
    public void removeTodoById(@NonNull String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeTodoById'");
    }
    
    private TodoDto toDto(Todo e){
        return TodoDto.builder()
                .id(e.getId())
                .title(e.getTitle())
                .status(e.getStatus())
                .createdAt(e.getCreatedAt())
                .updatedAt(e.getUpdatedAt())
                .build();
    }
}
