package com.softpro.Project2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softpro.Project2.entity.Brand;
import com.softpro.Project2.repository.BrandRepository;

@Service
public class BrandService {

    @Autowired
    private BrandRepository brandRepository;

    public void saveBrand(Brand brand) {
        brandRepository.save(brand);
    }

    public List<Brand> getAllBrand() {
        return brandRepository.findAll();
    }

    public Brand getBrandById(int id) {
        return brandRepository.findById(id).orElse(null);
    }

    public void updateBrand(Brand brand) {
        brandRepository.save(brand);
    }

    public void deleteBrand(int id) {
        brandRepository.deleteById(id);
    }
}