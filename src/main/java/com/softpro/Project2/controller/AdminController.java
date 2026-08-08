package com.softpro.Project2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.softpro.Project2.entity.Admin;
import com.softpro.Project2.repository.AdminRepository;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminRepository adminRepository;


    // ================= ADMIN LOGIN PAGE =================

    @GetMapping("/login")
    public String loginPage() {

        return "admin/login";
    }


    // ================= ADMIN LOGIN =================

    @PostMapping("/login")
    public String loginAdmin(
            @RequestParam String email,
            @RequestParam String password,
            HttpSession session,
            Model model) {

        Admin admin =
                adminRepository.findByAdminEmail(email);

        if (admin != null
                && admin.getAdminPassword().equals(password)
                && "active".equalsIgnoreCase(admin.getAdminStatus())) {

            session.setAttribute("loggedAdmin", admin);

            return "redirect:/admin/dashboard";
        }

        model.addAttribute(
                "error",
                "Invalid admin email or password"
        );

        return "admin/login";
    }


    // ================= ADMIN DASHBOARD =================

    @GetMapping("/dashboard")
    public String dashboard(
            HttpSession session,
            Model model) {

        Admin admin =
                (Admin) session.getAttribute("loggedAdmin");

        // IMPORTANT: Session check
        if (admin == null) {

            return "redirect:/admin/login";
        }

        // Send admin to dashboard
        model.addAttribute("admin", admin);

        return "admin/dashboard";
    }


    // ================= ADMIN LOGOUT =================

    @GetMapping("/logout")
    public String logout(HttpSession session) {

        session.removeAttribute("loggedAdmin");

        return "redirect:/admin/login";
    }

}