package com.example.demo.controller;

import com.example.demo.dto.TodoDTO;
import com.example.demo.model.Todo;
import com.example.demo.service.ITodoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/todos")
@RequiredArgsConstructor
public class TodoController {

    private final ITodoService todoService;

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("todos", todoService.getAllTodo());
        return "todo-list";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("todoDTO", new TodoDTO());
        return "form";
    }

    @PostMapping("/create")
    public String create(@Valid @ModelAttribute TodoDTO todoDTO, BindingResult result) {
        if (result.hasErrors()) {
            return "form";
        }
        Todo duplicate = todoService.getTodoByContent(todoDTO.getContent());
        if (duplicate != null){
            result.rejectValue("content", null, "Content đã tồn tại");
            return "form";
        }

        todoService.createTodo(todoDTO);
        return "redirect:/todos";
    }
}
