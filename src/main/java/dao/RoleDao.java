package dao;

import java.util.ArrayList;



import java.util.List;
import entity.Role;
import jakarta.persistence.TypedQuery;
import service.connection.JpaLink;

/**
 * 
 */
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

	
	/**
	 * @return
	 */
	public List<Role> findAll() {
		return JpaLink.getEntityManager()
				.createQuery("SELECT r FROM Role r LEFT JOIN FETCH r.actor LEFT JOIN FETCH r.movie", Role.class).getResultList();
	}

	/**
	 * @param roleList
	 */
	public void allInsert(List<Role> roleList) {
		
		for (Role r : roleList) {

			Role role = new Role(r.getPerson(), r.getMovie(), r.getActor());
			role.setPrincipal(r.isPrincipal());
			
			insert(role);	
		}
	}

	public Role findByPersonName(String person) {
		return roleList.stream().filter(p -> p.getPerson().equals(person)).findFirst().orElse(null);
	}

	public boolean roleExist(Role role) {
		return roleList.stream().anyMatch(r -> r.getActor().getId().equals(role.getActor().getId())
				&& r.getMovie().getId().equals(role.getMovie().getId()) &&  r.getPerson().equals(role.getPerson()));
	}
	
	public List<Role> findCasting(String movieSearched) {
		TypedQuery<Role> query = JpaLink.getEntityManager().createQuery(
				  "SELECT r FROM Role r JOIN r.movie m WHERE m.name = :movieName", Role.class);
				List<Role> roles = query.setParameter("movieName", movieSearched).getResultList();
		return roles;
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
