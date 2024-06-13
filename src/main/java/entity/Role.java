package entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "ROLE")
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "personne", length = 40)
	private String personnage;
	
	@Column(name="casting_principal")
	private boolean isPrincipal;

	@ManyToOne
	@JoinColumn(name = "id_film")
	private Film film;
	
	@ManyToOne
	@JoinColumn(name = "id_acteur")
	private Acteur acteur;

	/** Constructor
	 * @param personnage
	 * @param film
	 * @param acteur
	 */
	public Role(String personnage, Film film, Acteur acteur) {
		this.personnage = personnage;
		this.film = film;
		this.acteur = acteur;
	}

	/** Getter for personnage
	 * @return the personnage
	 */
	public String getPersonnage() {
		return personnage;
	}

	/** Setter for personnage
	 * @param personnage the personnage to set
	 */
	public void setPersonnage(String personnage) {
		this.personnage = personnage;
	}

	/** Getter for film
	 * @return the film
	 */
	public Film getFilm() {
		return film;
	}

	/** Setter for film
	 * @param film the film to set
	 */
	public void setFilm(Film film) {
		this.film = film;
	}

	/** Getter for acteur
	 * @return the acteur
	 */
	public Acteur getActeur() {
		return acteur;
	}

	/** Setter for acteur
	 * @param acteur the acteur to set
	 */
	public void setActeur(Acteur acteur) {
		this.acteur = acteur;
	}

	/** Getter for isPrincipal
	 * @return the isPrincipal
	 */
	public boolean isPrincipal() {
		return isPrincipal;
	}

	/** Setter for isPrincipal
	 * @param isPrincipal the isPrincipal to set
	 */
	public void setPrincipal(boolean isPrincipal) {
		this.isPrincipal = isPrincipal;
	}
	
	
	

	
	


}
