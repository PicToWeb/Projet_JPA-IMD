package entity;

import java.util.Set;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "COUNTRY")
public class Country {

	/** id */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	/** name */
	@Column(name = "name", length = 60)
	private String name;

	/** url */
	@Column(name = "url", length = 200)
	private String url;
	
	/** movies */
	@OneToMany(mappedBy = "country")
	private Set<Movie> movies;
	
	/** adresses */
	@OneToMany(mappedBy = "country")
	private Set<Address> adresses;
	
	/** Constructor
	 * @param name
	 * @param url
	 */
	public Country(String name, String url) {
		this.name = name;
		this.url = url;
	}

	/** Constructor
	 * 
	 */
	public Country() {
	}

	/** Getter for nom
	 * @return the nom
	 */
	public String getName() {
		return name != null ? name : ""; 
	}

	/** Setter for nom
	 * @param nom the nom to set
	 */
	public void setName(String name) {
		this.name = name;
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

	@Override
	public String toString() {
		return "Pays [nom= " + name + ", url= " + url + "] \n";
	}
	
	
	

}
