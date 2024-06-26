
package backendc3.Clinica_Odontologica.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "odontologos")
public class Odontologo {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)

        private Long id;
        @Column
        private String numeroMatricula;
        @Column
        private String nombre;
        @Column
        private String apellido;


        public Odontologo() {
        }

        public Odontologo(Long id) {
                this.id = id;
        }

        public Odontologo(Long id, String numeroMatricula, String nombre, String apellido) {
                this.id = id;
                this.numeroMatricula = numeroMatricula;
                this.nombre = nombre;
                this.apellido = apellido;
        }

        public Odontologo(String numeroMatricula, String nombre, String apellido) {
                this.numeroMatricula = numeroMatricula;
                this.nombre = nombre;
                this.apellido = apellido;
        }


        @Override
        public String toString() {
                return "Odontologo{" +
                        "id=" + id +
                        ", numeroMatricula='" + numeroMatricula + '\'' +
                        ", nombre='" + nombre + '\'' +
                        ", apellido='" + apellido + '\'' +
                        '}';
        }
}