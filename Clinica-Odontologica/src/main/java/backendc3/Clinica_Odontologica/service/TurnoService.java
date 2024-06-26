package backendc3.Clinica_Odontologica.service;


import backendc3.Clinica_Odontologica.entity.Odontologo;
import backendc3.Clinica_Odontologica.entity.Paciente;
import backendc3.Clinica_Odontologica.entity.Turno;
import backendc3.Clinica_Odontologica.repository.OdontologoRepository;
import backendc3.Clinica_Odontologica.repository.PacienteRepository;
import backendc3.Clinica_Odontologica.repository.TurnoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TurnoService {

    @Autowired
    private TurnoRepository turnoRepository;
    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    private OdontologoRepository odontologoRepository;

    @Transactional
    public Turno guardarTurno(Turno turno) {

        if (turno.getPaciente() != null && turno.getPaciente().getId() != null) {
            Paciente paciente = pacienteRepository.findById(turno.getPaciente().getId())
                    .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));
            turno.setPaciente(paciente);
        }


        if (turno.getOdontologo() != null && turno.getOdontologo().getId() != null) {
            Odontologo odontologo = odontologoRepository.findById(turno.getOdontologo().getId())
                    .orElseThrow(() -> new RuntimeException("Odontologo no encontrado"));
            turno.setOdontologo(odontologo);
        }

        return turnoRepository.save(turno);
    }

    public List<Turno> listarTodos() {
        return turnoRepository.findAll();
    }

    public Optional<Turno> buscarPorId(Long id){
        return turnoRepository.findById(id);
    }

    public void eliminarTurno(Long id){
        turnoRepository.deleteById(id);
    }

    public void actualizarTurno(Turno turno){
        turnoRepository.save(turno);

    }
}

//    public Turno guardarTurno(Turno turno) {
//        return turnoRepository.save(turno);
//    }
//    public List<Turno> listarTodos(){
//        return turnoRepository.findAll();
//    }


