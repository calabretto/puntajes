package imb.lh.puntajes.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import imb.lh.puntajes.entity.Aplicante;
import imb.lh.puntajes.repository.AplicanteRepository;
import imb.lh.puntajes.service.IAplicante;

@Service
public class AplicanteService implements IAplicante {
	
	@Autowired
	private AplicanteRepository aplicanteRepository;
	
	@Override
	public List<Aplicante> buscarTodos() {
		return aplicanteRepository.findAll();
	}

	@Override
	public Aplicante buscarPorId(Integer id) {
		 Optional<Aplicante> optional = aplicanteRepository.findById(id);
		 return optional.orElse(null);
    }
	
	@Override
	public Aplicante guardar(Aplicante aplicante) {
		 return aplicanteRepository.save(aplicante);
	}

	@Override
	public void eliminar(Integer id) {
		 aplicanteRepository.deleteById(id);
	}
	
	@Override
	public boolean existe(Integer id) {
		 return id != null && aplicanteRepository.existsById(id);
	}

	@Override
	public Aplicante buscarPorDNI(Integer dni) {
		// TODO Auto-generated method stub
		return null;
	}

}
