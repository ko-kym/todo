package dev.koko.todo_api.dtos;

import dev.koko.todo_api.enums.Status;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateTodoDto {
    @Size(max = 100)
    String title;
    // TODO: Enumアノテーション
    Status status;
}
