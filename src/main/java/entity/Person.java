package entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "PERSON")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Person {

	/** idPrimary */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_primary")
	protected Integer idPrimary;

	/** id */
	@Column(name = "id_person", length = 25)
	protected String id;
	/** identity */
	@Column(name = "identity", length = 150)
	protected String identity;

	/** birthdayDate */
	@Column(name = "birthday_date", columnDefinition = "DATE")
	protected LocalDate birthdayDate;

	/** url */
	@Column(name = "url", length = 200)
	protected String url;

	/** address */
	@ManyToOne
	@JoinColumn(name = "id_adress", nullable = true)
	protected Address address;

	/**
	 * Constructor
	 * 
	 * @param id
	 * @param identity
	 * @param birthdayDate
	 * @param url
	 */
	public Person(String id, String identity, String url) {
		this.id = id;
		this.identity = identity;
		this.url = url;
	}

	/** Constructor
	 * 
	 */
	public Person() {

	}

	/**
	 * Getter for id
	 * 
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Setter for id
	 * 
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Getter for identite
	 * 
	 * @return the identite
	 */
	public String getIdentite() {
		return identity;
	}

	/**
	 * Setter for identite
	 * 
	 * @param identite the identite to set
	 */
	public void setIdentite(String identite) {
		this.identity = identite;
	}

	/**
	 * Getter for dateNaissance
	 * 
	 * @return the dateNaissance
	 */
	public LocalDate getbirthdayDate() {
		return birthdayDate;
	}

	/**
	 * Setter for dateNaissance
	 * 
	 * @param dateNaissance the dateNaissance to set
	 */
	public void setBirthdayDate(LocalDate dateNaissance) {
		this.birthdayDate = dateNaissance;
	}

	/**
	 * Getter for url
	 * 
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * Setter for url
	 * 
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * Getter for lieu
	 * 
	 * @return the lieu
	 */
	public Address getAdress() {
		return address;
	}

	/**
	 * Setter for lieu
	 * 
	 * @param address the lieu to set
	 */
	public void setAdress(Address address) {
		this.address = address;
	}

}
