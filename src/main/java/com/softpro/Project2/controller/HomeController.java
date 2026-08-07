package com.softpro.Project2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.softpro.Project2.service.ProductService;

@Controller
public class HomeController {

    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public String main() {
        return "main";
    }

    @GetMapping("/home")
    public String home(Model model) {

        model.addAttribute("products", productService.getProductList());

        return "index";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }

    @GetMapping("/products")
    public String products() {
        return "product";
    }
    @GetMapping("/cart")
    public String cart() {
        return "cart";
    }
}