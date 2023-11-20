package imb.lh.puntajes.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import imb.lh.puntajes.entity.Item;
import imb.lh.puntajes.repository.ItemRepository;
import imb.lh.puntajes.service.IItem;

@Service
public class ItemService implements IItem {
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Override
	public List<Item> buscarTodos() {
		return itemRepository.findAll();
	}

	@Override
	public Item buscarPorId(Integer id) {
		 Optional<Item> optional = itemRepository.findById(id);
		 return optional.orElse(null);
    }
	
	@Override
	public Item guardar(Item item) {
		 return itemRepository.save(item);
	}

	@Override
	public void eliminar(Integer id) {
		 itemRepository.deleteById(id);
	}
	
	@Override
	public boolean existe(Integer id) {
		 return id != null && itemRepository.existsById(id);
	}

}
