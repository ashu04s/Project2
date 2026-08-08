package com.softpro.Project2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.softpro.Project2.entity.Product;
import com.softpro.Project2.service.ProductService;

@Controller
public class ProductDetailsController {

    @Autowired
    private ProductService productService;

    @GetMapping("/product/{id}")
    public String productDetails(@PathVariable Integer id, Model model) {

        Product product = productService.getProductById(id);

        model.addAttribute("product", product);

        return "product-details";
    }
}