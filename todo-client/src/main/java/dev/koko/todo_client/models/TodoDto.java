package dev.koko.todo_client.models;

import java.time.OffsetDateTime;

import dev.koko.todo_client.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TodoDto {
    private String id;
    private String title;
    private Status status;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}