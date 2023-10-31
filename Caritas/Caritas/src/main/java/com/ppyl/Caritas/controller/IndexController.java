package com.ppyl.Caritas.controller;

import com.ppyl.Caritas.model.Publicacion;
import com.ppyl.Caritas.repository.PublicacionRepository;
import com.ppyl.Caritas.service.IPublicacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("/")
public class IndexController {
    @Autowired
    private IPublicacionService publicacionService;
    @Autowired
    private PublicacionRepository publicacionRepository;

    @GetMapping("index")
    public String getPublicacionesRecientes(Model model) {
        List<Publicacion> publicacionesRecientes = publicacionService.getPublicacionesRecientes();
        model.addAttribute("publicaciones", publicacionesRecientes);
        return "index";
    }
}



