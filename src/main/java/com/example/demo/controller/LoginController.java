package com.example.demo.controller;

import com.example.demo.dto.UserDTO;
import jakarta.servlet.http.HttpSession;
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
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {

    private final HttpSession session;

    @GetMapping
    public String loginForm(Model model) {
        if (session.getAttribute("user") != null) {
            return "redirect:/todos";
        }
        model.addAttribute("loginDTO", new UserDTO());
        return "login";
    }

    @PostMapping
    public String login(@Valid @ModelAttribute UserDTO dto, BindingResult result) {
        if (result.hasErrors()) return "login";
        session.setAttribute("user", dto.getUsername());
        return "redirect:/todos";
    }
}
