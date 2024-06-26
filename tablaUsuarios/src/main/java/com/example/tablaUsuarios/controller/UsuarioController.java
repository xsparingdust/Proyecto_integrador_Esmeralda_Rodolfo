package com.example.tablaUsuarios.controller;

import com.example.tablaUsuarios.entity.Usuario;
import com.example.tablaUsuarios.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<Usuario> guardarUsuario(@RequestBody Usuario usuario) {
        return ResponseEntity.ok(usuarioService.guardarUsuario(usuario));
    }

    @PutMapping
    public ResponseEntity<String> actualizarUsuario(@RequestBody Usuario usuario) {
        Optional<Usuario> usuarioBuscado = usuarioService.buscarPorId(usuario.getId());
        if (usuarioBuscado.isPresent()) {
            usuarioService.actualizarUsuario(usuario);
            return ResponseEntity.ok("Movimiento actualizado con exito!");

        } else {
            return ResponseEntity.badRequest().body("movimiento no encontrado");
        }
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> listarTodos(){
        return ResponseEntity.ok(usuarioService.listarTodos());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Usuario>> buscarPorId(@PathVariable Long id){
        return ResponseEntity.ok(usuarioService.buscarPorId(id));
    }}