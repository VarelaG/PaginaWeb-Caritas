package com.ppyl.Caritas.service;

import com.ppyl.Caritas.model.Publicacion;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface IPublicacionService {
    List<Publicacion> getAll();

    void create(Publicacion publicacion);

    void delete(Long id);

    Publicacion findById(Long id);

    List<Publicacion> getPublicacionesRecientes();
}
