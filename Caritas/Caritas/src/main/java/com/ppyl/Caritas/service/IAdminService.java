package com.ppyl.Caritas.service;



import com.ppyl.Caritas.model.Admin;

import java.util.List;

public interface IAdminService {
    public Admin create(Admin admin);
    public List<Admin> getAll();
    public void delete(Long id);

    public Admin buscarAdmin(String email);
}
