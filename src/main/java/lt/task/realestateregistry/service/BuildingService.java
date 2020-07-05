package lt.task.realestateregistry.service;

import java.util.List;
import java.util.Optional;

import lt.task.realestateregistry.model.Building;

public interface BuildingService {

	Optional<Building> findById(Long id);
	
	List<Building> findAll();
	
	Building create(Building building);
	
	void deleteById(Long id);
	
	Building update(Long id, Building building);
	
	List<Building> findByOwnerId(Long id);

	void createBuildingAndAssignToOwner(Building building, Long id);

}
