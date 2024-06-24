package dao;

import java.util.ArrayList;
import java.util.List;
import entity.Country;
import service.connection.JpaLink;

/**
 * Data Access Object (DAO) for managing countries.
 * Implements the DaoInterface for Country objects.
 */
public class CountryDao implements DaoInterface<Country> {

	/** countryList to store country data*/
	List<Country> countryList = new ArrayList<>();

	
	/**
     * Constructor initializes the country list by calling findAll().
     */
	public CountryDao() {
		this.countryList = findAll();
	}

	/**
     * Retrieves all countries from the database.
     *
     * @return List of countries.
     */
	public List<Country> findAll() {
		return JpaLink.getEntityManager().createQuery("SELECT c FROM Country c", Country.class).getResultList();
	}

	/**
     * Checks if a country with the given name exists.
     *
     * @param pays Name of the country.
     * @return True if country exists, false otherwise.
     */
	public boolean countryExist(String pays) {
		return countryList.stream().anyMatch(p -> p.getName().equals(pays));
	}

	/**
     * Finds a country by its name.
     *
     * @param pays Name of the country.
     * @return Country object or null if not found.
     */
	public Country findByName(String pays) {
		return countryList.stream().filter(p -> p.getName().equals(pays)).findFirst().orElse(null);
	}

	/**
     * Checks if a country exists, and adds it if not.
     *
     * @param country Country object to check/add.
     * @return Existing or newly added country.
     */
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


}
