package imb.lh.puntajes.repository;
import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

import imb.lh.puntajes.entity.Aplicante;

public interface AplicanteRepository extends JpaRepository<Aplicante, Integer> {

}