package entity;

import java.util.Objects;

import org.hibernate.type.TrueFalseConverter;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import utils.ShowThis;

@Entity
@Table(name = "ROLE")
public class Role {

	/** id */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	/** person */
	@Column(name = "person", length = 500)
	private String person;
	
	/** isPrincipal */
	@Column(name="main_casting")
	@Convert(converter = TrueFalseConverter.class)
	private boolean isPrincipal;

	/** movie */
	@ManyToOne
	@JoinColumn(name = "id_movie")
	private Movie movie ;
	
	/** actor */
	@ManyToOne
	@JoinColumn(name = "id_actor")
	private Actor actor;

	/** Constructor
	 * @param person
	 * @param movie
	 * @param actor
	 */
	public Role(String person, Movie movie, Actor actor) {
		this.person = person;
		this.movie = movie;
		this.actor = actor;
	}

	/** Constructor
	 * 
	 * 
	 */
	public Role() {
		
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Role role = (Role) o;
		return Objects.equals(actor.getId(), role.getActor().getId()) && Objects.equals(movie.getId(), role.getMovie().getId());
	}

	@Override
	public int hashCode() {
		return Objects.hash(actor, movie);
	}

	@Override
	public String toString() {
		//return ShowThis.toString(" ","Personnage: ",person,", isPrincipal: ", isPrincipal,", Movie :",getMovie().getId(),", Actor :",getActor().getId());	
		return ShowThis.toString(" "," isPrincipal: ", isPrincipal);	
	}

	/** Getter for personnage
	 * @return the personnage
	 */
	public String getPerson() {
		return person;
	}

	/** Setter for personnage
	 * @param person the personnage to set
	 */
	public void setPerson(String person) {
		this.person = person;
	}

	/** Getter for film
	 * @return the film
	 */
	public Movie getMovie() {
		return movie;
	}

	/** Setter for film
	 * @param movie the film to set
	 */
	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	/** Getter for acteur
	 * @return the acteur
	 */
	public Actor getActor() {
		return actor;
	}

	/** Setter for acteur
	 * @param actor the acteur to set
	 */
	public void setActor(Actor actor) {
		this.actor = actor;
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
