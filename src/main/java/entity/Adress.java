package entity;

import java.util.Objects;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "ADRESS")
public class Adress {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "street", length = 150)
	private String street;

	@Column(name = "city", length = 200)
	private String city;

	@Column(name = "etat", length = 100)
	private String etat;

	@OneToMany(mappedBy = "adress")
	private Set<Person> persons;

	@OneToMany(mappedBy = "adress")
	private Set<Movie> movies;

	@ManyToOne
	@JoinColumn(name = "id_country", nullable = true)
	private Country country;

	/**
	 * Constructor
	 * 
	 * @param street
	 * @param ville
	 * @param etat
	 * @param country
	 */
	public Adress(String street, String ville, String etat, Country country) {
		this.street = street;
		this.city = ville;
		this.etat = etat;
		this.country = country;
	}

	public Adress() {
		
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Adress adress = (Adress) o;
		return Objects.equals(city, adress.city) && Objects.equals(etat, adress.etat)
				&& Objects.equals(country, adress.country);
	}

	@Override
	public int hashCode() {
		return Objects.hash(city, etat, country);
	}

	@Override
	public String toString() {
		return "Lieu [rue=" + street + ", ville=" + city + ", etat=" + etat + " pays " + "]";
	}

	/**
	 * Getter for rue
	 * 
	 * @return the rue
	 */
	public String getStreet() {
		return street != null ? street : "";
	}

	/**
	 * Setter for rue
	 * 
	 * @param rue the rue to set
	 */
	public void setStreet(String rue) {
		this.street = rue;
	}

	/**
	 * Getter for ville
	 * 
	 * @return the ville
	 */
	public String getCity() {
		return city != null ? city : "";
	}

	/**
	 * Setter for ville
	 * 
	 * @param ville the ville to set
	 */
	public void setCity(String ville) {
		this.city = ville;
	}

	/**
	 * Getter for etat
	 * 
	 * @return the etat
	 */
	public String getEtat() {
		return etat != null ? etat : "";
	}

	/**
	 * Setter for etat
	 * 
	 * @param etat the etat to set
	 */
	public void setEtat(String etat) {
		this.etat = etat;
	}

	/**
	 * Getter for pays
	 * 
	 * @return the pays
	 */
	public Country getCountry() {
		return country;
	}

	/**
	 * Setter for pays
	 * 
	 * @param country the pays to set
	 */
	public void setCountry(Country country) {
		this.country = country;
	}

	/**
	 * Getter for id
	 * 
	 * @return the id
	 */
	public int getId() {
		return id;
	}

}
