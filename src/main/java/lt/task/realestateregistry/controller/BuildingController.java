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
import lt.task.realestateregistry.service.BuildingService;

@RestController
@RequestMapping(path = "/api/v1/buildings")
public class BuildingController {

	@Autowired
	private BuildingService buildingService;

	// Get all buildings
	@GetMapping()
	@ResponseStatus(HttpStatus.OK)
	public List<Building> getAll() {
		return buildingService.findAll();
	}

	// Get building by id
	@GetMapping("/{buildingId}")
	@ResponseStatus(HttpStatus.OK)
	public Optional<Building> getById(@PathVariable Long buildingId) {
		return buildingService.findById(buildingId);
	}

	// Create building
	@PostMapping()
	@ResponseStatus(HttpStatus.CREATED)
	public Building create(@RequestBody @Valid Building building) {
		return buildingService.create(building);
	}

	// Delete building
	@DeleteMapping("/{buildingId}")
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable Long buildingId) {
		buildingService.deleteById(buildingId);
	}

	// Update building
	@PutMapping("/{buildingId}")
	@ResponseStatus(HttpStatus.OK)
	public Building update(@RequestBody @Valid Building building, @PathVariable Long buildingId) {
		return buildingService.update(buildingId, building);
	}
}
