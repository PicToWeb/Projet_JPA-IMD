package entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "ACTEUR")
public class Acteur extends Intervenant {

	@Column(name = "taille", length = 10)
	private String taille;
	
	
	@OneToMany(mappedBy="acteur")
	private Set<Role> roles = new HashSet<>();
	

	public Acteur(String id, String identite, String url, String taille) {
		super(id, identite, url);
		this.taille = taille;
	}

	public Acteur() {
	}

	
	@Override
	public String toString() {
		return "Acteur [ " +super.id + " " +super.identite  + " LieuNaissance= " + getLieu() + " DateNaissance= " + getDateNaissance() + 
				" taille= "+ getTaille() + " " + " URL= " + super.url + "]";
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
	

}
