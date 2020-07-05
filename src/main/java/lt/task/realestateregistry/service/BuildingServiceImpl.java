package lt.task.realestateregistry.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lt.task.realestateregistry.model.Building;
import lt.task.realestateregistry.model.Owner;
import lt.task.realestateregistry.repository.BuildingRepository;
import lt.task.realestateregistry.repository.OwnerRepository;

@Service
public class BuildingServiceImpl implements BuildingService {

	@Autowired
	private BuildingRepository buildingRepository;
	@Autowired
	private OwnerRepository ownerRepository;

	@Override
	public Optional<Building> findById(Long id) {
		return buildingRepository.findById(id);
	}

	@Override
	public List<Building> findAll() {
		return buildingRepository.findAll();
	}

	@Override
	public Building create(Building building) {
		return buildingRepository.save(building);
	}

	@Override
	public void deleteById(Long id) {
		buildingRepository.deleteById(id);

	}

	@Override
	public Building update(Long id, Building building) {
		if (buildingRepository.findById(id).isPresent()) {
			Building newBuilding = buildingRepository.findById(id).get();
			newBuilding.setSize(building.getSize());
			newBuilding.setMarketValue(building.getMarketValue());
			newBuilding.setPropertyType(building.getPropertyType());
			newBuilding.setAddress(building.getAddress());

			return buildingRepository.save(newBuilding);
		} else {
			throw new IllegalStateException("There is no such building.");
		}
	}

	@Override
	public List<Building> findByOwnerId(Long id) {
		return buildingRepository.findByOwnerId(id);
	}

	@Override
	public void createBuildingAndAssignToOwner(Building building, Long id) {
		Optional<Owner> o = ownerRepository.findById(id);
		Owner owner = o.get();
		owner.addBuilding(building);
		ownerRepository.save(owner);
	}

}
