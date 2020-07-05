package lt.task.realestateregistry.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lt.task.realestateregistry.model.Address;
import lt.task.realestateregistry.repository.AddressRepository;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	private AddressRepository addressRepository;

	@Override
	public List<Address> findAll() {
		return addressRepository.findAll();
	}

	@Override
	public Optional<Address> findById(Long id) {
		return addressRepository.findById(id);
	}

	@Override
	public Address create(Address address) {
		return addressRepository.save(address);
	}

	@Override
	public void delete(Long id) {
		addressRepository.deleteById(id);
	}

	@Override
	public Address update(Long id, Address address) {
		if (addressRepository.findById(id).isPresent()) {
			Address newAddress = addressRepository.findById(id).get();
			newAddress.setCity(address.getCity());
			newAddress.setStreet(address.getCity());
			newAddress.setHouseNumber(address.getHouseNumber());
			return addressRepository.save(newAddress);
		} else {
			throw new IllegalStateException("There is no such address.");
		}
	}

}
