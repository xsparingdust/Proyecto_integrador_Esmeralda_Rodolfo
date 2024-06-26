package backendc3.Clinica_Odontologica.controller;

import backendc3.Clinica_Odontologica.entity.Odontologo;
import backendc3.Clinica_Odontologica.entity.Paciente;
import backendc3.Clinica_Odontologica.exception.ResourceNotFoundException;
import backendc3.Clinica_Odontologica.service.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {
    @Autowired
    private  OdontologoService odontologoService;


    @PostMapping //--> nos permite persistir los datos que vienen desde la vista
    public ResponseEntity<Odontologo> guardarOdontologo(@RequestBody Odontologo odontologo) {
        return  ResponseEntity.ok(odontologoService.guardarOdontologo(odontologo));
    }


    @GetMapping
    public  ResponseEntity <List<Odontologo>> buscarTodos(){
        return  ResponseEntity.ok(odontologoService.listarTodos());

    }


    @PutMapping("/update")
    public ResponseEntity<String> actualizarOdontologo(@RequestBody Odontologo odontologo) {
        try {
            Optional<Odontologo> odontologoBuscado = odontologoService.buscarPorId(odontologo.getId());
            if (odontologoBuscado.isPresent()) {
                odontologoService.actualizarOdontologo(odontologo);
                return ResponseEntity.ok("Odontólogo actualizado con éxito");
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al actualizar el odontólogo: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarOdontologo(@PathVariable Long id) throws ResourceNotFoundException{

        Optional<Odontologo> odontologoBuscado = odontologoService.buscarPorId(id);
        if (odontologoBuscado.isPresent()) {
            odontologoService.eliminarOdontologo(id);
            return ResponseEntity.ok("odontologo eliminado con exito");
        } else {

            throw new ResourceNotFoundException("odontologo o encontrado");
//            return ResponseEntity.notFound().build();
        }

    }


    @GetMapping("/buscar/{id}")
    public ResponseEntity<Optional<Odontologo>> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(odontologoService.buscarPorId(id));
    }


}

