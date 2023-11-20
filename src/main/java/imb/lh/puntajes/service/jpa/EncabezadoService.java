package imb.lh.puntajes.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import imb.lh.puntajes.entity.Encabezado;
import imb.lh.puntajes.repository.EncabezadoRepository;
import imb.lh.puntajes.service.IEncabezado;

@Service
public class EncabezadoService implements IEncabezado {
	
	@Autowired
	private EncabezadoRepository encabezadoRepository;
	
	@Override
	public List<Encabezado> buscarTodos() {
		return encabezadoRepository.findAll();
	}

	@Override
	public Encabezado buscarPorId(Integer id) {
		 Optional<Encabezado> optional = encabezadoRepository.findById(id);
		 return optional.orElse(null);
    }
	
	@Override
	public Encabezado guardar(Encabezado alquiler) {
		 return encabezadoRepository.save(alquiler);
	}

	@Override
	public void eliminar(Integer id) {
		 encabezadoRepository.deleteById(id);
	}
	
	@Override
	public boolean existe(Integer id) {
		 return id != null && encabezadoRepository.existsById(id);
	}

}
