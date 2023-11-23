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

import imb.lh.puntajes.entity.Encabezado;
import imb.lh.puntajes.service.IEncabezado;
import jakarta.validation.ConstraintViolationException;
@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("/api/v1/encabezado") 
public class EncabezadoController { 
	
	@Autowired
	private IEncabezado encabezadoService; 
	
	@GetMapping("")
	public ResponseEntity<APIResponse<List<Encabezado>>> buscarTodosLosEncabezados(){
		List<Encabezado> encabezados = encabezadoService.buscarTodos(); 
		return encabezados.isEmpty() ? ResponseUtil.notFound("No hay encabezados")
				: ResponseUtil.success(encabezados);
	}
	
    @GetMapping("{id}")
    public ResponseEntity<APIResponse<Encabezado>> buscarEncabezadoPorId(@PathVariable("id") Integer id){
    	Encabezado encabezado = encabezadoService.buscarPorId(id);
    	return encabezado == null ? ResponseUtil.notFound("No se encontró el encabezado con el identificador proporcionado")
    			: ResponseUtil.success(encabezado);
    }
	
	@PostMapping("")
	public ResponseEntity<APIResponse<Encabezado>> crearEncabezado(@RequestBody Encabezado encabezado){
		return encabezadoService.existe(encabezado.getId()) ? ResponseUtil.badRequest("No se pudo crear el encabezado, el ID ingresado ya existe")
				: ResponseUtil.created(encabezadoService.guardar(encabezado));
	}
	
	@PutMapping("")
	 public ResponseEntity<APIResponse<Encabezado>> modificarEncabezado(@RequestBody Encabezado encabezado){
		return encabezadoService.existe(encabezado.getId()) ? ResponseUtil.success(encabezadoService.guardar(encabezado))
				: ResponseUtil.badRequest("No se pudo actualizar el encabezado, el ID ingresado no existe");
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<APIResponse<Encabezado>> eliminarEncabezado(@PathVariable("id") Integer id){
	   return encabezadoService.existe(id) ? ResponseUtil.success(null)
			   : ResponseUtil.badRequest("No existe el encabezado con el ID ingresado");
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<APIResponse<Encabezado>> handleException(Exception ex) {
		return ResponseUtil.badRequest(ex.getMessage());
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<APIResponse<Encabezado>> handleConstraintViolationException(ConstraintViolationException ex) {
		return ResponseUtil.handleConstraintException(ex);
	}

}
