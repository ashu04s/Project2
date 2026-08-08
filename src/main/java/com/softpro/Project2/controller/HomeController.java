package com.softpro.Project2.controller;

import com.softpro.Project2.entity.User;
import com.softpro.Project2.service.ProductService;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private ProductService productService;


    // ================= HOME =================

    @GetMapping("/home")
    public String home(Model model) {

        model.addAttribute(
                "products",
                productService.getHomeProductList()
        );

        return "index";
    }


    // ================= ABOUT =================

    @GetMapping("/about")
    public String about() {

        return "about";
    }


    // ================= CONTACT =================

    @GetMapping("/contact")
    public String contact() {

        return "contact";
    }


    // ================= PRODUCTS =================

    @GetMapping("/products")
    public String products() {

        return "product";
    }


    // ================= CART =================

    @GetMapping("/cart")
    public String cart() {

        return "cart";
    }


    // ================= CHECKOUT =================

    @GetMapping("/checkout")
    public String checkout(
            HttpSession session) {

        User user =
                (User) session.getAttribute(
                        "loggedUser"
                );


        if (user == null) {

            return "redirect:/login";
        }


        return "checkout";
    }

}