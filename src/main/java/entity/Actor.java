package entity;

import java.util.HashSet;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "ACTOR")
public class Actor extends Person {

	/** size */
	@Column(name = "size", length = 10)
	private String size;	
	
	/** roles */
	@OneToMany(mappedBy="actor")
	private Set<Role> roles = new HashSet<>();
	

	/** Constructor
	 * @param id
	 * @param identite
	 * @param url
	 * @param size
	 */
	public Actor(String id, String identite, String url, String size) {
		super(id, identite, url);
		this.size = size;
	}
	

	/** Constructor
	 * 
	 */
	public Actor() {
	}

	
	@Override
	public String toString() {
		return "Acteur [ " +super.id + " " +super.identity  + " LieuNaissance= " + getAdress() + " DateNaissance= " + getbirthdayDate() + 
				" taille= "+ getSize() + " " + " URL= " + super.url + "]";
	}

	/** Getter for taille
	 * @return the taille
	 */
	public String getSize() {
		return size;
	}

	/** Setter for taille
	 * @param size the taille to set
	 */
	public void setSize(String size) {
		this.size = size;
	}
	
	
	

}
