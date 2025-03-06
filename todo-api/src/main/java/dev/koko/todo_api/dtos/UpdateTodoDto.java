package dev.koko.todo_api.dtos;

import dev.koko.todo_api.enums.Status;
import jakarta.validation.constraints.Max;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@AllArgsConstructor
@Builder
public class UpdateTodoDto {
    @Max(value = 100)
    String title;
    // TODO: Enumアノテーション
    Status status;
}
