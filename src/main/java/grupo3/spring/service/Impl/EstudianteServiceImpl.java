package grupo3.spring.service.Impl;

import grupo3.spring.entities.Estudiante;
import grupo3.spring.repository.EstudianteRepository;
import grupo3.spring.service.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class EstudianteServiceImpl implements EstudianteService {

    @Autowired
    EstudianteRepository estudianteRepository;

    @Override
    public Estudiante crear(Estudiante estudiante) {
        return estudianteRepository.save(estudiante);
    }

    @Override
    public Estudiante actualizar(Estudiante estudiante) {
        return estudianteRepository.save(estudiante);
    }

    @Override
    public List<Estudiante> getEstudiantes() {
        return estudianteRepository.findAll();
    }

    @Override
    public void eliminar(String cedula) {
        Estudiante estudiante = estudianteRepository.findById(cedula).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        estudianteRepository.delete(estudiante);
    }

    @Override
    public Estudiante buscarCed(String cedula) {
        Estudiante estudiante = estudianteRepository.findById(cedula).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return estudiante;
    }
}
