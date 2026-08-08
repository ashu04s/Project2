package com.softpro.Project2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.softpro.Project2.entity.Admin;
import com.softpro.Project2.entity.Brand;
import com.softpro.Project2.service.BrandService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin/brand")
public class BrandController {

    @Autowired
    private BrandService brandService;


    // ================= ADD BRAND =================

    @GetMapping("/add")
    public String addBrand(
            Model model,
            HttpSession session) {

        Admin admin =
                (Admin) session.getAttribute("loggedAdmin");

        // Admin login check
        if (admin == null) {
            return "redirect:/admin/login";
        }

        model.addAttribute("brand", new Brand());

        return "admin/brand/add";
    }


    // ================= SAVE BRAND =================

    @PostMapping("/save")
    public String saveBrand(
            @ModelAttribute Brand brand,
            HttpSession session) {

        Admin admin =
                (Admin) session.getAttribute("loggedAdmin");

        if (admin == null) {
            return "redirect:/admin/login";
        }

        brandService.saveBrand(brand);

        return "redirect:/admin/brand/show";
    }


    // ================= SHOW BRAND =================

    @GetMapping("/show")
    public String showBrand(
            Model model,
            HttpSession session) {

        Admin admin =
                (Admin) session.getAttribute("loggedAdmin");

        if (admin == null) {
            return "redirect:/admin/login";
        }

        model.addAttribute(
                "brands",
                brandService.getAllBrand()
        );

        return "admin/brand/show";
    }


    // ================= EDIT BRAND =================

    @GetMapping("/edit/{id}")
    public String editBrand(
            @PathVariable int id,
            Model model,
            HttpSession session) {

        Admin admin =
                (Admin) session.getAttribute("loggedAdmin");

        if (admin == null) {
            return "redirect:/admin/login";
        }

        model.addAttribute(
                "brand",
                brandService.getBrandById(id)
        );

        return "admin/brand/edit";
    }


    // ================= UPDATE BRAND =================

    @PostMapping("/update")
    public String updateBrand(
            @ModelAttribute Brand brand,
            HttpSession session) {

        Admin admin =
                (Admin) session.getAttribute("loggedAdmin");

        if (admin == null) {
            return "redirect:/admin/login";
        }

        brandService.updateBrand(brand);

        return "redirect:/admin/brand/show";
    }


    // ================= DELETE BRAND =================

    @GetMapping("/delete/{id}")
    public String deleteBrand(
            @PathVariable int id,
            HttpSession session) {

        Admin admin =
                (Admin) session.getAttribute("loggedAdmin");

        if (admin == null) {
            return "redirect:/admin/login";
        }

        brandService.deleteBrand(id);

        return "redirect:/admin/brand/show";
    }

}