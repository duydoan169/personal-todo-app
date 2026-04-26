package com.example.demo.service;

import com.example.demo.dto.TodoDTO;
import com.example.demo.model.Todo;

import java.util.List;

public interface ITodoService {
    List<Todo> getAllTodo();

    void createTodo(TodoDTO dto);

    Todo getTodoByContent(String content);

    Todo getTodoById(Long id);

    void updateTodo(TodoDTO dto, Long id);

    void deleteTodo(Long id);
}
