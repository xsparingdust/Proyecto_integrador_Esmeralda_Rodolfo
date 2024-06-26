package backendc3.Clinica_Odontologica.service;

import backendc3.Clinica_Odontologica.entity.Turno;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TurnoServiceTest {

    @Autowired
    private TurnoService turnoService;


    @Test
    @Order(1)
    public void guardarTurno() {
        Turno turno = new Turno("1", "1", LocalDate.of(2024, 6, 20));
        Turno turnoGuardado = turnoService.guardarTurno(turno);
        assertEquals(1L, turnoGuardado.getId());
    }

    @Test
    @Order(2)
    public void buscarTurnoPorId(){
        Long id= 1L;
        Optional<Turno> turnoBuscado= turnoService.buscarPorId(id);
        assertNotNull(turnoBuscado.get());
    }

    @Test
    @Order(3)
    public void actualizarTurno(){
        Long id= 1L;
        Turno turno= new Turno(id,"1", "1", LocalDate.of(2024, 6, 20));
        turnoService.actualizarTurno(turno);
        Optional<Turno> turnoBuscado= turnoService.buscarPorId(id);
        assertEquals(1L, turnoBuscado.get().getId());
    }

    @Order(4)
    public void ListarTodos() {
        List<Turno> listaTurnos = turnoService.listarTodos();
        assertTrue(listaTurnos.size() > 0, "La lista de turnos no está vacía");
    }

    // Otros métodos de prueba omitidos por brevedad

    @Test
    @Order(5)
    public void eliminarTurno(){
        turnoService.eliminarTurno(1L);
        Optional<Turno> turnoEliminado= turnoService.buscarPorId(1L);
        assertFalse(turnoEliminado.isPresent());
    }
}


