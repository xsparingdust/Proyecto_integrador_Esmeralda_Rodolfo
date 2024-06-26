package backendc3.Clinica_Odontologica.controller;

import backendc3.Clinica_Odontologica.entity.Odontologo;
import backendc3.Clinica_Odontologica.entity.Paciente;
import backendc3.Clinica_Odontologica.entity.Turno;
import backendc3.Clinica_Odontologica.exception.BadRequestException;
import backendc3.Clinica_Odontologica.service.OdontologoService;
import backendc3.Clinica_Odontologica.service.PacienteService;
import backendc3.Clinica_Odontologica.service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/turnos")
public class TurnoController {

    @Autowired
    private TurnoService turnoService;
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private OdontologoService odontologoService;


    @PostMapping
    public ResponseEntity<Turno> guardarTurno(@RequestBody Turno turno) {
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPorId(turno.getPaciente().getId());
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarPorId(turno.getOdontologo().getId());

        if (pacienteBuscado.isPresent() && odontologoBuscado.isPresent()) {
            Turno turnoGuardado = turnoService.guardarTurno(turno);
            return ResponseEntity.ok(turnoGuardado);
        } else {
            throw new BadRequestException("No se encontró el paciente o el odontólogo especificado.");
        }
    }


    @GetMapping("/buscarTodos")
    public ResponseEntity<List<Turno>> buscarTodos(){
        return ResponseEntity.ok(turnoService.listarTodos());
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Optional<Paciente>> buscarPorPaciente(@PathVariable Long id) {
        return ResponseEntity.ok(pacienteService.buscarPorId(id));
    }
    @GetMapping
    public ResponseEntity<Optional<Odontologo>> buscarPorOdontologo(@PathVariable Long id) {
        return ResponseEntity.ok(odontologoService.buscarPorId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarTurno(@PathVariable Long id){
        Optional<Turno> turnoBuscado= turnoService.buscarPorId(id);
        if (turnoBuscado.isPresent()){
            turnoService.eliminarTurno(id);
            return ResponseEntity.ok("turno eliminado con exito");
        }else {
           return ResponseEntity.notFound().build();
        }
    }


    @PutMapping("/{update}")
    public ResponseEntity<String> actualizarTurno(@PathVariable Turno turno) {
        Optional<Turno> turnoBuscado = turnoService.buscarPorId(turno.getId());
        if (turnoBuscado.isPresent()) {
            turnoService.actualizarTurno(turno);
            return ResponseEntity.ok("turno actualizado con exito");
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
