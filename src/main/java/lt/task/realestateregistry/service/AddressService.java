package lt.task.realestateregistry.service;

import java.util.List;
import java.util.Optional;

import lt.task.realestateregistry.model.Address;

public interface AddressService {

	List<Address> findAll();
	
	Optional<Address> findById(Long id);
	
	Address create(Address address);
	
	void delete(Long id);
	
	Address update(Long id, Address address);
}
