package imb.lh.puntajes.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import imb.lh.puntajes.entity.Carrera;
import imb.lh.puntajes.repository.CarreraRepository;
import imb.lh.puntajes.service.ICarrera;

@Service
public class CarreraService implements ICarrera {
	
	@Autowired
	private CarreraRepository carreraRepository;
	
	@Override
	public List<Carrera> buscarTodos() {
		return carreraRepository.findAll();
	}

	@Override
	public Carrera buscarPorId(Integer id) {
		 Optional<Carrera> optional = carreraRepository.findById(id);
		 return optional.orElse(null);
    }
	
	@Override
	public Carrera guardar(Carrera carrera) {
		 return carreraRepository.save(carrera);
	}

	@Override
	public void eliminar(Integer id) {
		 carreraRepository.deleteById(id);
	}
	
	@Override
	public boolean existe(Integer id) {
		 return id != null && carreraRepository.existsById(id);
	}

}