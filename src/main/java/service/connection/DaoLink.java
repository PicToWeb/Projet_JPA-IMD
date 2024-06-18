package service.connection;

import dao.ActorDao;
import dao.AdressDao;
import dao.CountryDao;
import dao.LanguageDao;
import dao.MovieDao;
import dao.MovieGenreDao;
import dao.ProducerDao;
import dao.RoleDao;

public abstract class DaoLink {

	private static final CountryDao countryDao = new CountryDao();
	private static final AdressDao adressDao = new AdressDao();
	private static final ActorDao actorDao = new ActorDao();
	private static final ProducerDao producerDao = new ProducerDao();
	private static final MovieDao movieDao = new MovieDao();
	private static final MovieGenreDao movieGenreDao = new MovieGenreDao();
	private static final LanguageDao languageDao = new LanguageDao();
	private static final RoleDao roleDao = new RoleDao();

	public static CountryDao countryDao() {
		return countryDao;
	}

	public static AdressDao adressDao() {
		return adressDao;
	}

	public static ActorDao actorDao() {
		return actorDao;
	}

	public static ProducerDao producerDao() {
		return producerDao;
	}

	public static MovieDao movieDao() {
		return movieDao;
	}

	public static MovieGenreDao movieGenreDao() {
		return movieGenreDao;
	}

	public static LanguageDao languageDao() {
		return languageDao;
	}

	public static RoleDao roleDao() {
		return roleDao;
	}

}
