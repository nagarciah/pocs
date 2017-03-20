package nagarciah.pocs.statemachine.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import nagarciah.pocs.statemachine.entity.Menu;

public interface MenuDAO extends JpaRepository<Menu, Integer> {

}
