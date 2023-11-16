package imb.lh.puntajes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import imb.lh.puntajes.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Integer> {

}
