package com.example.directorTecnico.controller;

import com.example.directorTecnico.entity.DirectorTecnico;
import com.example.directorTecnico.entity.Jugador;
import com.example.directorTecnico.service.JugadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("jugadores")
public class JugadorController {
    @Autowired
    private JugadorService jugadorService;
    @PostMapping
    public ResponseEntity<Jugador> registrarJugador(@RequestBody Jugador jugador) {
        return ResponseEntity.ok(jugadorService.registrarJugador(jugador));
    }



}
