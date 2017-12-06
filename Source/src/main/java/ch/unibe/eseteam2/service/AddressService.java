package ch.unibe.eseteam2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.unibe.eseteam2.model.Address;
import ch.unibe.eseteam2.model.dao.AddressRepository;

@Service
public class AddressService {
	@Autowired
	private AddressRepository addressRepository;

	public void save(Address address) {
		this.addressRepository.save(address);
	}
}
