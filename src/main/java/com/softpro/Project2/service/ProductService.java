package com.softpro.Project2.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softpro.Project2.entity.Brand;
import com.softpro.Project2.entity.Category;
import com.softpro.Project2.entity.Product;
import com.softpro.Project2.helper.ProductHelper;
import com.softpro.Project2.repository.BrandRepository;
import com.softpro.Project2.repository.CategoryRepository;
import com.softpro.Project2.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private BrandRepository brandRepository;

    // Save Product
    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    // Get All Products
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    // Get Product By Id
    public Product getProductById(Integer id) {
        return productRepository.findById(id).orElse(null);
    }

    // Update Product
    public void updateProduct(Product product) {
        productRepository.save(product);
    }

    // Delete Product
    public void deleteProduct(Integer id) {
        productRepository.deleteById(id);
    }

    // Product List with Category & Brand Name
    public List<ProductHelper> getProductList() {

        List<ProductHelper> helperList = new ArrayList<>();

        List<Product> products = productRepository.findAll();

        for (Product product : products) {

            ProductHelper helper = new ProductHelper();

            helper.setProductId(product.getProduct_id());
            helper.setProductName(product.getProduct_name());
            helper.setQuantity(product.getProduct_qty());
            helper.setCostPrice(product.getProduct_cost_price());
            helper.setSellingPrice(product.getProduct_selling_price());
            helper.setRating(product.getProduct_rating());
            helper.setImage(product.getProduct_image_sm());
            helper.setWeight(product.getProduct_weight());
            helper.setWeightType(product.getProduct_weight_type());
            helper.setStatus(product.getProduct_status());

            // Category
            Category category = categoryRepository
                    .findById(product.getFk_cat_id())
                    .orElse(null);

            if (category != null) {
                helper.setCategoryName(category.getCatName());
            }

            // Brand
            Brand brand = brandRepository
                    .findById(product.getFk_brand_id())
                    .orElse(null);

            if (brand != null) {
                helper.setBrandName(brand.getBrandName());
            }

            helperList.add(helper);
        }

        return helperList;
    }
}