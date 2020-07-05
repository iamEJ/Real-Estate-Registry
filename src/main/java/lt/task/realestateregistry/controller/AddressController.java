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

import lt.task.realestateregistry.model.Address;
import lt.task.realestateregistry.service.AddressService;

@RestController
@RequestMapping(path = "/api/v1/addresses")
public class AddressController {

	@Autowired
	private AddressService addressService;

	// Get all address
	@GetMapping()
	@ResponseStatus(HttpStatus.OK)
	public List<Address> getAll() {
		return addressService.findAll();
	}

	// Get address by id
	@GetMapping("/{addressId}")
	@ResponseStatus(HttpStatus.OK)
	public Optional<Address> getbyId(@PathVariable Long addressId) {
		return addressService.findById(addressId);
	}

	// Create address
	@PostMapping()
	@ResponseStatus(HttpStatus.CREATED)
	public Address create(@Valid @RequestBody Address address) {
		return addressService.create(address);
	}

	// Delete address
	@DeleteMapping("/{addressId}")
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable Long addressId) {
		addressService.delete(addressId);
	}

	// Update address
	@PutMapping("/{addressId}")
	@ResponseStatus(HttpStatus.OK)
	public Address update(@Valid @RequestBody Address address, @PathVariable Long addressId) {
		return addressService.update(addressId, address);
	}
}
