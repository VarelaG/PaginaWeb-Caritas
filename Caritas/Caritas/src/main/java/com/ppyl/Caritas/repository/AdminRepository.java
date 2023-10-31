package com.ppyl.Caritas.repository;

import com.ppyl.Caritas.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin,Long> {
    public Admin findByUsername(String username);
}
