package com.softpro.Project2.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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


    // ================= ADD PRODUCT =================

    @GetMapping("/add")
    public String addProduct(Model model) {

        model.addAttribute("product", new Product());
        model.addAttribute("categoryList", categoryRepository.findAll());
        model.addAttribute("brandList", brandRepository.findAll());

        return "admin/product/add";
    }


    // ================= SAVE PRODUCT =================

    @PostMapping("/save")
    public String saveProduct(
            @ModelAttribute Product product,
            @RequestParam("file1") MultipartFile file1,
            @RequestParam("file2") MultipartFile file2)
            throws IOException {

        // Upload folder
        String uploadDir = System.getProperty("user.dir") + "/uploads/";

        Path uploadPath = Paths.get(uploadDir);

        // Create uploads folder if it doesn't exist
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }


        // ---------- SMALL IMAGE ----------

        if (!file1.isEmpty()) {

            String fileName = file1.getOriginalFilename();

            Path filePath = uploadPath.resolve(fileName);

            file1.transferTo(filePath.toFile());

            product.setProduct_image_sm(fileName);
        }


        // ---------- LARGE IMAGE ----------

        if (!file2.isEmpty()) {

            String fileName = file2.getOriginalFilename();

            Path filePath = uploadPath.resolve(fileName);

            file2.transferTo(filePath.toFile());

            product.setProduct_image_lg(fileName);
        }


        // Save product into database
        productService.saveProduct(product);

        return "redirect:/admin/product/list";
    }


    // ================= PRODUCT LIST =================

    @GetMapping("/list")
    public String listProduct(Model model) {

        model.addAttribute(
                "products",
                productService.getProductList()
        );

        return "admin/product/list";
    }


    // ================= EDIT PRODUCT =================

    @GetMapping("/edit/{id}")
    public String editProduct(
            @PathVariable Integer id,
            Model model) {

        Product product = productService.getProductById(id);

        model.addAttribute("product", product);
        model.addAttribute(
                "categoryList",
                categoryRepository.findAll()
        );
        model.addAttribute(
                "brandList",
                brandRepository.findAll()
        );

        return "admin/product/edit";
    }


    // ================= UPDATE PRODUCT =================

    @PostMapping("/update")
    public String updateProduct(
            @ModelAttribute Product product,
            @RequestParam("file1") MultipartFile file1,
            @RequestParam("file2") MultipartFile file2)
            throws IOException {

        // Upload folder
        String uploadDir = System.getProperty("user.dir") + "/uploads/";

        Path uploadPath = Paths.get(uploadDir);

        // Create folder if it doesn't exist
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }


        // ---------- SMALL IMAGE ----------

        if (!file1.isEmpty()) {

            String fileName = file1.getOriginalFilename();

            Path filePath = uploadPath.resolve(fileName);

            file1.transferTo(filePath.toFile());

            product.setProduct_image_sm(fileName);
        }


        // ---------- LARGE IMAGE ----------

        if (!file2.isEmpty()) {

            String fileName = file2.getOriginalFilename();

            Path filePath = uploadPath.resolve(fileName);

            file2.transferTo(filePath.toFile());

            product.setProduct_image_lg(fileName);
        }


        // Save updated product
        productService.saveProduct(product);

        return "redirect:/admin/product/list";
    }


    // ================= DELETE PRODUCT =================

    @GetMapping("/delete/{id}")
    public String deleteProduct(
            @PathVariable Integer id) {

        productService.deleteProduct(id);

        return "redirect:/admin/product/list";
    }
}