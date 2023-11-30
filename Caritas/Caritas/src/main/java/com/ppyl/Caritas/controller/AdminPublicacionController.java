package com.ppyl.Caritas.controller;

import com.ppyl.Caritas.model.Publicacion;
import com.ppyl.Caritas.repository.PublicacionRepository;
import com.ppyl.Caritas.service.IPublicacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@Controller
@RequestMapping("/admin/publicacion")
public class AdminPublicacionController {
    @Autowired
    private IPublicacionService publicacionService;
    @Autowired
    private PublicacionRepository publicacionRepository;

    @GetMapping("/new")
    public String adminNew(Model model) {
        model.addAttribute("publicacion", new Publicacion());
        return "/admin/publicacionAdmin/new"; //retorna el nombre del html
    }


    @PostMapping
    public String create(@ModelAttribute Publicacion publicacion, @RequestParam("file") MultipartFile imagen) {
        if (!imagen.isEmpty()) {
            String rutaAbsoluta = "C:\\Users\\juanm\\OneDrive\\Escritorio\\PaginaWeb-Caritas\\Caritas\\Caritas\\src\\main\\resources\\static\\media";

            try {
                byte[] bytesImg = imagen.getBytes();
                Path rutaCompleta = Paths.get(rutaAbsoluta, imagen.getOriginalFilename());
                Files.write(rutaCompleta, bytesImg);
                publicacion.setImagen(imagen.getOriginalFilename());
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        publicacionService.create(publicacion);
        return "redirect:/admin/home";
    }

    @GetMapping("/delete/{id}")
    public String deletePublicacion(@PathVariable Long id) {
        publicacionService.delete(id);
        return "redirect:/admin/home";
    }


    @GetMapping("/detalle/{id}")
    public String detalle(@PathVariable("id") Long id,Model model) {
        Publicacion publicacion =publicacionService.findById(id);
        model.addAttribute("publicacionDetalle",publicacion);
        return "/admin/publicacionAdmin/detalle";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable("id") Long id,Model model) {
        Publicacion publicacion =publicacionService.findById(id);
        model.addAttribute("publicacionDetalle",publicacion);
        return "/admin/publicacionAdmin/editarDetalle";
    }

    @PostMapping("/editar")
    public String editPublicacion(@ModelAttribute Publicacion publicacion, @RequestParam("file") MultipartFile imagen) {
        if (!imagen.isEmpty()) {
            String rutaAbsoluta = "C:\\Users\\juanm\\OneDrive\\Escritorio\\PaginaWeb-Caritas\\Caritas\\Caritas\\src\\main\\resources\\static\\media";

            try {
                byte[] bytesImg = imagen.getBytes();
                Path rutaCompleta = Paths.get(rutaAbsoluta, imagen.getOriginalFilename());
                Files.write(rutaCompleta, bytesImg);
                publicacion.setImagen(imagen.getOriginalFilename());
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        publicacionService.create(publicacion);
        return "redirect:/admin/home";
    }

}
