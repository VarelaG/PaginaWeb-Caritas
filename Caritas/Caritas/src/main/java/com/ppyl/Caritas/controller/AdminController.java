package com.ppyl.Caritas.controller;


import com.ppyl.Caritas.model.Admin;
import com.ppyl.Caritas.model.Publicacion;
import com.ppyl.Caritas.service.IAdminService;
import com.ppyl.Caritas.service.IPublicacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/admin")
public class AdminController {

    private IAdminService adminService;

    @Autowired
    private IPublicacionService publicacionService;

    @Autowired
    public AdminController(IAdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/new")
    public String adminNew(Model model) {
        model.addAttribute("admin", new Admin());
        return "admin/newAdmin"; //retorna el nombre del html
    }

    @GetMapping("/home")
    public String index(Model model) {
        List<Admin> admins = adminService.getAll();
        model.addAttribute("admin", admins);
        List<Publicacion> publicaciones = publicacionService.getAll();
        model.addAttribute("publicaciones", publicaciones);
        return "admin/home";
    }



    @PostMapping
    public String create(@ModelAttribute Admin administrator) {
        adminService.create(administrator);
        return "redirect:/admin/home"; /*esto es enviado por url*/
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        adminService.delete(id);
        return "redirect:/admin/home";
    }

}