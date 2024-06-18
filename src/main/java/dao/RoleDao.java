package dao;

import java.util.ArrayList;
import java.util.List;
import entity.Role;
import utils.JpaConnection;

public class RoleDao implements DaoInterface<Role> {

	List<Role> roleList = new ArrayList<>();

	/**
	 * Constructor
	 * 
	 * @param lieuMap
	 */
	public RoleDao() {
		this.roleList = findAll();

	}
	public Role findByPersonName(String person) {
		return roleList.stream().filter(p -> p.getPersonnage().equals(person)).findFirst().orElse(null);
	}
	
	public boolean roleExist(Role role) {
		return roleList.stream().anyMatch(r ->r.getActeur().getId().equals(role.getActeur().getId()) && r.getFilm().getId().equals(role.getFilm().getId()) && r.getPersonnage().equals(role.getPersonnage()));
	}

	public List<Role> findAll() {

		return JpaConnection.getEntityManager().createQuery("SELECT r FROM Role r JOIN FETCH r.film f JOIN FETCH r.acteur a JOIN FETCH a.lieu JOIN FETCH f.realisateurs rea JOIN FETCH rea.lieu l ", Role.class).getResultList();
	}

	@Override
	public void insert(Role role) {

		JpaConnection.persist(role);
		roleList.add(role);

	}

	@Override
	public void delete(Role role) {

	}

}
