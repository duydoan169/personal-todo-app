package com.example.demo.service.impl;

import com.example.demo.dto.TodoDTO;
import com.example.demo.model.Todo;
import com.example.demo.repository.ITodoRepository;
import com.example.demo.service.ITodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements ITodoService {
    private final ITodoRepository todoRepository;

    @Override
    public List<Todo> getAllTodo() {
        return todoRepository.findAll();
    }

    @Override
    public void createTodo(TodoDTO dto) {
        Todo todo = new Todo();
        todo.setContent(dto.getContent());
        todo.setDueDate(dto.getDueDate());
        todo.setStatus(dto.getStatus());
        todo.setPriority(dto.getPriority());
        todoRepository.save(todo);
    }
}
