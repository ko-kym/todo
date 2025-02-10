package dev.koko.todo.repository;

import java.util.List;
import java.util.UUID;

import org.apache.ibatis.annotations.Mapper;

import dev.koko.todo.entity.Todo;

@Mapper
public interface TodoRepository {
    List<Todo> findAll();
    Todo findById(UUID id);
}
