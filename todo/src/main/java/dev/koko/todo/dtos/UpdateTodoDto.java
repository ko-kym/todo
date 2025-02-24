package dev.koko.todo.dtos;

import dev.koko.todo.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@AllArgsConstructor
@Builder
public class UpdateTodoDto {
    // TODO: titleの文字数制限(100文字)
    String title;
    Status status;
}
