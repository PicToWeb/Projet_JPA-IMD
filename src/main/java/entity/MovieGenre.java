package entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "GENRE")
public class MovieGenre {

	/** id */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	/** name */
	@Column(name = "name", length = 40)
	private String name;

	/** movies */
	@ManyToMany
	@JoinTable(name = "GENRE_MOVIE", joinColumns = @JoinColumn(name = "id_genre", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "id_movie", referencedColumnName = "id"))
	private Set<Movie> movies = new HashSet<>();

	/** Constructor
	 * @param name
	 */
	public MovieGenre(String name) {
		this.name = name;
	}
	

	/** Constructor
	 * 
	 */
	public MovieGenre() {
	}

	

	@Override
	public String toString() {
		return "Genre [id=" + id + ", nom=" + name + "]";
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
