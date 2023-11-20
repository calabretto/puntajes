package imb.lh.puntajes.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import imb.lh.puntajes.entity.Periodo;
import imb.lh.puntajes.repository.PeriodoRepository;
import imb.lh.puntajes.service.IPeriodo;

@Service
public class PeriodoService implements IPeriodo {
	
	@Autowired
	private PeriodoRepository periodoRepository;
	
	@Override
	public List<Periodo> buscarTodos() {
		return periodoRepository.findAll();
	}

	@Override
	public Periodo buscarPorId(Integer id) {
		 Optional<Periodo> optional = periodoRepository.findById(id);
		 return optional.orElse(null);
    }
	
	@Override
	public Periodo guardar(Periodo periodo) {
		 return periodoRepository.save(periodo);
	}

	@Override
	public void eliminar(Integer id) {
		 periodoRepository.deleteById(id);
	}
	
	@Override
	public boolean existe(Integer id) {
		 return id != null && periodoRepository.existsById(id);
	}

}
