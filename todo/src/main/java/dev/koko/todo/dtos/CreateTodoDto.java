package dev.koko.todo.dtos;

import dev.koko.todo.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@AllArgsConstructor
@Builder
public class CreateTodoDto {
    // TODO: Not empty and null -> blank
    String title;
    Status status;
}
