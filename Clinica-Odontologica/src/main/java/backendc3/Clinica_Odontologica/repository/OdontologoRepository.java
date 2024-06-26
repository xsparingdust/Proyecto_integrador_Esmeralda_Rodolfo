package backendc3.Clinica_Odontologica.repository;

import backendc3.Clinica_Odontologica.entity.Odontologo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface OdontologoRepository extends JpaRepository<Odontologo, Long> {
    @Query("SELECT dt FROM Odontologo dt WHERE dt.nombre=?1")
    Optional<Odontologo> buscarPorNombre(String nombre);

}
