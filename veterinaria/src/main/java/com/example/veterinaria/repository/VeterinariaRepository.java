package com.example.veterinaria.repository;

import com.example.veterinaria.entity.Veterinaria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface VeterinariaRepository extends JpaRepository<Veterinaria, Long> {

    @Query("SELECT dt FROM Mascota dt WHERE dt.nombre=?1")
    Optional<Veterinaria> findByName(String nombre);
}

}

