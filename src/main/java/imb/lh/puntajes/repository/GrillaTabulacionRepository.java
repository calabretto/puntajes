package imb.lh.puntajes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import imb.lh.puntajes.entity.GrillaTabulacion;

public interface GrillaTabulacionRepository extends JpaRepository<GrillaTabulacion, Integer> {

	@Query("SELECT g FROM GrillaTabulacion g WHERE g.aplicante.id = :aplicante AND g.materia.id = :materia AND g.periodo.id = :periodo")
	List<GrillaTabulacion> buscarPorIds(@Param("aplicante") Integer aplicante, 
	                                    @Param("materia") Integer materia, 
	                                    @Param("periodo") Integer periodo);

	@Query("SELECT g FROM GrillaTabulacion g WHERE g.materia.id = :materia AND g.periodo.id = :periodo")
    List<GrillaTabulacion> buscarPorMateriaYPeriodo(@Param("materia") Integer materia, @Param("periodo") Integer periodo);
}
