package grupo3.spring.service;

import grupo3.spring.entities.Estudiante;

import java.util.List;

public interface EstudianteService {
    Estudiante crear(Estudiante estudiante);
    Estudiante actualizar(Estudiante estudiante);
    List<Estudiante> getEstudiantes();
    void eliminar(String cedula);
    Estudiante buscarCed(String cedula);
}
