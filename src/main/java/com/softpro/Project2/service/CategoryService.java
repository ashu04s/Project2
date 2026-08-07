package com.softpro.Project2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softpro.Project2.entity.Category;
import com.softpro.Project2.repository.CategoryRepository;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    // Save Category
    public void saveCategory(Category category) {
        categoryRepository.save(category);
    }

    // Show All Categories
    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }

    // Get Single Category By Id (Edit)
    public Category getCategoryById(int id) {
        return categoryRepository.findById(id).orElse(null);
    }

    // Update Category
    public void updateCategory(Category category) {
        categoryRepository.save(category);
    }

    // Delete Category
    public void deleteCategory(int id) {
        categoryRepository.deleteById(id);
    }

}