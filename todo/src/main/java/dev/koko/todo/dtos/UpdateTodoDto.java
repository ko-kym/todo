package dev.koko.todo.dtos;

import dev.koko.todo.enums.Status;
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
