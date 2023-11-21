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

import imb.lh.puntajes.entity.Aplicante; // Cambio en el nombre de la entidad
import imb.lh.puntajes.service.IAplicante; // Cambio en el nombre del servicio
import jakarta.validation.ConstraintViolationException;

@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("/api/v1/aplicante") 
public class AplicanteController { 
	
	@Autowired
	private IAplicante aplicanteService; 
	
	@GetMapping("")
	public ResponseEntity<APIResponse<List<Aplicante>>> buscarTodosLosAplicantes(){
		List<Aplicante> aplicantes = aplicanteService.buscarTodos(); 
		return aplicantes.isEmpty() ? ResponseUtil.notFound("No hay aplicantes") // Cambio en el mensaje
				: ResponseUtil.success(aplicantes);
	}
	
    @GetMapping("{id}")
    public ResponseEntity<APIResponse<Aplicante>> buscarAplicantePorId(@PathVariable("id") Integer id){ // Cambio en el nombre del método y del parámetro
    	Aplicante aplicante = aplicanteService.buscarPorId(id); // Cambio en el nombre de la variable y del método del servicio
    	return aplicante == null ? ResponseUtil.notFound("No se encontro el aplicante con el identificador proporcionado") // Cambio en el mensaje
    			: ResponseUtil.success(aplicante);
    }
    
    @GetMapping("/dni/{dni}")
    public ResponseEntity<APIResponse<Aplicante>> buscarAplicantePorDNI(@PathVariable("dni") Integer dni) {
        Aplicante aplicante = aplicanteService.buscarPorDNI(dni);
        return aplicante == null ? ResponseUtil.notFound("No se encontró el aplicante con el DNI proporcionado")
                : ResponseUtil.success(aplicante);
    }
	
	@PostMapping("")
	public ResponseEntity<APIResponse<Aplicante>> crearAplicante(@RequestBody Aplicante aplicante){ // Cambio en el nombre del método y del parámetro
		return aplicanteService.existe(aplicante.getId()) ? ResponseUtil.badRequest("No se pudo crear el aplicante, el Id ingresado ya existe") // Cambio en el mensaje
				: ResponseUtil.created(aplicanteService.guardar(aplicante));
	}
	
	@PutMapping("")
	 public ResponseEntity<APIResponse<Aplicante>> modificarAplicante(@RequestBody Aplicante aplicante){ // Cambio en el nombre del método y del parámetro
		return aplicanteService.existe(aplicante.getId()) ? ResponseUtil.success(aplicanteService.guardar(aplicante)) // Cambio en el mensaje
				: ResponseUtil.badRequest("No se pudo actualizar el aplicante, el id ingresado no ha sido creado");
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<APIResponse<Aplicante>> eliminarAplicante(@PathVariable("id") Integer id){ // Cambio en el nombre del método y del parámetro
	   return aplicanteService.existe(id) ? ResponseUtil.success(null)
			   : ResponseUtil.badRequest("No existe el aplicante con el id ingresado"); // Cambio en el mensaje
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<APIResponse<Aplicante>> handleException(Exception ex) {
		return ResponseUtil.badRequest(ex.getMessage());
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<APIResponse<Aplicante>> handleConstraintViolationException(ConstraintViolationException ex) {
		return ResponseUtil.handleConstraintException(ex);
	}

}
