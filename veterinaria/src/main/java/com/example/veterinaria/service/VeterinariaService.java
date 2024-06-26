package com.example.veterinaria.service;

import com.example.veterinaria.entity.Veterinaria;
import com.example.veterinaria.repository.VeterinariaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VeterinariaService {
    @Autowired
    private VeterinariaRepository veterinariaRepository;
    public Veterinaria registrarVeterinaria(Veterinaria veterinaria){
        return veterinariaRepository.save(veterinaria);

    }
}
