package service.searchData;

import java.util.Scanner;


import dao.ActorDao;
import dao.MovieDao;
import dao.RoleDao;
import service.connection.DaoLink;

/**
 * SearchData is an abstract class that provides methods for searching movie-related data.
 * It interacts with DAOs (Data Access Objects) to retrieve information from the database.
 * The class includes methods for searching movies by actor, casting, release year, and more.
 *
 * @author Antoine Picron
 */
public abstract class SearchData {
	/** MOVIE_DAO */
	public static final MovieDao MOVIE_DAO = DaoLink.movieDao();
	/** ROLE_DAO */
	public static final RoleDao ROLE_DAO = DaoLink.roleDao();
	/** ACTOR_DAO */
	public static final ActorDao ACTOR_DAO = DaoLink.actorDao();
	
	/** input */
	public static final Scanner input = new Scanner(System.in);
	
	/**
     * Displays movies associated with a given actor.
     */
	public static void movieOfActor() {
		System.out.println("Witch actor ? ");
		
		MOVIE_DAO.findMovieOfActor(input.nextLine()).forEach(System.out::println);
	}
	
	/**
     * Displays the casting of a specific movie.
     */
	public static void movieCasting() {
		System.out.println("Witch Movie ? ");
		
		ROLE_DAO.findCasting(input.nextLine()).forEach(System.out::println);
	}
	
	 /**
     * Displays movies released between two specified years.
     */
	public static void movieBetweenTwoYear() {
		System.out.println("Year 1 ? ");
		int year1 = input.nextInt();
		System.out.println("Year 2 ? ");
		int year2 = input.nextInt();
		
		MOVIE_DAO.findMovieBetweenYear(year1,year2).forEach(System.out::println);
	}
	
	 /**
     * Displays movies common to two actors.
     */
	public static void communMoviesOfTwoActors() {
		System.out.println("Actor 1 ? ");
		String actor1 = input.nextLine();
		System.out.println("Actor 2 ? ");
		String actor2 = input.nextLine();

		System.out.println(MOVIE_DAO.findCommunMoviebyTwoActors(actor1,actor2));
	}
	
	 /**
     * Displays actors common to two movies.
     */
	public static void communActorsOfTwoMovies() {
		System.out.println("Movie 1 ? ");
		String movie1 = input.nextLine();
		System.out.println("Movie 2 ? ");
		String movie2 = input.nextLine();
		
		ACTOR_DAO.findMovieOfActors(movie1,movie2).forEach(System.out::println);
	}
	
	/**
     * Displays movies released between two years with a specific actor in the cast.
     */
	public static void movieBetweenTwoYearFromActor() {
		System.out.println("Year 1 ? ");
		int year1 = Integer.parseInt(input.nextLine());
		System.out.println("Year 2 ? ");
		int year2 = Integer.parseInt(input.nextLine());
		System.out.println("Actor ? ");
		String actor = input.nextLine();
	
		MOVIE_DAO.findMovieBetweenYearFromActor(year1,year2,actor).forEach(System.out::println);
	}

}
