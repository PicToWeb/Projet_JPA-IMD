package dao;

import java.util.ArrayList;


import java.util.List;

import entity.Address;
import service.connection.JpaLink;

public class AddressDao implements DaoInterface<Address> {

	List<Address> addressList = new ArrayList<>();

	
	/**
	 * Constructor
	 * 
	 * @param addressList
	 */
	public AddressDao() {
		this.addressList = findAll();
	}

	public List<Address> findAll() {
		return JpaLink.getEntityManager().createQuery("SELECT a FROM Address a JOIN FETCH a.country", Address.class)
				.getResultList();
	}

	public Address findByName(Address address) {

		return addressList.stream()
				.filter(l -> l.getCity().equals(address.getCity()))
				.filter(l -> l.getEtat().equals(address.getEtat()))
				.findFirst().orElse(null);
	}

	public Address existOrAdd(Address address) {
		Address addressFound = findByName(address);
		if (addressFound == null) {
			addressFound = new Address(address.getStreet(), address.getCity(),address.getEtat(),address.getCountry());
				insert(addressFound);
		}
		return addressFound;
	}

	@Override
	public void insert(Address address) {
		JpaLink.persist(address);
		addressList.add(address);
	}

	@Override
	public void delete(Address entity) {

	}

}
