package imb.lh.puntajes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import imb.lh.puntajes.entity.Carrera;
import imb.lh.puntajes.service.ICarrera;
import jakarta.validation.ConstraintViolationException;

@RestController
@RequestMapping("/api/v1/carrera") 
public class CarreraController { 
	
	@Autowired
	private ICarrera carreraService; 
	
	@GetMapping("")
	public ResponseEntity<APIResponse<List<Carrera>>> buscarTodasLasCarreras(){
		List<Carrera> carreras = carreraService.buscarTodos(); 
		return carreras.isEmpty() ? ResponseUtil.notFound("No hay carreras")
				: ResponseUtil.success(carreras);
	}
	
    @GetMapping("{id}")
    public ResponseEntity<APIResponse<Carrera>> buscarCarreraPorId(@PathVariable("id") Integer id){
    	Carrera carrera = carreraService.buscarPorId(id);
    	return carrera == null ? ResponseUtil.notFound("No se encontró la carrera con el identificador proporcionado")
    			: ResponseUtil.success(carrera);
    }
	
	@PostMapping("")
	public ResponseEntity<APIResponse<Carrera>> crearCarrera(@RequestBody Carrera carrera){
		return carreraService.existe(carrera.getId()) ? ResponseUtil.badRequest("No se pudo crear la carrera, el ID ingresado ya existe")
				: ResponseUtil.created(carreraService.guardar(carrera));
	}
	
	@PutMapping("")
	 public ResponseEntity<APIResponse<Carrera>> modificarCarrera(@RequestBody Carrera carrera){
		return carreraService.existe(carrera.getId()) ? ResponseUtil.success(carreraService.guardar(carrera))
				: ResponseUtil.badRequest("No se pudo actualizar la carrera, el ID ingresado no existe");
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<APIResponse<Carrera>> eliminarCarrera(@PathVariable("id") Integer id){
	   return carreraService.existe(id) ? ResponseUtil.success(null)
			   : ResponseUtil.badRequest("No existe la carrera con el ID ingresado");
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<APIResponse<Carrera>> handleException(Exception ex) {
		return ResponseUtil.badRequest(ex.getMessage());
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<APIResponse<Carrera>> handleConstraintViolationException(ConstraintViolationException ex) {
		return ResponseUtil.handleConstraintException(ex);
	}

}
