package com.softpro.Project2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.softpro.Project2.entity.User;
import com.softpro.Project2.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {

    @Autowired
    private UserService userService;


    // ================= LOGIN PAGE =================

    @GetMapping("/")
    public String loginHome() {
        return "login";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }


    // ================= REGISTER PAGE =================

    @GetMapping("/register")
    public String registerPage(Model model) {

        model.addAttribute("user", new User());

        return "register";
    }


    // ================= REGISTER USER =================

    @PostMapping("/register")
    public String registerUser(
            @ModelAttribute User user,
            Model model) {

        // Check if email already exists
        if (userService.emailExists(user.getEmail())) {

            model.addAttribute(
                    "error",
                    "Email already registered!"
            );

            return "register";
        }

        // Save new user
        userService.saveUser(user);

        // Registration successful
        return "redirect:/login";
    }


    // ================= LOGIN USER =================

    @PostMapping("/login")
    public String loginUser(
            @RequestParam String email,
            @RequestParam String password,
            HttpSession session,
            Model model) {

        // Find user by email
        User user = userService.findByEmail(email);


        // Email is not registered
        if (user == null) {

            model.addAttribute(
                    "error",
                    "Please register first!"
            );

            return "login";
        }


        // Check password
        if (user.getPassword().equals(password)) {

            // Create login session
            session.setAttribute(
                    "loggedUser",
                    user
            );

            // Login successful
            return "redirect:/home";
        }


        // Password is incorrect
        model.addAttribute(
                "error",
                "Invalid email or password"
        );

        return "login";
    }


    // ================= PROFILE =================

    @GetMapping("/profile")
    public String profile(
            HttpSession session,
            Model model) {

        // Get logged-in user
        User user =
                (User) session.getAttribute("loggedUser");


        // User is not logged in
        if (user == null) {

            return "redirect:/login";
        }


        // Send user data to profile page
        model.addAttribute(
                "user",
                user
        );

        return "profile";
    }


    // ================= LOGOUT =================

    @GetMapping("/logout")
    public String logout(
            HttpSession session) {

        // Destroy session
        session.invalidate();

        // Go back to login
        return "redirect:/";
    }
}