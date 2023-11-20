package imb.lh.puntajes.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import imb.lh.puntajes.entity.Materia;
import imb.lh.puntajes.repository.MateriaRepository;
import imb.lh.puntajes.service.IMateria;

@Service
public class MateriaService implements IMateria {
	
	@Autowired
	private MateriaRepository materiaRepository;
	
	@Override
	public List<Materia> buscarTodos() {
		return materiaRepository.findAll();
	}

	@Override
	public Materia buscarPorId(Integer id) {
		 Optional<Materia> optional = materiaRepository.findById(id);
		 return optional.orElse(null);
    }
	
	@Override
	public Materia guardar(Materia materia) {
		 return materiaRepository.save(materia);
	}

	@Override
	public void eliminar(Integer id) {
		 materiaRepository.deleteById(id);
	}
	
	@Override
	public boolean existe(Integer id) {
		 return id != null && materiaRepository.existsById(id);
	}
	 @Override
	    public List<Materia> buscarPorCarreraId(Integer carreraId) {
	        return materiaRepository.findByCarreraId(carreraId);
	    }

}
