package parseCsv;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import dao.ActorDao;
import dao.AdressDao;
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
	public static final AdressDao adressDao = DaoLink.adressDao();
	/** actorDao */
	public static final ActorDao actorDao = DaoLink.actorDao();
	/** movieDao */
	public static final MovieDao movieDao = DaoLink.movieDao();

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
	public static List<Role> readFileToList(String url, String urlDep) {

		List<Role> roleList = new ArrayList<>();
		List<String> linesFileList = null;
		Boolean isPrincipal = false;

			List<Role> mainCastingList = readFileToLinkMainCasting(urlDep);
			
			linesFileList = FileSource.readLinesCsv(url);
			linesFileList.remove(0);
			for (String line : linesFileList) {
				Role role = parseStringBeforeAdd(line);
				
				//System.out.println(role.getMovie().getId());
				//System.out.println("----------" + " bug : nm0111348");
				//System.out.println(role.getActor().getId());
//				try {
//					isPrincipal = findMainRole(mainCastingList, role);
//					
//				}catch(Exception e) {
//					System.err.println(e.getMessage());
//				}
				//role.setPrincipal(isPrincipal);
				roleList.add(role);
				
			}
			System.out.println(mainCastingList.size());
			System.out.println(roleList.size());
			
			 Map<String, String> filmActorMap = roleList.stream()
		                .filter(role -> mainCastingList .stream()
		                        .anyMatch(movie -> movie.getMovie().getId().equals(role.getMovie().getId())))
		                .collect(Collectors.toMap(
		                        role -> role.getMovie().getId(),
		                        role -> role.getActor().getId()));

			 roleList.forEach(movie -> {
		            String actorId = filmActorMap.get(movie.getActor().getId());
		            if (actorId != null) {
		                 movie.setPrincipal(true); 
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
	public static Role parseStringBeforeAdd(String line) {

		String[] column = line.split(";");

		// if (column.length > 9) return new Film();
		if(column[1].equals("nm0111348") && column[0].equals("tt0073310")) {
			System.out.println(column[1]);
			System.err.println("found");
			System.err.println("found");
			System.err.println("found");
			System.out.println(column[0]);
		}
			
		Movie movie = movieDao.findMovieById(column[0]);
		if(column[1].equals("nm0111348") && column[0].equals("tt0073310")) {
		System.out.println(movie.getId());
		}
		Actor actor = actorDao.findActorById(column[1]);
		String person = "";

		if (column.length > 2) {
			person = column[2];
		}

		Role role = new Role(person, movie, actor);
		if(column[1].equals("nm0111348") && column[0].equals("tt0073310")) {
			System.out.println(role.getMovie().getId());
			}
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
	public static List<Role> readFileToLinkMainCasting(String urlDep) {

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

			Movie movie = movieDao.findMovieById(column[0]);
			Actor actor = actorDao.findActorById(column[1]);
			
			role.setActor(actor);
			role.setMovie(movie);
			mainCastingList.add(role);
		}
		
		return mainCastingList;
	}
	
	//Trouver le film dans castingPrincipal = film dans role
	// Ce film doit etre bouclé et faire matcher les acteurs de casting principal a ceux de role -> is principal = true 

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
