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

import imb.lh.puntajes.entity.GrillaTabulacion;
import imb.lh.puntajes.service.IGrillaTabulacion;
import jakarta.validation.ConstraintViolationException;

@RestController
@RequestMapping("/api/v1/grillatabulacion") 
public class GrillaTabulacionController { 
	
	@Autowired
	private IGrillaTabulacion grillatabulacionService; 
	
	@GetMapping("")
	public ResponseEntity<APIResponse<List<GrillaTabulacion>>> buscarTodasLasGrillaTabulaciones(){
		List<GrillaTabulacion> grillatabulaciones = grillatabulacionService.buscarTodos(); 
		return grillatabulaciones.isEmpty() ? ResponseUtil.notFound("No hay grilla tabulaciones")
				: ResponseUtil.success(grillatabulaciones);
	}
	
    @GetMapping("{id}")
    public ResponseEntity<APIResponse<GrillaTabulacion>> buscarGrillaTabulacionPorId(@PathVariable("id") Integer id){
    	GrillaTabulacion grillatabulacion = grillatabulacionService.buscarPorId(id);
    	return grillatabulacion == null ? ResponseUtil.notFound("No se encontró la grilla tabulación con el identificador proporcionado")
    			: ResponseUtil.success(grillatabulacion);
    }
	
	@PostMapping("")
	public ResponseEntity<APIResponse<GrillaTabulacion>> crearGrillaTabulacion(@RequestBody GrillaTabulacion grillatabulacion){
		return grillatabulacionService.existe(grillatabulacion.getId()) ? ResponseUtil.badRequest("No se pudo crear la grilla tabulación, el ID ingresado ya existe")
				: ResponseUtil.created(grillatabulacionService.guardar(grillatabulacion));
	}
	
	@PutMapping("")
	 public ResponseEntity<APIResponse<GrillaTabulacion>> modificarGrillaTabulacion(@RequestBody GrillaTabulacion grillatabulacion){
		return grillatabulacionService.existe(grillatabulacion.getId()) ? ResponseUtil.success(grillatabulacionService.guardar(grillatabulacion))
				: ResponseUtil.badRequest("No se pudo actualizar la grilla tabulación, el ID ingresado no existe");
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<APIResponse<GrillaTabulacion>> eliminarGrillaTabulacion(@PathVariable("id") Integer id){
	   return grillatabulacionService.existe(id) ? ResponseUtil.success(null)
			   : ResponseUtil.badRequest("No existe la grilla tabulación con el ID ingresado");
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<APIResponse<GrillaTabulacion>> handleException(Exception ex) {
		return ResponseUtil.badRequest(ex.getMessage());
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<APIResponse<GrillaTabulacion>> handleConstraintViolationException(ConstraintViolationException ex) {
		return ResponseUtil.handleConstraintException(ex);
	}

}
