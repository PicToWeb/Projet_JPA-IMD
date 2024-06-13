package entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="INTERVENANT")
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class Intervenant {

	@Id
	@Column(name = "id", length = 15)
	protected String id;

	@Column(name = "identite", length = 150)
	protected String identite;

	@Column(name = "date_naissance", columnDefinition = "DATE")
	protected LocalDate dateNaissance;

	@Column(name = "url", length = 200)
	protected String url;
	
	@ManyToOne
	@JoinColumn(name="id_lieu")
	protected Lieu lieu;

	
	/** Constructor
	 * @param id
	 * @param identite
	 * @param dateNaissance
	 * @param url
	 */
	public Intervenant(String id, String identite, String url) {
		this.id = id;
		this.identite = identite;
		this.url = url;
	}

	public Intervenant() {
		
	}

	/** Getter for id
	 * @return the id
	 */
	public String getId() {
		return id;
	}


	/** Setter for id
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}


	/** Getter for identite
	 * @return the identite
	 */
	public String getIdentite() {
		return identite;
	}


	/** Setter for identite
	 * @param identite the identite to set
	 */
	public void setIdentite(String identite) {
		this.identite = identite;
	}


	/** Getter for dateNaissance
	 * @return the dateNaissance
	 */
	public LocalDate getDateNaissance() {
		return dateNaissance;
	}


	/** Setter for dateNaissance
	 * @param dateNaissance the dateNaissance to set
	 */
	public void setDateNaissance(LocalDate dateNaissance) {
		this.dateNaissance = dateNaissance;
	}


	/** Getter for url
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}


	/** Setter for url
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}


	/** Getter for lieu
	 * @return the lieu
	 */
	public Lieu getLieu() {
		return lieu;
	}


	/** Setter for lieu
	 * @param lieu the lieu to set
	 */
	public void setLieu(Lieu lieu) {
		this.lieu = lieu;
	}
	
	
	
	
}
