package service.connection;

import dao.ActorDao;
import dao.AddressDao;
import dao.CountryDao;
import dao.LanguageDao;
import dao.MovieDao;
import dao.MovieGenreDao;
import dao.ProducerDao;
import dao.RoleDao;

/**
 * Abstract class representing a link to various DAO (Data Access Object) instances.
 */
public abstract class DaoLink {

	/** DAO instance for managing countries */
	private static final CountryDao COUNTRY_DAO = new CountryDao();
	 /** DAO instance for managing addresses */
	private static final AddressDao ADRESSE_DAO = new AddressDao();
	 /** DAO instance for managing actors */
	private static final ActorDao ACTOR_DAO = new ActorDao();
	/** DAO instance for managing producers */
	private static final ProducerDao PRODUCER_DAO = new ProducerDao();
	 /** DAO instance for managing movies */
	private static final MovieDao MOVIE_DAO = new MovieDao();
	 /** DAO instance for managing movie genres */
	private static final MovieGenreDao MOVIE_GENRE = new MovieGenreDao();
	 /** DAO instance for managing languages */
	private static final LanguageDao LANGUE_DAO = new LanguageDao();
	/** DAO instance for managing roles */
	private static final RoleDao ROLE_DAO = new RoleDao();

	 /**
     * Retrieves the DAO instance for managing countries.
     *
     * @return CountryDao instance
     */
	public static CountryDao countryDao() {
		return COUNTRY_DAO;
	}

	 /**
     * Retrieves the DAO instance for managing addresses.
     *
     * @return AddressDao instance
     */
	public static AddressDao addressDao() {
		return ADRESSE_DAO;
	}

	 /**
     * Retrieves the DAO instance for managing actors.
     *
     * @return ActorDao instance
     */
	public static ActorDao actorDao() {
		return ACTOR_DAO;
	}

	 /**
     * Retrieves the DAO instance for managing producers.
     *
     * @return ProducerDao instance
     */
	public static ProducerDao producerDao() {
		return PRODUCER_DAO;
	}

	 /**
     * Retrieves the DAO instance for managing movies.
     *
     * @return MovieDao instance
     */
	public static MovieDao movieDao() {
		return MOVIE_DAO;
	}

	/**
     * Retrieves the DAO instance for managing movie genres.
     *
     * @return MovieGenreDao instance
     */
	public static MovieGenreDao movieGenreDao() {
		return MOVIE_GENRE;
	}

	/**
     * Retrieves the DAO instance for managing languages.
     *
     * @return LanguageDao instance
     */
	public static LanguageDao languageDao() {
		return LANGUE_DAO;
	}
	/**
     * Retrieves the DAO instance for managing roles.
     *
     * @return RoleDao instance
     */
	public static RoleDao roleDao() {
		return ROLE_DAO;
	}

}
