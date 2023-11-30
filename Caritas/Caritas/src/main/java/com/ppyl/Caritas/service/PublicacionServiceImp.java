package com.ppyl.Caritas.service;

import com.ppyl.Caritas.model.Publicacion;
import com.ppyl.Caritas.repository.PublicacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
public class PublicacionServiceImp implements IPublicacionService, UserDetailsService {
    @Autowired
    private PublicacionRepository repository;

    public PublicacionServiceImp(PublicacionRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }

    @Override
    public List<Publicacion> getAll() {
        return repository.findAll();
    }

    @Override
    public void create(Publicacion publicacion) {
        publicacion.setFecha(LocalDate.now());
        repository.save(publicacion);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Publicacion findById(Long id){
        List<Publicacion> publicacions= getAll();
        for(Publicacion p : publicacions){
            if(p.getId() == id){
                return p;
            }
        }
        return null;
    }
    @Override
    public List<Publicacion> getPublicacionesRecientes() {
        LocalDate fechaActual = LocalDate.now();
        LocalDate fechaDosDiasAntes = fechaActual.minus(2, ChronoUnit.DAYS);

        List<Publicacion> publicacionesRecientes = repository.findPublicacionesRecientes(
                fechaDosDiasAntes, fechaActual
        );

        if (publicacionesRecientes.size() >= 3) {

            return publicacionesRecientes.subList(0, 3);
        } else {
            return publicacionesRecientes;
        }
    }


    public void setRepository(PublicacionRepository repository) {
        this.repository = repository;
    }
}
