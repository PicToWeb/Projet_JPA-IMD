package entity;


import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "REALISATEUR")
public class Realisateur extends Intervenant {

	@Column(name = "taille", length = 10)
	private String taille;
	
	@ManyToMany
	@JoinTable(name = "FILM_REAL", joinColumns = @JoinColumn(name = "id_real", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "id_film", referencedColumnName = "id"))
	private Set<Film> films = new HashSet<>();
	
	@OneToMany(mappedBy="acteur")
	private Set<Role> roles = new HashSet<>();
	

	public Realisateur(String id, String identite, String url, String taille) {
		super(id, identite, url);
		this.taille = taille;
	}

	/** Getter for taille
	 * @return the taille
	 */
	public String getTaille() {
		return taille;
	}

	/** Setter for taille
	 * @param taille the taille to set
	 */
	public void setTaille(String taille) {
		this.taille = taille;
	}

	/** Getter for films
	 * @return the films
	 */
	public Set<Film> getFilms() {
		return films;
	}

	/** Setter for films
	 * @param films the films to set
	 */
	public void setFilms(Set<Film> films) {
		this.films = films;
	}

	/** Getter for roles
	 * @return the roles
	 */
	public Set<Role> getRoles() {
		return roles;
	}

	/** Setter for roles
	 * @param roles the roles to set
	 */
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	
	

}
