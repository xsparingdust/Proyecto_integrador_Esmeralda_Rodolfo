package com.example.veterinaria.repository;

import com.example.veterinaria.entity.Mascota;
import com.example.veterinaria.entity.Veterinaria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MascotaRepository extends JpaRepository<Mascota, Long> {

    @Query("SELECT dt FROM Mascota dt WHERE dt.nombre=?1")
    Optional<Mascota> buscarMascotaPorTipo(String nombre);


}
