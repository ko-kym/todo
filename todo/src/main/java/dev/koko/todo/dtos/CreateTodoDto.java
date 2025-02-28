package dev.koko.todo.dtos;

import dev.koko.todo.enums.Status;
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
