package dev.koko.todo_api.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import dev.koko.todo_api.entity.Todo;
import lombok.NonNull;

@Mapper
public interface TodoRepository {
    List<Todo> findAll();
    Todo findById(@NonNull String id);
    void save(@NonNull Todo entity);
    void update(@NonNull Todo entity);
    void removeById(@NonNull String id);
}
