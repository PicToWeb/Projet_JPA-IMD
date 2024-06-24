package dao;

import java.util.ArrayList;


import java.util.List;

import entity.Address;
import service.connection.JpaLink;

/**
 * Data Access Object (DAO) for managing adress.
 * Implements the DaoInterface for Address objects.
 */
public class AddressDao implements DaoInterface<Address> {

	/** addressList to store address data */
	List<Address> addressList = new ArrayList<>();

	
	/**
     * Constructor
     *
     * @param addressList The list of addresses
     */
	public AddressDao() {
		this.addressList = findAll();
	}

	 /**
     * Retrieves all addresses from the database.
     *
     * @return A list of addresses
     */
	public List<Address> findAll() {
		return JpaLink.getEntityManager().createQuery("SELECT a FROM Address a JOIN FETCH a.country", Address.class)
				.getResultList();
	}

	/**
     * Finds an address by its city and state.
     *
     * @param address The address to search for
     * @return The matching address or null if not found
     */
	public Address findByName(Address address) {

		return addressList.stream()
				.filter(l -> l.getCity().equals(address.getCity()))
				.filter(l -> l.getEtat().equals(address.getEtat()))
				.findFirst().orElse(null);
	}

	/**
     * Checks if an address exists in the list, and adds it if not.
     *
     * @param address The address to check/add
     * @return The existing or newly added address
     */
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


}
