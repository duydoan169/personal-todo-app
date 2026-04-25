package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "todos")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String content;

    @Column
    private LocalDate dueDate;

    @Column
    private Boolean status;

    @Column
    private Integer priority;
}
