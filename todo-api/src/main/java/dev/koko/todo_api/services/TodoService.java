package dev.koko.todo_api.services;

import java.util.List;

import dev.koko.todo_api.dtos.CreateTodoDto;
import dev.koko.todo_api.dtos.TodoDto;
import dev.koko.todo_api.dtos.UpdateTodoDto;
import lombok.NonNull;

public interface TodoService {
    List<TodoDto> findTodos();
    TodoDto findTodoById(@NonNull String id);
    TodoDto saveTodo(@NonNull CreateTodoDto dto);
    void updateTodo(@NonNull String id, @NonNull UpdateTodoDto dto);
    void removeTodoById(@NonNull String id);
}
