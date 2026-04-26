package com.example.demo.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TodoDTO {

    @NotBlank(message = "Content không được để trống")
    private String content;

    @FutureOrPresent(message = "Due date không được ở trong quá khứ")
    @NotNull(message = "Due date không được để trống")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dueDate;

    @NotNull(message = "Status không được để trống")
    private Boolean status;

    @NotNull(message = "Priority không được để trống")
    @Positive(message = "Priority không được âm")
    private Integer priority;
}