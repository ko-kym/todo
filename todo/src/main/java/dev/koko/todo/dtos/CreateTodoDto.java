package dev.koko.todo.dtos;

import dev.koko.todo.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class CreateTodoDto {
    private String title;
    private Status status;
}
