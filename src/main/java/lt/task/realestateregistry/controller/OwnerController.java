package lt.task.realestateregistry.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lt.task.realestateregistry.model.Building;
import lt.task.realestateregistry.model.Owner;
import lt.task.realestateregistry.service.BuildingService;
import lt.task.realestateregistry.service.OwnerService;

@RestController
@RequestMapping(path = "/api/v1/owners")
public class OwnerController {

	@Autowired
	private OwnerService ownerService;
	@Autowired
	private BuildingService buildingService;

	// Get owner by id
	@GetMapping("/{ownerId}")
	@ResponseStatus(HttpStatus.OK)
	public Optional<Owner> getById(@PathVariable Long ownerId) {
		return ownerService.findById(ownerId);
	}

	// Get all owners
	@GetMapping()
	@ResponseStatus(HttpStatus.OK)
	public List<Owner> getAll() {
		return ownerService.findAll();
	}

	// Create owner
	@PostMapping()
	@ResponseStatus(HttpStatus.CREATED)
	public Owner create(@RequestBody @Valid Owner owner) {
		return ownerService.create(owner);
	}

	// Delete owner
	@DeleteMapping("/{ownerId}")
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable Long ownerId) {
		ownerService.delete(ownerId);
	}

	// Update owner
	@PutMapping("/{ownerId}")
	@ResponseStatus(HttpStatus.OK)
	public Owner update(@PathVariable Long ownerId, @Valid @RequestBody Owner owner) {
		return ownerService.update(ownerId, owner);
	}

	/// Get all buildings by owner id
	@GetMapping("/{ownerId}/buildings")
	@ResponseStatus(HttpStatus.OK)
	public List<Building> findAllByOwnerId(@Valid @PathVariable Long ownerId) {
		return buildingService.findByOwnerId(ownerId);
	}

	/// Get one building by owner id
	@GetMapping("/{ownerId}/buildings/{buildingId}")
	@ResponseStatus(HttpStatus.OK)
	public Optional<Building> findBuildingByOwnerId(@Valid @PathVariable Long buildingId) {
		return buildingService.findById(buildingId);
	}

	// Create and assign building to the owner
	@PostMapping("/{id}/buildings")
	public void addBuildingToOwner(@PathVariable Long id, @RequestBody @Valid Building building) {
		buildingService.createBuildingAndAssignToOwner(building, id);
	}

	/// Update owner's building
	@PutMapping("/{ownerId}/buildings/{buildingId}")
	@ResponseStatus(HttpStatus.OK)
	public void updateOwnersBuilding(@Valid @RequestBody Building building, @PathVariable Long buildingId) {
		buildingService.update(buildingId, building);
	}

	// Get owner's real estate tax
	@GetMapping("/{ownerId}/tax")
	@ResponseStatus(HttpStatus.OK)
	public double getRealEstateTaxByOwnerId(@PathVariable Long ownerId) {
		return ownerService.getRealEstateTax(ownerId);
	}
}
