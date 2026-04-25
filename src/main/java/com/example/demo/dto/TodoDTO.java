package com.example.demo.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TodoDTO {

    @NotBlank(message = "Content không được để trống")
    private String content;

    @FutureOrPresent(message = "Due date không được ở trong quá khứ")
    @NotNull(message = "Due date không được để trống")
    private LocalDate dueDate;

    @NotNull(message = "Status không được để trống")
    private Boolean status;

    @NotNull(message = "Priority không được để trống")
    private Integer priority;
}