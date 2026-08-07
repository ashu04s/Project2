package com.softpro.Project2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.softpro.Project2.entity.Brand;
import com.softpro.Project2.service.BrandService;

@Controller
@RequestMapping("/admin/brand")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @GetMapping("/add")
    public String addBrand(Model model) {
        model.addAttribute("brand", new Brand());
        return "admin/brand/add";
    }

    @PostMapping("/save")
    public String saveBrand(@ModelAttribute Brand brand) {
        brandService.saveBrand(brand);
        return "redirect:/admin/brand/show";
    }

    @GetMapping("/show")
    public String showBrand(Model model) {
        model.addAttribute("brands", brandService.getAllBrand());
        return "admin/brand/show";
    }

    @GetMapping("/edit/{id}")
    public String editBrand(@PathVariable int id, Model model) {
        model.addAttribute("brand", brandService.getBrandById(id));
        return "admin/brand/edit";
    }

    @PostMapping("/update")
    public String updateBrand(@ModelAttribute Brand brand) {
        brandService.updateBrand(brand);
        return "redirect:/admin/brand/show";
    }

    @GetMapping("/delete/{id}")
    public String deleteBrand(@PathVariable int id) {
        brandService.deleteBrand(id);
        return "redirect:/admin/brand/show";
    }
}