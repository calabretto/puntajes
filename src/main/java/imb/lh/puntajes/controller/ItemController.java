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

import imb.lh.puntajes.entity.Item;
import imb.lh.puntajes.service.IItem;
import jakarta.validation.ConstraintViolationException;

@RestController
@RequestMapping("/api/v1/item") 
public class ItemController { 
	
	@Autowired
	private IItem itemService; 
	
	@GetMapping("")
	public ResponseEntity<APIResponse<List<Item>>> buscarTodosLosItems(){
		List<Item> items = itemService.buscarTodos(); 
		return items.isEmpty() ? ResponseUtil.notFound("No hay items")
				: ResponseUtil.success(items);
	}
	
    @GetMapping("{id}")
    public ResponseEntity<APIResponse<Item>> buscarItemPorId(@PathVariable("id") Integer id){
    	Item item = itemService.buscarPorId(id);
    	return item == null ? ResponseUtil.notFound("No se encontró el item con el identificador proporcionado")
    			: ResponseUtil.success(item);
    }
	
	@PostMapping("")
	public ResponseEntity<APIResponse<Item>> crearItem(@RequestBody Item item){
		return itemService.existe(item.getId()) ? ResponseUtil.badRequest("No se pudo crear el item, el ID ingresado ya existe")
				: ResponseUtil.created(itemService.guardar(item));
	}
	
	@PutMapping("")
	 public ResponseEntity<APIResponse<Item>> modificarItem(@RequestBody Item item){
		return itemService.existe(item.getId()) ? ResponseUtil.success(itemService.guardar(item))
				: ResponseUtil.badRequest("No se pudo actualizar el item, el ID ingresado no existe");
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<APIResponse<Item>> eliminarItem(@PathVariable("id") Integer id){
	   return itemService.existe(id) ? ResponseUtil.success(null)
			   : ResponseUtil.badRequest("No existe el item con el ID ingresado");
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<APIResponse<Item>> handleException(Exception ex) {
		return ResponseUtil.badRequest(ex.getMessage());
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<APIResponse<Item>> handleConstraintViolationException(ConstraintViolationException ex) {
		return ResponseUtil.handleConstraintException(ex);
	}

}
