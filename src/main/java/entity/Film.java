package entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "FILM")
public class Film {

	@Id
	@Column(name = "id", length = 15)
	private String id;

	@Column(name = "nom", length = 150)
	private String nom;

	@Column(name = "annee", length = 5)
	private Integer annee;

	@Column(name = "rating", length = 5)
	private Double rating;

	@Column(name = "url", length = 500)
	private String url;
	
	@Column(name = "resume",length =1500)
	private String resume;

	@ManyToMany
	@JoinTable(name = "FILM_GENRE", joinColumns = @JoinColumn(name = "id_film", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "id_genre", referencedColumnName = "id"))
	private Set<Genre> genres = new HashSet<>();
		
	@ManyToMany
	@JoinTable(name = "FILM_REAL", joinColumns = @JoinColumn(name = "id_film", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "id_real", referencedColumnName = "id"))
	private Set<Realisateur> realisateurs = new HashSet<>();
	
	@ManyToOne
	@JoinColumn(name="id_langue")
	private Langue langue;
	
	@ManyToOne
	@JoinColumn(name="id_lieu")
	private Lieu lieu;
	
	@ManyToOne
	@JoinColumn(name="id_pays")
	private Pays pays;
	
	@OneToMany(mappedBy="film")
	private Set<Role> roles = new HashSet<>();

	/** Constructor
	 * @param id
	 * @param nom
	 * @param annee
	 * @param rating
	 * @param url
	 * @param resume
	 */
	public Film(String id, String nom, Integer annee, Double rating, String url, String resume ) {
		this.id = id;
		this.nom = nom;
		this.annee = annee;
		this.rating = rating;
		this.url = url;
		this.resume = resume;
	}

	public Film() {
	}
	
	
	public void addProducer(Realisateur realisateur) {
		realisateurs.add(realisateur);
	}

	@Override
	public String toString() {
		return "Film [id=" + id + ", nom=" + nom + ", annee=" + annee + ", rating=" + rating + ", url=" + url
				+ ", resume=" + resume + ", genres=" + genres + ", realisateurs=" + realisateurs + ", langue=" + langue
				+ ", lieu=" + lieu + ", pays=" + pays + ", roles=" + roles + "]";
	}
	
	

	/** Getter for id
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/** Setter for id
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/** Getter for nom
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}

	/** Setter for nom
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/** Getter for annee
	 * @return the annee
	 */
	public Integer getAnnee() {
		return annee;
	}

	/** Setter for annee
	 * @param annee the annee to set
	 */
	public void setAnnee(Integer annee) {
		this.annee = annee;
	}

	/** Getter for rating
	 * @return the rating
	 */
	public Double getRating() {
		return rating;
	}

	/** Setter for rating
	 * @param rating the rating to set
	 */
	public void setRating(Double rating) {
		this.rating = rating;
	}

	/** Getter for url
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/** Setter for url
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	
	/** Getter for resume
	 * @return the resume
	 */
	public String getResume() {
		return resume;
	}

	/** Setter for resume
	 * @param resume the resume to set
	 */
	public void setResume(String resume) {
		this.resume = resume;
	}

	/** Getter for genres
	 * @return the genres
	 */
	public Set<Genre> getGenres() {
		return genres;
	}

	/** Setter for genres
	 * @param genres the genres to set
	 */
	public void setGenres(Set<Genre> genres) {
		this.genres = genres;
	}


	/** Getter for realisateurs
	 * @return the realisateurs
	 */
	public Set<Realisateur> getRealisateurs() {
		return realisateurs;
	}

	/** Setter for realisateurs
	 * @param realisateurs the realisateurs to set
	 */
	public void setRealisateurs(Set<Realisateur> realisateurs) {
		this.realisateurs = realisateurs;
	}

	/** Getter for langue
	 * @return the langue
	 */
	public Langue getLangue() {
		return langue;
	}

	/** Setter for langue
	 * @param langue the langue to set
	 */
	public void setLangue(Langue langue) {
		this.langue = langue;
	}

	/** Getter for lieu
	 * @return the lieu
	 */
	public Lieu getLieu() {
		return lieu;
	}

	/** Setter for lieu
	 * @param lieu the lieu to set
	 */
	public void setLieu(Lieu lieu) {
		this.lieu = lieu;
	}

	/** Getter for pays
	 * @return the pays
	 */
	public Pays getPays() {
		return pays;
	}

	/** Setter for pays
	 * @param pays the pays to set
	 */
	public void setPays(Pays pays) {
		this.pays = pays;
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
