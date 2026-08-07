package com.softpro.Project2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softpro.Project2.entity.Admin;
import com.softpro.Project2.repository.AdminRepository;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    // Save Admin
    public void saveAdmin(Admin admin) {
        adminRepository.save(admin);
    }

    // Show All Admin
    public List<Admin> getAllAdmin() {
        return adminRepository.findAll();
    }

    // Get Single Admin By Id (Edit)
    public Admin getAdminById(int id) {
        return adminRepository.findById(id).orElse(null);
    }

    // Update Admin
    public void updateAdmin(Admin admin) {
        adminRepository.save(admin);
    }

    // Delete Admin
    public void deleteAdmin(int id) {
        adminRepository.deleteById(id);
    }

}