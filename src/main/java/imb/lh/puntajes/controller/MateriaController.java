package imb.lh.puntajes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import imb.lh.puntajes.entity.Materia;
import imb.lh.puntajes.service.IMateria;
import jakarta.validation.ConstraintViolationException;
@CrossOrigin(origins = "http://127.0.0.1:5500") // Reemplaza esto con tu URL de origen
@RestController
@RequestMapping("/api/v1/materia") 
public class MateriaController { 
	
	@Autowired
	private IMateria materiaService; 
	
	@GetMapping("")
	public ResponseEntity<APIResponse<List<Materia>>> buscarTodasLasMaterias(){
		List<Materia> materias = materiaService.buscarTodos(); 
		return materias.isEmpty() ? ResponseUtil.notFound("No hay materias")
				: ResponseUtil.success(materias);
	}
	
    @GetMapping("{id}")
    public ResponseEntity<APIResponse<Materia>> buscarMateriaPorId(@PathVariable("id") Integer id){
    	Materia materia = materiaService.buscarPorId(id);
    	return materia == null ? ResponseUtil.notFound("No se encontró la materia con el identificador proporcionado")
    			: ResponseUtil.success(materia);
    }
	
	@PostMapping("")
	public ResponseEntity<APIResponse<Materia>> crearMateria(@RequestBody Materia materia){
		return materiaService.existe(materia.getId()) ? ResponseUtil.badRequest("No se pudo crear la materia, el ID ingresado ya existe")
				: ResponseUtil.created(materiaService.guardar(materia));
	}
	
	@PutMapping("")
	 public ResponseEntity<APIResponse<Materia>> modificarMateria(@RequestBody Materia materia){
		return materiaService.existe(materia.getId()) ? ResponseUtil.success(materiaService.guardar(materia))
				: ResponseUtil.badRequest("No se pudo actualizar la materia, el ID ingresado no existe");
	}
	@GetMapping("/carrera/{carreraId}")
	public ResponseEntity<APIResponse<List<Materia>>> buscarMateriasPorCarrera(@PathVariable("carreraId") Integer carreraId) {
	    List<Materia> materiasPorCarrera = materiaService.buscarPorCarreraId(carreraId);
	    return materiasPorCarrera.isEmpty() ? ResponseUtil.notFound("No hay materias para esta carrera")
	            : ResponseUtil.success(materiasPorCarrera);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<APIResponse<Materia>> eliminarMateria(@PathVariable("id") Integer id){
	   return materiaService.existe(id) ? ResponseUtil.success(null)
			   : ResponseUtil.badRequest("No existe la materia con el ID ingresado");
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<APIResponse<Materia>> handleException(Exception ex) {
		return ResponseUtil.badRequest(ex.getMessage());
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<APIResponse<Materia>> handleConstraintViolationException(ConstraintViolationException ex) {
		return ResponseUtil.handleConstraintException(ex);
	}

}
