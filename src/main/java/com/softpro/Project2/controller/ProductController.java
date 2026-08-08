package com.softpro.Project2.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.softpro.Project2.entity.Product;
import com.softpro.Project2.repository.BrandRepository;
import com.softpro.Project2.repository.CategoryRepository;
import com.softpro.Project2.service.ProductService;

@Controller
@RequestMapping("/admin/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private BrandRepository brandRepository;

    // ================= Add Product =================

    @GetMapping("/add")
    public String addProduct(Model model) {

        model.addAttribute("product", new Product());
        model.addAttribute("categoryList", categoryRepository.findAll());
        model.addAttribute("brandList", brandRepository.findAll());

        return "admin/product/add";
    }

    // ================= Save Product =================

    @PostMapping("/save")
    public String saveProduct(@ModelAttribute Product product,
                              @RequestParam("file1") MultipartFile file1,
                              @RequestParam("file2") MultipartFile file2)
            throws IOException {

        String uploadDir = System.getProperty("user.dir")
                + "/src/main/resources/static/uploads/";

        Path uploadPath = Paths.get(uploadDir);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        if (!file1.isEmpty()) {
            String fileName = file1.getOriginalFilename();
            file1.transferTo(uploadPath.resolve(fileName));
            product.setProduct_image_sm(fileName);
        }

        if (!file2.isEmpty()) {
            String fileName = file2.getOriginalFilename();
            file2.transferTo(uploadPath.resolve(fileName));
            product.setProduct_image_lg(fileName);
        }

        productService.saveProduct(product);

        return "redirect:/admin/product/list";
    }

    // ================= Product List =================

    @GetMapping("/list")
    public String listProduct(Model model) {

        model.addAttribute("products", productService.getProductList());

        return "admin/product/list";
    }

    // ================= Edit Product =================

    @GetMapping("/edit/{id}")
    public String editProduct(@PathVariable Integer id, Model model) {

        Product product = productService.getProductById(id);

        model.addAttribute("product", product);
        model.addAttribute("categoryList", categoryRepository.findAll());
        model.addAttribute("brandList", brandRepository.findAll());

        return "admin/product/edit";
    }

    // ================= Update Product =================

    @PostMapping("/update")
    public String updateProduct(@ModelAttribute Product product,
                                @RequestParam("file1") MultipartFile file1,
                                @RequestParam("file2") MultipartFile file2)
            throws IOException {

        String uploadDir = System.getProperty("user.dir")
                + "/src/main/resources/static/uploads/";

        Path uploadPath = Paths.get(uploadDir);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        if (!file1.isEmpty()) {
            String fileName = file1.getOriginalFilename();
            file1.transferTo(uploadPath.resolve(fileName));
            product.setProduct_image_sm(fileName);
        }

        if (!file2.isEmpty()) {
            String fileName = file2.getOriginalFilename();
            file2.transferTo(uploadPath.resolve(fileName));
            product.setProduct_image_lg(fileName);
        }

        productService.saveProduct(product);

        return "redirect:/admin/product/list";
    }

    // ================= Delete Product =================

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Integer id) {

        productService.deleteProduct(id);

        return "redirect:/admin/product/list";
    }
    

}