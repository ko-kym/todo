package dev.koko.todo.entity;

import java.time.OffsetDateTime;

import dev.koko.todo.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Todo {
    private String id;
    private String title;
    private Status status;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;    
}
