package entity;

import java.util.HashSet;
import java.util.Set;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "LANGUAGE")
public class MovieLanguage {

	/** id */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	/** name */
	@Column(name = "name", length = 50)
	private String name;

	/** movies */
	@OneToMany(mappedBy="movieLanguage")
	private Set<Movie> movies = new HashSet<>();

	/**
	 * Constructor
	 * 
	 * @param id
	 * @param name
	 */
	public MovieLanguage(String name) {
		this.name = name;
	}
	

	/** Constructor
	 * 
	 * 
	 */
	public MovieLanguage() {
	}


	@Override
	public String toString() {
		return "Langue [nom=" + name + "]";
	}


	/**
	 * Getter for nom
	 * 
	 * @return the nom
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setter for nom
	 * 
	 * @param nom the nom to set
	 */
	public void setName(String name) {
		this.name = name;
	}

}
