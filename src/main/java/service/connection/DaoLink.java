package service.connection;

import dao.ActorDao;
import dao.AddressDao;
import dao.CountryDao;
import dao.LanguageDao;
import dao.MovieDao;
import dao.MovieGenreDao;
import dao.ProducerDao;
import dao.RoleDao;

public abstract class DaoLink {

	private static final CountryDao COUNTRY_DAO = new CountryDao();
	private static final AddressDao ADRESSE_DAO = new AddressDao();
	private static final ActorDao ACTOR_DAO = new ActorDao();
	private static final ProducerDao PRODUCER_DAO = new ProducerDao();
	private static final MovieDao MOVIE_DAO = new MovieDao();
	private static final MovieGenreDao MOVIE_GENRE = new MovieGenreDao();
	private static final LanguageDao LANGUE_DAO = new LanguageDao();
	private static final RoleDao ROLE_DAO = new RoleDao();

	public static CountryDao countryDao() {
		return COUNTRY_DAO;
	}

	public static AddressDao addressDao() {
		return ADRESSE_DAO;
	}

	public static ActorDao actorDao() {
		return ACTOR_DAO;
	}

	public static ProducerDao producerDao() {
		return PRODUCER_DAO;
	}

	public static MovieDao movieDao() {
		return MOVIE_DAO;
	}

	public static MovieGenreDao movieGenreDao() {
		return MOVIE_GENRE;
	}

	public static LanguageDao languageDao() {
		return LANGUE_DAO;
	}

	public static RoleDao roleDao() {
		return ROLE_DAO;
	}

}
