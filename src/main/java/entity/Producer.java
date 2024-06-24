package entity;


import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "PRODUCER")
public class Producer extends Person {

	
	/** movies */
	@ManyToMany
	@JoinTable(name = "PRODUCER_MOVIE", joinColumns = @JoinColumn(name = "id_producer", referencedColumnName = "id_primary"), inverseJoinColumns = @JoinColumn(name = "id_movie", referencedColumnName = "id"))
	private Set<Movie> movies = new HashSet<>();
	
	/** Constructor
	 * @param id
	 * @param identity
	 * @param url
	 */
	public Producer(String id, String identity, String url) {
		super(id, identity, url);
	}


	/** Constructor 
	 * 
	 */
	public Producer() {
		
	}


	/** Getter for films
	 * @return the films
	 */
	public Set<Movie> getMovies() {
		return movies;
	}

	/** Setter for films
	 * @param movies the films to set
	 */
	public void setMovies(Set<Movie> movies) {
		this.movies = movies;
	}

}
