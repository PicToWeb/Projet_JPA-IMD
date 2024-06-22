package dao;

import java.util.ArrayList;
import java.util.List;
import entity.Country;
import service.connection.JpaLink;

/**
 * 
 */
public class CountryDao implements DaoInterface<Country> {

	List<Country> countryList = new ArrayList<>();

	
	/**
	 * Constructor
	 * 
	 * @param lieuMap
	 */
	public CountryDao() {
		this.countryList = findAll();
	}

	public List<Country> findAll() {
		return JpaLink.getEntityManager().createQuery("SELECT c FROM Country c", Country.class).getResultList();
	}

	public boolean countryExist(String pays) {
		return countryList.stream().anyMatch(p -> p.getName().equals(pays));
	}

	public Country findByName(String pays) {
		return countryList.stream().filter(p -> p.getName().equals(pays)).findFirst().orElse(null);
	}

	public Country existOrAdd(Country country) {
		Country countryFound = findByName(country.getName());
		if (countryFound == null) {
			countryFound = new Country(country.getName(), "");
			insert(countryFound);
		}
		return countryFound;
	}

	@Override
	public void insert(Country country) {
		JpaLink.persist(country);
		countryList.add(country);
	}

	@Override
	public void delete(Country entity) {

	}

}
