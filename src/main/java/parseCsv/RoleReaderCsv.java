package parseCsv;

import java.io.IOException;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import dao.ActorDao;
import dao.AddressDao;
import dao.MovieDao;
import entity.Actor;
import entity.Movie;
import entity.Role;
import service.connection.DaoLink;
import utils.FileSource;
import utils.ShowThis;

/**
 * Abstract Class used to import and merge roles.csv and castingPrincipal.csv
 * 
 */
public abstract class RoleReaderCsv {
	/** adressDao */
	public static final AddressDao ADDRESS_DAO = DaoLink.addressDao();
	/** actorDao */
	public static final ActorDao ACTOR_DAO = DaoLink.actorDao();
	/** movieDao */
	public static final MovieDao MOVIE_DAO = DaoLink.movieDao();

	/**
	 * Static Method used to read each lines of Csv files 
	 * 
	 * First step : Call static Method (readFileToLinkMainCasting)
	 * to process castingPrincipal.csv and return a role List
	 * 
	 * Second Step : Loop each lines from roles.csv to call parseStringBeforeAdd Method 
	 * and return a role Object
	 * Third step : Always in the loop, call findMainRole Method to find witch actor isPrincipal
	 * Four step: set boolean principal role to the Role Object and add this to the list of Role
	 * @param url roles.csv
	 * @param urlDep castingPrincipal.csv
	 * @return List of Role Object whith principal Casting added
	 */
	public static List<Role> readFile(String url, String urlDep) {

		List<Role> roleList = new ArrayList<>();
		List<String> linesFileList = null;

			List<Role> mainCastingList = readFileMainCasting(urlDep);
			
			linesFileList = FileSource.readLinesCsv(url);
			linesFileList.remove(0);
			
			roleList = linesFileList.parallelStream()
				    .map(line -> parseLine(line))
				    .collect(Collectors.toList());
			
			System.out.println("Main Cast List processing - merging");
			
			Map<String, String> filmActorMap = roleList.parallelStream()
			        .filter(role -> role.getMovie() != null) 
			        .filter(role -> mainCastingList.stream()
			                .anyMatch(movie -> movie.getMovie().getId().equals(role.getMovie().getId())))
			        .collect(Collectors.toMap(
			                role -> role.getActor().getId(),
			                role -> role.getMovie().getId()));

			 roleList.forEach(role -> {
		            String actorId = filmActorMap.get(role.getActor().getId());
		            if (actorId != null) {
		                 role.setPrincipal(true); 
		            }
		        });
			 System.out.println("Main Cast List processing - completed");
		return roleList;
	}

	/**
	 * Static Method used to parse row from roles.csv
	 * findMovieById Method is called for Movie
	 * findActorById Method is called for Actor
	 * 
	 * @param line (row of file)
	 * @return Role Object
	 */
	public static Role parseLine(String line) {

		String[] column = line.split(";");

		// if (column.length > 9) return new Film();
			
		Movie movie = MOVIE_DAO.findMovieById(column[0]);
		Actor actor = ACTOR_DAO.findById(column[1]);
		String person = "";

		if (column.length > 2) {
			person = column[2];
		}

		Role role = new Role(person, movie, actor);

		return role;

	}

	/**
	 * Static Method used to parse row from castingPrincipal.csv
	 * findMovieById Method is called for Movie
	 * findActorById Method is called for Actor
	 * 
	 * @param urlDep castingPrincipal.csv
	 * @return List of Role Object
	 * @throws IOException
	 */
	public static List<Role> readFileMainCasting(String urlDep) {

		List<Role> mainCastingList = new ArrayList<>();
		Role role = new Role();
		List<String> linesCasting = null;

		linesCasting = FileSource.readLinesCsv(urlDep);
		linesCasting.remove(0);
		
		System.out.println("---------------");
		System.out.println("Main Cast List processing - in progress");
		System.out.println("---------------");
		
		for (String c : linesCasting) {
			String[] column = c.split(";");

			Movie movie = MOVIE_DAO.findMovieById(column[0]);
			Actor actor = ACTOR_DAO.findById(column[1]);
			
			role.setActor(actor);
			role.setMovie(movie);
			mainCastingList.add(role);
		}

		return mainCastingList;
	}
	
	/**
	 * Static Method is used to compare witch id_actor from list and id_actor from role 
	 * are equals to id_film from list and id_film from role
	 * 
	 * @param mainCastingList
	 * @param role
	 * @return Boolean 
	 */ 
	public static Boolean findMainRole(List<Role> mainCastingList, Role role) {
		
		 return mainCastingList.stream()
		            .anyMatch(r -> r.getMovie().getId().equals(role.getMovie().getId()) &&
		                    r.getActor().getId().equals(role.getActor().getId()));	
	}

}
