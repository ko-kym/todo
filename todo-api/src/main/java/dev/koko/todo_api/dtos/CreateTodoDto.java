package dev.koko.todo_api.dtos;

import dev.koko.todo_api.enums.Status;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@AllArgsConstructor
@Builder
public class CreateTodoDto {
    @NotNull
    @Max(value = 100)
    String title;
    //TODO: Enumアノテーション
    @NotNull
    Status status;
}
