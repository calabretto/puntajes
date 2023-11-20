package imb.lh.puntajes.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import imb.lh.puntajes.entity.GrillaTabulacion;
import imb.lh.puntajes.repository.GrillaTabulacionRepository;
import imb.lh.puntajes.service.IGrillaTabulacion;

@Service
public class GrillaTabulacionService implements IGrillaTabulacion {
	
	@Autowired
	private GrillaTabulacionRepository grillatabulacionRepository;
	
	@Override
	public List<GrillaTabulacion> buscarTodos() {
		return grillatabulacionRepository.findAll();
	}

	@Override
	public GrillaTabulacion buscarPorId(Integer id) {
		 Optional<GrillaTabulacion> optional = grillatabulacionRepository.findById(id);
		 return optional.orElse(null);
    }
	
	@Override
	public GrillaTabulacion guardar(GrillaTabulacion grillatabulacion) {
		 return grillatabulacionRepository.save(grillatabulacion);
	}

	@Override
	public void eliminar(Integer id) {
		 grillatabulacionRepository.deleteById(id);
	}
	
	@Override
	public boolean existe(Integer id) {
		 return id != null && grillatabulacionRepository.existsById(id);
	}

}
