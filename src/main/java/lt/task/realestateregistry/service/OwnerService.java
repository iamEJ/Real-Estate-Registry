package lt.task.realestateregistry.service;

import java.util.List;
import java.util.Optional;

import lt.task.realestateregistry.model.Owner;

public interface OwnerService {
	
	Optional<Owner> findById(Long id);
	
	List<Owner> findAll();
	
	Owner create(Owner owner);
	
	void delete(Long id);
	
	Owner update(Long id, Owner owner);
	
	double getRealEstateTax(Long id);
}
