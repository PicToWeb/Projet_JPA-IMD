package dao;

import java.util.ArrayList;
import java.util.List;

import entity.Lieu;
import entity.Pays;
import utils.JpaConnection;

public class LieuDao implements DaoInterface<Lieu> {

	List<Lieu> lieuList = new ArrayList<>();

	/**
	 * Constructor
	 * 
	 * @param lieuList
	 */
	public LieuDao() {
		this.lieuList = findAll();

	}

	public boolean lieuExist(Lieu lieu) {
		return lieuList.stream()
				.anyMatch(l -> l.getRue() != null && l.getRue().equals(lieu.getRue()) && l.getVille() != null
						&& l.getVille().equals(lieu.getVille()) && l.getEtat() != null
						&& l.getEtat().equals(lieu.getEtat()));
	}

	public List<Lieu> findAll() {
		return JpaConnection.getEntityManager().createQuery("SELECT l FROM Lieu l JOIN FETCH l.pays", Lieu.class)
				.getResultList();
	}

	// ajout rue
	public Lieu findByName(Lieu lieu) {
		return lieuList.stream()
				.filter(l -> l.getVille() != null
						&& l.getVille().equals(lieu.getVille()))
				.filter(l -> l.getEtat() != null && l.getEtat().equals(lieu.getEtat())).findFirst().orElse(null);

	}

	public void lieuExistOrAdded(Lieu lieu) {
		 Lieu existingLieu = findByName(lieu);
		    if (existingLieu == null) {
		        existingLieu = new Lieu(lieu.getRue(), lieu.getVille(), lieu.getEtat(), lieu.getPays());
		        insert(existingLieu);
		    }
	}

	@Override
	public void insert(Lieu lieu) {
		JpaConnection.persist(lieu);
		lieuList.add(lieu);
	}

	@Override
	public void delete(Lieu entity) {

	}

}
