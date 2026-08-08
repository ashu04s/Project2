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


    // ================= SAVE PRODUCT =================

    public void saveProduct(Product product) {

        productRepository.save(product);
    }


    // ================= GET ALL PRODUCTS =================

    public List<Product> getAllProduct() {

        return productRepository.findAll();
    }


    // ================= GET PRODUCT BY ID =================

    public Product getProductById(Integer id) {

        return productRepository
                .findById(id)
                .orElse(null);
    }


    // ================= UPDATE PRODUCT =================

    public void updateProduct(Product product) {

        productRepository.save(product);
    }


    // ================= DELETE PRODUCT =================

    public void deleteProduct(Integer id) {

        productRepository.deleteById(id);
    }


    // =====================================================
    // ALL PRODUCTS
    // =====================================================

    public List<ProductHelper> getProductList() {

        List<ProductHelper> helperList =
                new ArrayList<>();


        // Get products
        List<Product> products =
                productRepository.findAll();


        // Get categories only once
        List<Category> categories =
                categoryRepository.findAll();


        // Get brands only once
        List<Brand> brands =
                brandRepository.findAll();


        for (Product product : products) {

            ProductHelper helper =
                    createHelper(
                            product,
                            categories,
                            brands
                    );

            helperList.add(helper);
        }


        return helperList;
    }


    // =====================================================
    // HOME PRODUCTS
    // =====================================================

    public List<ProductHelper> getHomeProductList() {

        List<ProductHelper> helperList =
                new ArrayList<>();


        // Get active products
        List<Product> products =
                productRepository
                        .findHomeProducts("Active");


        // Get categories once
        List<Category> categories =
                categoryRepository.findAll();


        // Get brands once
        List<Brand> brands =
                brandRepository.findAll();


        // Convert products
        for (Product product : products) {

            ProductHelper helper =
                    createHelper(
                            product,
                            categories,
                            brands
                    );

            helperList.add(helper);
        }


        // Only 8 products on Home
        if (helperList.size() > 8) {

            return helperList.subList(0, 8);
        }


        return helperList;
    }


    // =====================================================
    // CREATE PRODUCT HELPER
    // =====================================================

    private ProductHelper createHelper(
            Product product,
            List<Category> categories,
            List<Brand> brands) {


        ProductHelper helper =
                new ProductHelper();


        // ================= PRODUCT =================

        helper.setProductId(
                product.getProduct_id()
        );

        helper.setProductName(
                product.getProduct_name()
        );

        helper.setQuantity(
                product.getProduct_qty()
        );

        helper.setCostPrice(
                product.getProduct_cost_price()
        );

        helper.setSellingPrice(
                product.getProduct_selling_price()
        );

        helper.setRating(
                product.getProduct_rating()
        );

        helper.setImage(
                product.getProduct_image_sm()
        );

        helper.setWeight(
                product.getProduct_weight()
        );

        helper.setWeightType(
                product.getProduct_weight_type()
        );

        helper.setStatus(
                product.getProduct_status()
        );


        // ================= CATEGORY =================

        for (Category category : categories) {

            if (category.getCatId()
                    .equals(product.getFk_cat_id())) {

                helper.setCategoryName(
                        category.getCatName()
                );

                break;
            }
        }


        // ================= BRAND =================

        for (Brand brand : brands) {

            if (brand.getBrandId()
                    .equals(product.getFk_brand_id())) {

                helper.setBrandName(
                        brand.getBrandName()
                );

                break;
            }
        }


        return helper;
    }

}