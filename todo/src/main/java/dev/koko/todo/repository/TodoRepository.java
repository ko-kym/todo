package dev.koko.todo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import dev.koko.todo.entity.Todo;
import lombok.NonNull;

@Mapper
public interface TodoRepository {
    List<Todo> findAll();
    Todo findById(String id);
    void save(@NonNull Todo todo);
}
