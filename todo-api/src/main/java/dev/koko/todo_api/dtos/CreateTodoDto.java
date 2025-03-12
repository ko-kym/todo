package dev.koko.todo_api.dtos;

import dev.koko.todo_api.enums.Status;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateTodoDto {
    @NotNull
    @Size(max = 100)
    private String title;
    // TODO: Enumアノテーション
    @NotNull
    private Status status;
}
