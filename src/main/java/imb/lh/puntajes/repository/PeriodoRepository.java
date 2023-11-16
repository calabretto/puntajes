package imb.lh.puntajes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import imb.lh.puntajes.entity.Periodo;

public interface PeriodoRepository extends JpaRepository<Periodo, Integer> {

}
