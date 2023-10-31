package com.ppyl.Caritas.controller;

import com.ppyl.Caritas.model.Admin;
import com.ppyl.Caritas.model.Publicacion;
import com.ppyl.Caritas.repository.PublicacionRepository;
import com.ppyl.Caritas.service.IPublicacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.plaf.multi.MultiPopupMenuUI;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;


@Controller
@RequestMapping("/publicacion")
public class PublicacionController {
    @Autowired
    private IPublicacionService publicacionService;
    @Autowired
    private PublicacionRepository publicacionRepository;

    @GetMapping("/new")
    public String adminNew(Model model) {
        model.addAttribute("publicacion", new Publicacion());
        return "/admin/publicacion/new"; //retorna el nombre del html
    }


    @PostMapping
    public String create(@ModelAttribute Publicacion publicacion, Model model, @RequestParam("file") MultipartFile imagen) {
        if (!imagen.isEmpty()) {
            String rutaAbsoluta = "C:\\Users\\gabi\\OneDrive\\Escritorio\\Caritas\\Caritas\\src\\main\\resources\\static\\media";

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
            return "/admin/publicacion/detalle";
        }

}
