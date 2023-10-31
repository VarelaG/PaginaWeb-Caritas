package com.ppyl.Caritas.repository;


import com.ppyl.Caritas.model.Publicacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface PublicacionRepository extends JpaRepository<Publicacion,Long> {
    @Query("SELECT p FROM Publicacion p WHERE p.fecha BETWEEN :startDate AND :endDate ORDER BY p.fecha DESC")
    List<Publicacion> findPublicacionesRecientes(LocalDate startDate, LocalDate endDate);


}
