package dao;

import java.util.ArrayList;



import java.util.List;
import entity.Role;
import jakarta.persistence.TypedQuery;
import service.connection.JpaLink;

/**
 * Data Access Object (DAO) for managing Role entities.
 */
public class RoleDao implements DaoInterface<Role> {

	/** roleList */
	List<Role> roleList = new ArrayList<>();

    /**
     * Constructor initializes the role list by calling findAll().
     */
	public RoleDao() {
		this.roleList = findAll();

	}

	
    /**
     * Retrieves all Role entities from the database.
     *
     * @return List of Role entities
     */
	public List<Role> findAll() {
		return JpaLink.getEntityManager()
				.createQuery("SELECT r FROM Role r LEFT JOIN FETCH r.actor LEFT JOIN FETCH r.movie", Role.class).getResultList();
	}

    /**
     * Inserts a list of Role entities into the database.
     *
     * @param roleList List of Role entities to insert
     */
	public void allInsert(List<Role> roleList) {
		
		for (Role r : roleList) {

			Role role = new Role(r.getPerson(), r.getMovie(), r.getActor());
			role.setPrincipal(r.isPrincipal());
			
			insert(role);	
		}
	}

    /**
     * Finds a Role entity by person name.
     *
     * @param person Person name to search for
     * @return Role entity or null if not found
     */
	public Role findByPersonName(String person) {
		return roleList.stream().filter(p -> p.getPerson().equals(person)).findFirst().orElse(null);
	}

    /**
     * Checks if a Role entity already exists in the list.
     *
     * @param role Role entity to check
     * @return true if the role exists, false otherwise
     */
	public boolean roleExist(Role role) {
		return roleList.stream().anyMatch(r -> r.getActor().getId().equals(role.getActor().getId())
				&& r.getMovie().getId().equals(role.getMovie().getId()) &&  r.getPerson().equals(role.getPerson()));
	}
	
    /**
     * Finds all roles associated with a specific movie.
     *
     * @param movieSearched Name of the movie to search for
     * @return List of Role entities related to the movie
     */
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


}
