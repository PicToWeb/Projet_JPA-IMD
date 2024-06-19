package dao;

import java.util.ArrayList;

import java.util.List;

import entity.Adress;
import service.connection.JpaLink;

public class AdressDao implements DaoInterface<Adress> {

	List<Adress> lieuList = new ArrayList<>();

	/**
	 * Constructor
	 * 
	 * @param lieuList
	 */
	public AdressDao() {
		this.lieuList = findAll();

	}

	public boolean lieuExist(Adress adress) {
		return lieuList.stream()
				.anyMatch(l -> l.getStreet() != null && l.getStreet().equals(adress.getStreet()) && l.getCity() != null
						&& l.getCity().equals(adress.getCity()) && l.getEtat() != null
						&& l.getEtat().equals(adress.getEtat()));
	}

	public List<Adress> findAll() {
		return JpaLink.getEntityManager().createQuery("SELECT a FROM Adress a JOIN FETCH a.country", Adress.class)
				.getResultList();
	}

	// ajout rue
	public Adress findByName(Adress adress) {
		return lieuList.stream()
				.filter(l -> l.getCity() != null && adress.getCity() != null && l.getCity().equals(adress.getCity()))
				.filter(l -> l.getEtat() != null && adress.getEtat() != null && l.getEtat().equals(adress.getEtat()))
				.findFirst().orElse(null);
	}

	public Adress lieuExistOrAdded(Adress adress) {
//		 Adress existingLieu = findByName(adress);
//		    if (existingLieu == null) {
//		        existingLieu = new Adress(adress.getStreet(), adress.getCity(), adress.getEtat(), adress.getCountry());
//		        insert(existingLieu);
//		    }
		if (adress != null) {

			Adress existingAdress = findByName(adress);

			if (existingAdress == null) {
				existingAdress = new Adress();
				if (!adress.getStreet().isEmpty()) {
					existingAdress.setStreet(adress.getStreet());
				}
				if (adress.getCity() != null) {
					existingAdress.setCity(adress.getCity());
				}
				if (adress.getEtat() != null) {
					existingAdress.setEtat(adress.getEtat());
				}
				existingAdress.setCountry(adress.getCountry());

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

}
