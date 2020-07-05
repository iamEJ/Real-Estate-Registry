package lt.task.realestateregistry.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lt.task.realestateregistry.model.Building;
import lt.task.realestateregistry.model.Owner;
import lt.task.realestateregistry.model.Type;
import lt.task.realestateregistry.repository.OwnerRepository;

@Service
public class OwnerServiceImpl implements OwnerService {

	@Autowired
	private OwnerRepository ownerRepository;

	@Override
	public Optional<Owner> findById(Long id) {
		return ownerRepository.findById(id);
	}

	@Override
	public List<Owner> findAll() {
		return ownerRepository.findAll();
	}

	@Override
	public Owner create(Owner owner) {
		return ownerRepository.save(owner);
	}

	@Override
	public void delete(Long id) {
		ownerRepository.deleteById(id);
	}

	@Override
	public Owner update(Long id, Owner owner) {
		if (ownerRepository.findById(id).isPresent()) {
			Owner newOwner = ownerRepository.findById(id).get();
			newOwner.setFirstName(owner.getFirstName());
			newOwner.setLastName(owner.getLastName());
			newOwner.setPersonalCode(owner.getPersonalCode());
			return ownerRepository.save(newOwner);
		} else {
			throw new IllegalStateException("There is no such person.");
		}
	}

	// A method to calculate the total yearly real estate tax for
	// all properties owned by a particular owner.
	@Override
	public double getRealEstateTax(Long id) {
		if (ownerRepository.findById(id).isPresent()) {
			Owner newOwner = ownerRepository.findById(id).get();
			double realEstateTax = 0;
			// Tax rate for a year
			double taxRateOfApartment = 0.025;
			double taxRateOfCommercial = 0.042;
			double taxRateOfHouse = 0.03;
			double taxRateOfIndustrial = 0.075;
			double taxRateOfCottage = 0.033;

			for (Building b : newOwner.getBuildings()) {
				Type type = b.getPropertyType();
				if (type == Type.APARTMENT) {

					realEstateTax += (b.getMarketValue() * taxRateOfApartment);
				}
				if (type == Type.COMMERCIAL) {
					realEstateTax += (b.getMarketValue() * taxRateOfCommercial);
				}
				if (type == Type.HOUSE) {
					realEstateTax += (b.getMarketValue() * taxRateOfHouse);
				}
				if (type == Type.INDUSTRIAL) {
					realEstateTax += (b.getMarketValue() * taxRateOfIndustrial);
				}
				if (type == Type.COTTAGE) {
					realEstateTax += (b.getMarketValue() * taxRateOfCottage);
				}
			}
			return realEstateTax;
		} else {
			throw new IllegalStateException("There are no builings to tax");
		}
	}

}
