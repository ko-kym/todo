package dev.koko.todo.services;

import java.util.List;

import dev.koko.todo.dtos.CreateTodoDto;
import dev.koko.todo.dtos.TodoDto;
import lombok.NonNull;

public interface TodoService {
    List<TodoDto> findTodos();
    TodoDto findTodoById(@NonNull String id);
    TodoDto saveTodo(@NonNull CreateTodoDto dto);
    TodoDto updateTodo(@NonNull String id, TodoDto dto);
    void removeTodoById(@NonNull String id);
}
