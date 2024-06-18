package entity;


import java.util.HashSet;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "PRODUCER")
public class Producer extends Person {

	
	@ManyToMany
	@JoinTable(name = "FILM_REAL", joinColumns = @JoinColumn(name = "id_real", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "id_movie", referencedColumnName = "id"))
	private Set<Movie> movies = new HashSet<>();
	
	@OneToMany(mappedBy="actor")
	private Set<Role> roles = new HashSet<>();
	

	public Producer(String id, String identity, String url) {
		super(id, identity, url);
	}


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
