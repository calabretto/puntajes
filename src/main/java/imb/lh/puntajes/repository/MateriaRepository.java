package imb.lh.puntajes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import imb.lh.puntajes.entity.Materia;
import java.util.List;

public interface MateriaRepository extends JpaRepository<Materia, Integer> {
	List<Materia> findByCarreraId(Integer carreraId);
}
