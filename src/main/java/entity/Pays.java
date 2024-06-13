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
@Table(name = "PAYS")
public class Pays {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "nom", length = 60)
	private String nom;

	@Column(name = "url", length = 200)
	private String url;
	
	@OneToMany(mappedBy = "pays")
	private Set<Film> films;
	
	@OneToMany(mappedBy = "pays")
	private Set<Lieu> lieux;

	/** Constructor
	 * @param nom
	 * @param url
	 */
	public Pays(String nom, String url) {
		this.nom = nom;
		this.url = url;
	}
	
	

}
