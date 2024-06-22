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
 * 
 */
public abstract class DaoLink {

	/** COUNTRY_DAO */
	private static final CountryDao COUNTRY_DAO = new CountryDao();
	/** ADRESSE_DAO */
	private static final AddressDao ADRESSE_DAO = new AddressDao();
	/** ACTOR_DAO */
	private static final ActorDao ACTOR_DAO = new ActorDao();
	/** PRODUCER_DAO */
	private static final ProducerDao PRODUCER_DAO = new ProducerDao();
	/** MOVIE_DAO */
	private static final MovieDao MOVIE_DAO = new MovieDao();
	/** MOVIE_GENRE */
	private static final MovieGenreDao MOVIE_GENRE = new MovieGenreDao();
	/** LANGUE_DAO */
	private static final LanguageDao LANGUE_DAO = new LanguageDao();
	/** ROLE_DAO */
	private static final RoleDao ROLE_DAO = new RoleDao();

	/**
	 * @return
	 */
	public static CountryDao countryDao() {
		return COUNTRY_DAO;
	}

	/**
	 * @return
	 */
	public static AddressDao addressDao() {
		return ADRESSE_DAO;
	}

	/**
	 * @return
	 */
	public static ActorDao actorDao() {
		return ACTOR_DAO;
	}

	/**
	 * @return
	 */
	public static ProducerDao producerDao() {
		return PRODUCER_DAO;
	}

	/**
	 * @return
	 */
	public static MovieDao movieDao() {
		return MOVIE_DAO;
	}

	/**
	 * @return
	 */
	public static MovieGenreDao movieGenreDao() {
		return MOVIE_GENRE;
	}

	/**
	 * @return
	 */
	public static LanguageDao languageDao() {
		return LANGUE_DAO;
	}

	/**
	 * @return
	 */
	public static RoleDao roleDao() {
		return ROLE_DAO;
	}

}
