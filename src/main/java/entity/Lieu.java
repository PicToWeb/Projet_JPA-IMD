package entity;

import java.util.Objects;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "LIEU")
public class Lieu {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "rue", length = 150)
	private String rue;

	@Column(name = "ville", length = 60)
	private String ville;

	@Column(name = "etat", length = 100)
	private String etat;

	@OneToMany(mappedBy = "lieu")
	private Set<Intervenant> intervenants;

	@OneToMany(mappedBy = "lieu")
	private Set<Film> films;

	@ManyToOne
	@JoinColumn(name = "id_pays")
	private Pays pays;

	/**
	 * Constructor
	 * 
	 * @param rue
	 * @param ville
	 * @param etat
	 * @param pays
	 */
	public Lieu(String rue, String ville, String etat, Pays pays) {
		this.rue = rue;
		this.ville = ville;
		this.etat = etat;
		this.pays = pays;
	}

	public Lieu() {
		// TODO Auto-generated constructor stub
	}

//	@Override
//	public boolean equals(Object o) {
//		if (this == o)
//			return true;
//		if (o == null || getClass() != o.getClass())
//			return false;
//		Lieu adress = (Lieu) o;
//		return Objects.equals(ville, adress.ville) && Objects.equals(etat, adress.etat)
//				&& Objects.equals(pays, adress.pays);
//	}
//
//	@Override
//	public int hashCode() {
//		return Objects.hash(ville, etat, pays);
//	}

	@Override
	public String toString() {
		return "Lieu [rue=" + rue + ", ville=" + ville + ", etat=" + etat + ", pays=" + pays.getNom() + "]";
	}

	/**
	 * Getter for rue
	 * 
	 * @return the rue
	 */
	public String getRue() {
		return rue;
	}

	/**
	 * Setter for rue
	 * 
	 * @param rue the rue to set
	 */
	public void setRue(String rue) {
		this.rue = rue;
	}

	/**
	 * Getter for ville
	 * 
	 * @return the ville
	 */
	public String getVille() {
		return ville;
	}

	/**
	 * Setter for ville
	 * 
	 * @param ville the ville to set
	 */
	public void setVille(String ville) {
		this.ville = ville;
	}

	/**
	 * Getter for etat
	 * 
	 * @return the etat
	 */
	public String getEtat() {
		return etat;
	}

	/**
	 * Setter for etat
	 * 
	 * @param etat the etat to set
	 */
	public void setEtat(String etat) {
		this.etat = etat;
	}

	/**
	 * Getter for pays
	 * 
	 * @return the pays
	 */
	public Pays getPays() {
		return pays;
	}

	/**
	 * Setter for pays
	 * 
	 * @param pays the pays to set
	 */
	public void setPays(Pays pays) {
		this.pays = pays;
	}

	/** Getter for id
	 * @return the id
	 */
	public int getId() {
		return id;
	}

}
