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
@Table(name = "MOVIE")
public class Movie {

	/** id */
	@Id
	@Column(name = "id", length = 15)
	private String id;

	/** name */
	@Column(name = "name", length = 150)
	private String name;

	/** year */
	@Column(name = "year", length = 5)
	private Integer year;

	/** rating */
	@Column(name = "rating", length = 5)
	private Double rating;

	/** url */
	@Column(name = "url", length = 500)
	private String url;
	
	/** resume */
	@Column(name = "resume",length =1500)
	private String resume;

	/** movieGenres */
	@ManyToMany
	@JoinTable(name = "GENRE_MOVIE", joinColumns = @JoinColumn(name = "id_movie", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "id_genre", referencedColumnName = "id"))
	private Set<MovieGenre> movieGenres = new HashSet<>();
		
	/** producers */
	@ManyToMany
	@JoinTable(name = "PRODUCER_MOVIE", joinColumns = @JoinColumn(name = "id_movie", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "id_producer", referencedColumnName = "id_primary"))
	private Set<Producer> producers = new HashSet<>();
	
	/** movieLanguage */
	@ManyToOne
	@JoinColumn(name="id_language")
	private MovieLanguage movieLanguage;
	
	/** address */
	@ManyToOne
	@JoinColumn(name="id_adress")
	private Address address;
	
	/** country */
	@ManyToOne
	@JoinColumn(name="id_country")
	private Country country;
	
	/** roles */
	@OneToMany(mappedBy="movie")
	private Set<Role> roles = new HashSet<>();

	/** Constructor
	 * @param id
	 * @param name
	 * @param year
	 * @param rating
	 * @param url
	 * @param resume
	 */
	public Movie(String id, String name, Integer year, Double rating, String url, String resume ) {
		this.id = id;
		this.name = name;
		this.year = year;
		this.rating = rating;
		this.url = url;
		this.resume = resume;
	}

	/** Constructor
	 * 
	 */
	public Movie() {
	}
	
	
	/**
	 * @param producer
	 */
	public void addProducer(Producer producer) {
		producers.add(producer);
	}

	@Override
	public String toString() {
		return "Film [id=" + id + ", nom=" + name + ", annee=" + year + ", rating=" + rating + ", url=" + url
				+ ", resume=" + resume + ", genres=" + movieGenres + ", realisateurs=" + producers + ", langue=" + movieLanguage
				+ ", lieu=" + address + ", pays=" + country + ", roles=" + roles + "]";
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
	public String getNam() {
		return name;
	}

	/** Setter for nom
	 * @param nom the nom to set
	 */
	public void setName(String nom) {
		this.name = nom;
	}

	/** Getter for annee
	 * @return the annee
	 */
	public Integer getYear() {
		return year;
	}

	/** Setter for annee
	 * @param annee the annee to set
	 */
	public void setYear(Integer year) {
		this.year = year;
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
	public Set<MovieGenre> getGenres() {
		return movieGenres;
	}

	/** Setter for genres
	 * @param movieGenres the genres to set
	 */
	public void setGenres(Set<MovieGenre> movieGenres) {
		this.movieGenres = movieGenres;
	}


	/** Getter for realisateurs
	 * @return the realisateurs
	 */
	public Set<Producer> getProducers() {
		return producers;
	}

	/** Setter for realisateurs
	 * @param producers the realisateurs to set
	 */
	public void setProducers(Set<Producer> producers) {
		this.producers = producers;
	}

	/** Getter for langue
	 * @return the langue
	 */
	public MovieLanguage getLanguage() {
		return movieLanguage;
	}

	/** Setter for langue
	 * @param movieLanguage the langue to set
	 */
	public void setLanguage(MovieLanguage movieLanguage) {
		this.movieLanguage = movieLanguage;
	}

	/** Getter for lieu
	 * @return the lieu
	 */
	public Address getAdress() {
		return address;
	}

	/** Setter for lieu
	 * @param address the lieu to set
	 */
	public void setAdress(Address address) {
		this.address = address;
	}

	/** Getter for pays
	 * @return the pays
	 */
	public Country getCountry() {
		return country;
	}

	/** Setter for pays
	 * @param country the pays to set
	 */
	public void setCountry(Country country) {
		this.country = country;
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
