package grupo3.spring.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "estudiantes")
@Data
public class Estudiante {
    @Id
    private String cedula;
    private String nombre;
    private String apellido;
    private String direccion;
    private String telefono;
}
