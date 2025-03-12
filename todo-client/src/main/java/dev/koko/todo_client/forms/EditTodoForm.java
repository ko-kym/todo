package dev.koko.todo_client.forms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EditTodoForm {
    private String id;
    private String title;
    private String status;
}
