package grupo3.spring.controllers;

import grupo3.spring.entities.Estudiante;
import grupo3.spring.service.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/estudiantes")
public class EstudianteVistaController {
    @Autowired
    private EstudianteService estudianteService;

    @GetMapping
    public String listarEstudiantes(Model model){
        model.addAttribute("estudiantes",estudianteService.getEstudiantes());
        return "lista";
    }

    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model){
        model.addAttribute("estudiante",new Estudiante());
        return "formulario";
    }

    @PostMapping("/guardar")
    public String guardarEstudiante(Estudiante estudiante){
        estudianteService.crear(estudiante);
        return "redirect:/estudiantes";
    }

    @GetMapping("/editar/{cedula}")
    public String mostrarFormularioEditar(@PathVariable String cedula, Model model){
        Estudiante estudiante = estudianteService.buscarCed(cedula);
        model.addAttribute("estudiante",estudiante);
        return "formulario";
    }

    @GetMapping("/eliminar/{cedula}")
    public String eliminarEstudiante(@PathVariable String cedula){
        estudianteService.eliminar(cedula);
        return "redirect:/estudiantes";
    }

    @GetMapping("/buscar")
    public String buscarEstudiante(@RequestParam(required = false) String cedula, Model model) {
        if (cedula == null || cedula.trim().isEmpty()) {
            model.addAttribute("estudiantes", estudianteService.getEstudiantes());
        } else {
            try {
                Estudiante estudiante = estudianteService.buscarCed(cedula);
                model.addAttribute("estudiantes", List.of(estudiante));
            } catch (Exception e) {
                model.addAttribute("estudiantes", List.of());
                model.addAttribute("mensajeError", "No se encontró ningún estudiante con la cédula: " + cedula);
            }
        }
        return "lista";
    }

}
