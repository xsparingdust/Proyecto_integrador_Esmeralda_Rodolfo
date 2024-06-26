package backendc3.Clinica_Odontologica.controller;

import backendc3.Clinica_Odontologica.entity.Odontologo;
import backendc3.Clinica_Odontologica.entity.Paciente;
import backendc3.Clinica_Odontologica.exception.ResourceNotFoundException;
import backendc3.Clinica_Odontologica.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;


@RestController  //para trabajar sin tecnologia de vista
// @Controller<-- es controller pq vamos a usar una tecnologia de vista



@RequestMapping("/pacientes")
public class PacienteController {
@Autowired
    private  PacienteService pacienteService;

    @PostMapping //--> nos permite persistir los datos que vienen desde la vista
    public ResponseEntity<Paciente> guardarPaciente(@RequestBody Paciente paciente) {
        return  ResponseEntity.ok(pacienteService.guardarPaciente(paciente));
    }


    @GetMapping
    public  ResponseEntity <List<Paciente>> buscarTodos(){
         return  ResponseEntity.ok(pacienteService.listarTodos());

     }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarPaciente(@PathVariable Long id) throws ResourceNotFoundException {

        Optional<Paciente> pacienteBuscado = pacienteService.buscarPorId(id);
        if (pacienteBuscado.isPresent()) {
            pacienteService.eliminarPaciente(id);
            return ResponseEntity.ok("paciente eliminado con exito");
        } else {
            throw new ResourceNotFoundException("paciente no encontrado");
//            return ResponseEntity.notFound().build();
        }

    }



    @PutMapping("/update")
    public ResponseEntity<String> actualizarPaciente(@RequestBody Paciente paciente) {

       Optional<Paciente> pacienteBuscado = pacienteService.buscarPorId(paciente.getId());
        if (pacienteBuscado.isPresent()) {
            pacienteService.actualizarPaciente(paciente);
            return ResponseEntity.ok("paciente actualizado con exito");
        } else {
            return ResponseEntity.badRequest().build();
        }

    }
    @GetMapping("/buscar/{id}")
    public ResponseEntity<Optional<Paciente>> buscarPorPaciente(@PathVariable Long id) {
        return ResponseEntity.ok(pacienteService.buscarPorId(id));
    }
    @GetMapping("/buscar/email/{email}")
    public  ResponseEntity<Optional<Paciente>> buacarPorEmail(@PathVariable String email){
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPorEmail(email);
        if (pacienteBuscado.isPresent()) {
            return ResponseEntity.ok(pacienteBuscado);
        } else {
            return ResponseEntity.notFound().build();
        }    }

}