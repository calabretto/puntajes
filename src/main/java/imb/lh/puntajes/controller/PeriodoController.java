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

import imb.lh.puntajes.entity.Periodo;
import imb.lh.puntajes.service.IPeriodo;
import jakarta.validation.ConstraintViolationException;

@RestController
@RequestMapping("/api/v1/periodo") 
public class PeriodoController { 
	
	@Autowired
	private IPeriodo periodoService; 
	
	@GetMapping("")
	public ResponseEntity<APIResponse<List<Periodo>>> buscarTodosLosPeriodos(){
		List<Periodo> periodos = periodoService.buscarTodos(); 
		return periodos.isEmpty() ? ResponseUtil.notFound("No hay periodos")
				: ResponseUtil.success(periodos);
	}
	
    @GetMapping("{id}")
    public ResponseEntity<APIResponse<Periodo>> buscarPeriodoPorId(@PathVariable("id") Integer id){
    	Periodo periodo = periodoService.buscarPorId(id);
    	return periodo == null ? ResponseUtil.notFound("No se encontró el periodo con el identificador proporcionado")
    			: ResponseUtil.success(periodo);
    }
	
	@PostMapping("")
	public ResponseEntity<APIResponse<Periodo>> crearPeriodo(@RequestBody Periodo periodo){
		return periodoService.existe(periodo.getId()) ? ResponseUtil.badRequest("No se pudo crear el periodo, el ID ingresado ya existe")
				: ResponseUtil.created(periodoService.guardar(periodo));
	}
	
	@PutMapping("")
	 public ResponseEntity<APIResponse<Periodo>> modificarPeriodo(@RequestBody Periodo periodo){
		return periodoService.existe(periodo.getId()) ? ResponseUtil.success(periodoService.guardar(periodo))
				: ResponseUtil.badRequest("No se pudo actualizar el periodo, el ID ingresado no existe");
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<APIResponse<Periodo>> eliminarPeriodo(@PathVariable("id") Integer id){
	   return periodoService.existe(id) ? ResponseUtil.success(null)
			   : ResponseUtil.badRequest("No existe el periodo con el ID ingresado");
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<APIResponse<Periodo>> handleException(Exception ex) {
		return ResponseUtil.badRequest(ex.getMessage());
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<APIResponse<Periodo>> handleConstraintViolationException(ConstraintViolationException ex) {
		return ResponseUtil.handleConstraintException(ex);
	}

}
