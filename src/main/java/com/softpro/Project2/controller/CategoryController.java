package com.softpro.Project2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.softpro.Project2.entity.Admin;
import com.softpro.Project2.entity.Category;
import com.softpro.Project2.service.CategoryService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


    // ================= Show Category =================

    @GetMapping("/show")
    public String showCategory(
            Model model,
            HttpSession session) {

        Admin admin =
                (Admin) session.getAttribute("loggedAdmin");

        // Admin login check
        if (admin == null) {
            return "redirect:/admin/login";
        }

        model.addAttribute(
                "categories",
                categoryService.getAllCategory()
        );

        model.addAttribute("activeMenu", "category");

        return "admin/category/cat_show";
    }


    // ================= Add Category Page =================

    @GetMapping("/add")
    public String addCategory(
            Model model,
            HttpSession session) {

        Admin admin =
                (Admin) session.getAttribute("loggedAdmin");

        if (admin == null) {
            return "redirect:/admin/login";
        }

        model.addAttribute(
                "category",
                new Category()
        );

        model.addAttribute(
                "activeMenu",
                "category"
        );

        return "admin/category/cat_add";
    }


    // ================= Save Category =================

    @PostMapping("/save")
    public String saveCategory(
            @ModelAttribute Category category,
            HttpSession session) {

        Admin admin =
                (Admin) session.getAttribute("loggedAdmin");

        if (admin == null) {
            return "redirect:/admin/login";
        }

        categoryService.saveCategory(category);

        return "redirect:/admin/category/show";
    }


    // ================= Edit Category =================

    @GetMapping("/edit/{id}")
    public String editCategory(
            @PathVariable Integer id,
            Model model,
            HttpSession session) {

        Admin admin =
                (Admin) session.getAttribute("loggedAdmin");

        if (admin == null) {
            return "redirect:/admin/login";
        }

        Category category =
                categoryService.getCategoryById(id);

        model.addAttribute(
                "category",
                category
        );

        model.addAttribute(
                "activeMenu",
                "category"
        );

        return "admin/category/cat_edit";
    }


    // ================= Update Category =================

    @PostMapping("/update")
    public String updateCategory(
            @ModelAttribute Category category,
            HttpSession session) {

        Admin admin =
                (Admin) session.getAttribute("loggedAdmin");

        if (admin == null) {
            return "redirect:/admin/login";
        }

        categoryService.updateCategory(category);

        return "redirect:/admin/category/show";
    }


    // ================= Delete Category =================

    @GetMapping("/delete/{id}")
    public String deleteCategory(
            @PathVariable Integer id,
            HttpSession session) {

        Admin admin =
                (Admin) session.getAttribute("loggedAdmin");

        if (admin == null) {
            return "redirect:/admin/login";
        }

        categoryService.deleteCategory(id);

        return "redirect:/admin/category/show";
    }

}