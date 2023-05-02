package edu.tus.rm.repository;

import edu.tus.rm.entity.MenuItem;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;


@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {

}
