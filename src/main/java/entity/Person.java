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

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	protected Integer idPrimary;
	
	@Column(name = "idPerson")
	protected String id;
	@Column(name = "identity", length = 150)
	protected String identity;

	@Column(name = "birthday_date", columnDefinition = "DATE")
	protected LocalDate birthdayDate;

	@Column(name = "url", length = 200)
	protected String url;

	@ManyToOne
	@JoinColumn(name = "id_adress", nullable = true)
	protected Adress adress;

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
	public Adress getAdress() {
		return adress;
	}

	/**
	 * Setter for lieu
	 * 
	 * @param adress the lieu to set
	 */
	public void setAdress(Adress adress) {
		this.adress = adress;
	}

}
