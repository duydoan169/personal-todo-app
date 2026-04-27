package com.example.demo.controller;

import com.example.demo.dto.TodoDTO;
import com.example.demo.model.Todo;
import com.example.demo.service.ITodoService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/todos")
@RequiredArgsConstructor
public class TodoController {

    private final ITodoService todoService;
    private final HttpSession session;

    @GetMapping
    public String getAll(Model model) {
        if (session.getAttribute("user") == null){
            return "redirect:/login";
        }
        model.addAttribute("todos", todoService.getAllTodo());
        return "todo-list";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        if (session.getAttribute("user") == null){
            return "redirect:/login";
        }
        model.addAttribute("todoDTO", new TodoDTO());
        model.addAttribute("action", "/todos/create");
        return "form";
    }

    @PostMapping("/create")
    public String create(@Valid @ModelAttribute TodoDTO todoDTO, BindingResult result, Model model, RedirectAttributes ra) {
        if (result.hasErrors()) {
            model.addAttribute("action", "/todos/create");
            return "form";
        }

        Todo duplicate = todoService.getTodoByContent(todoDTO.getContent());
        if (duplicate != null){
            model.addAttribute("action", "/todos/create");
            result.rejectValue("content", null, "Content đã tồn tại");
            return "form";
        }

        todoService.createTodo(todoDTO);
        ra.addFlashAttribute("success", "Thêm công việc thành công");
        return "redirect:/todos";
    }

    @GetMapping("/update/{id}")
    public String updateForm(Model model, @PathVariable Long id, RedirectAttributes ra) {
        if (session.getAttribute("user") == null){
            return "redirect:/login";
        }
        Todo update = todoService.getTodoById(id);
        if (update == null){
            ra.addFlashAttribute("failed", "Id công việc không tồn tại");
            return "redirect:/todos";
        }

        model.addAttribute("todoDTO", new TodoDTO(
                update.getContent(),
                update.getDueDate(),
                update.getStatus(),
                update.getPriority()
        ));

        model.addAttribute("action", "/todos/update/" + id);
        return "form";
    }

    @PostMapping("/update/{id}")
    public String update(@Valid @ModelAttribute TodoDTO todoDTO, BindingResult result, @PathVariable Long id, RedirectAttributes ra) {
        if (result.hasErrors()) {
            return "form";
        }

        todoService.updateTodo(todoDTO, id);
        ra.addFlashAttribute("success", "Sửa công việc thành công");
        return "redirect:/todos";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes ra){
        Todo delete = todoService.getTodoById(id);
        if (delete == null){
            ra.addFlashAttribute("failed", "Id công việc không tồn tại");
            return "redirect:/todos";
        }

        todoService.deleteTodo(id);
        ra.addFlashAttribute("success", "Xóa công việc thành công");
        return "redirect:/todos";
    }
}
