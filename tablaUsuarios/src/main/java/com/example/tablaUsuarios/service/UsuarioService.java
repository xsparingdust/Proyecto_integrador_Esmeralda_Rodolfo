package com.example.tablaUsuarios.service;

import com.example.tablaUsuarios.entity.Usuario;
import com.example.tablaUsuarios.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    //metodos manuales del service

    public Usuario guardarUsuario(Usuario usuario){
        return usuarioRepository.save(usuario);
    }
    public List<Usuario> listarTodos(){
        return  usuarioRepository.findAll();
    }
    public Usuario actualizarUsuario(Usuario usuario){ return usuarioRepository.save(usuario);}
    public Optional<Usuario> buscarPorId(Long id){
        return usuarioRepository.findAllById(id);
    }

}
