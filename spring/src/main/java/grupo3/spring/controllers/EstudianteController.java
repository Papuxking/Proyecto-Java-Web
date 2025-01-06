package grupo3.spring.controllers;

import grupo3.spring.entities.Estudiante;
import grupo3.spring.service.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ApiEstudiantes")
public class EstudianteController {
    @Autowired
    EstudianteService estudianteService;

    @GetMapping
    public List<Estudiante> getEstudiantes(){
        return estudianteService.getEstudiantes();
    }

    @PostMapping
    public Estudiante crear(@RequestBody Estudiante estudiante){
        return estudianteService.crear(estudiante);
    }

    @PutMapping
    public Estudiante actualizar(@RequestBody Estudiante estudiante){
        return estudianteService.actualizar(estudiante);
    }

    @DeleteMapping
    public void eliminar(@RequestParam String cedula){
        estudianteService.eliminar(cedula);
    }
}
