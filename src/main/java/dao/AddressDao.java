package dao;

import java.util.ArrayList;

import java.util.List;

import entity.Adress;
import service.connection.JpaLink;

public class AddressDao implements DaoInterface<Adress> {

	List<Adress> lieuList = new ArrayList<>();

	/**
	 * Constructor
	 * 
	 * @param lieuList
	 */
	public AddressDao() {
		this.lieuList = findAll();
	}

	public List<Adress> findAll() {
		return JpaLink.getEntityManager().createQuery("SELECT a FROM Adress a JOIN FETCH a.country", Adress.class)
				.getResultList();
	}

	public Adress findByName(Adress address) {

		return lieuList.stream()
				.filter(l -> l.getCity() != null && address.getCity() != null && l.getCity().equals(address.getCity()))
				.filter(l -> l.getEtat() != null && address.getEtat() != null && l.getEtat().equals(address.getEtat()))
				.findFirst().orElse(null);
	}

	public Adress existOrAdd(Adress address) {
		if (address != null) {
			Adress existingAdress = findByName(address);

			if (existingAdress == null) {
				existingAdress = new Adress();
				if (!address.getStreet().isEmpty()) {
					existingAdress.setStreet(address.getStreet());
				}
				if (address.getCity() != null) {
					existingAdress.setCity(address.getCity());
				}
				if (address.getEtat() != null) {
					existingAdress.setEtat(address.getEtat());
				}
				existingAdress.setCountry(address.getCountry());

				insert(existingAdress);
			}
			return existingAdress;
		}
		return null;

	}

	@Override
	public void insert(Adress adress) {
		JpaLink.persist(adress);
		lieuList.add(adress);
	}

	@Override
	public void delete(Adress entity) {

	}

//	public boolean lieuExist(Adress address) {
//		return lieuList.stream()
//				.anyMatch(l -> l.getStreet() != null && l.getStreet().equals(address.getStreet()) && l.getCity() != null
//						&& l.getCity().equals(address.getCity()) && l.getEtat() != null
//						&& l.getEtat().equals(address.getEtat()));
//	}
}
