package dao;

import java.util.ArrayList;


import java.util.List;

import entity.Adress;
import service.connection.JpaLink;

public class AddressDao implements DaoInterface<Adress> {

	List<Adress> addressList = new ArrayList<>();

	/**
	 * Constructor
	 * 
	 * @param addressList
	 */
	public AddressDao() {
		this.addressList = findAll();
	}

	public List<Adress> findAll() {
		return JpaLink.getEntityManager().createQuery("SELECT a FROM Adress a JOIN FETCH a.country", Adress.class)
				.getResultList();
	}

	public Adress findByName(Adress address) {

		return addressList.stream()
				.filter(l -> l.getCity().equals(address.getCity()))
				.filter(l -> l.getEtat().equals(address.getEtat()))
				.findFirst().orElse(null);
	}

	public Adress existOrAdd(Adress address) {
		Adress addressFound = findByName(address);
		if (addressFound == null) {
			addressFound = new Adress(address.getStreet(), address.getCity(),address.getEtat(),address.getCountry());
				insert(addressFound);
		}
		return addressFound;
	}

	@Override
	public void insert(Adress adress) {
		JpaLink.persist(adress);
		addressList.add(adress);
	}

	@Override
	public void delete(Adress entity) {

	}

}
