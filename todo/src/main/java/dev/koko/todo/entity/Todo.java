package dev.koko.todo.entity;

import java.time.OffsetDateTime;
import java.util.UUID;

import dev.koko.todo.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Todo {
    private UUID id;
    private String title;
    private Status status;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;    
}
