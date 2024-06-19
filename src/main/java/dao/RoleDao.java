package dao;

import java.util.ArrayList;
import java.util.List;
import entity.Role;
import service.connection.JpaLink;

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
		return roleList.stream().filter(p -> p.getPerson().equals(person)).findFirst().orElse(null);
	}
	
	public boolean roleExist(Role role) {
		return roleList.stream().anyMatch(r ->r.getActor().getId().equals(role.getActor().getId()) && r.getMovie().getId().equals(role.getMovie().getId()) && r.getPerson().equals(role.getPerson()));
	}

	public List<Role> findAll() {

		return JpaLink.getEntityManager().createQuery("SELECT r FROM Role r ", Role.class).getResultList();
	}

	@Override
	public void insert(Role role) {

		JpaLink.persist(role);
		roleList.add(role);

	}

	@Override
	public void delete(Role role) {

	}

}
