package com.example.veterinaria.service;

import com.example.veterinaria.entity.Mascota;
import com.example.veterinaria.repository.MascotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MascotaService {
    @Autowired
    private MascotaRepository mascotaRepository;
    public Mascota registrarMascota(Mascota mascota){
        return mascotaRepository.save(mascota);

    }
}
