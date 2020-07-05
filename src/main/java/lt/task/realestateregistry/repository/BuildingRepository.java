package lt.task.realestateregistry.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lt.task.realestateregistry.model.Building;

@Repository
public interface BuildingRepository extends JpaRepository<Building, Long> {
	List<Building> findByOwnerId(Long id);
}
