package entity;

import java.util.HashSet;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import utils.ShowThis;

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
		return 
				ShowThis.toString(" ", "Actor =",super.getId(),"Identity :",super.getIdentite(),"Born place :",getAdress()," Birthday date:",getBirthdayDate(),"Size:",getSize(),"Url:",super.getUrl());
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
