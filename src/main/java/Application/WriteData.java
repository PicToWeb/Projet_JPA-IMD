package Application;

import java.util.HashMap;
import java.util.List;

import dao.CountryDao;
import dao.LieuDao;
import entity.Acteur;
import entity.Pays;
import fileReaderCsv.ActorReaderCsv;
import fileReaderCsv.CountryReaderCsv2;
import utils.FileSource;
import utils.JpaConnection;


public class WriteData {

	public static final CountryDao countryDao = JpaConnection.countryDao();
	public static final LieuDao lieuDao = JpaConnection.lieuDao();
			
	public static void main(String[] args) {
		

		List<Pays> countryList = CountryReaderCsv2.readFileToList(FileSource.nom("pays.csv"));
		
		for(Pays p : countryList) {
			if(!countryDao.verifyCountry(p.getNom())) {
			countryDao.insert(p);
			}
		}
		
		
		//System.out.println(country.findAll().toString());
		
		//HashMap<String,Acteur> actorList = ActorReaderCsv.readFileToMap(FileSource.nom("acteurs.csv"));
		
		//lieu -> lier le pays en base (id)
		// Ajouter mon acteur -> lié à mon lieu 
		
		//actorList.values().stream().map(actor->actor.getLieu()).forEach(System.out::println);
		
		
		// un seul stream
		//for(Acteur a : actorList.values())
//			Lieu lieu = lieuDao.findAdresse(a.getLieu())
//			//
//			a.setLieu(lieu);
//		//objet acteur auquel un lieu est associé
//		acteurDao.insert(a);
//			System.out.println(a.getIdentite() + a.getLieu().getPays());


		
//		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpa_IMD");
//		EntityManager em = entityManagerFactory.createEntityManager();

//		EntityTransaction transaction = em.getTransaction();
//
//		transaction.begin();
//

//
//		Lieu lieuNaissance = new Lieu("rue 05", "Ville lieu", "Etat lieu", pays);
//		em.persist(lieuNaissance);
//
//		Realisateur real = new Realisateur("Fn1458789", "Dupont Jean", "fzejflfzjf/flefle.com", "1.85m");
//		LocalDate addDate = LocalDate.of(1992, 03, 23);
//		real.setLieu(lieuNaissance);
//		real.setDateNaissance(addDate);
//		em.persist(real);
//
//		Langue lgFr = new Langue("Français");
//		em.persist(lgFr);
//
//		Genre genre = new Genre("Drame");
//		em.persist(genre);
//
//		Film film1 = new Film("fe87978", "TitreFilm", 1952, 8.5, "monfilm.be", lgFr, lieuNaissance, pays);
//		film1.getRealisateurs().add(real);
//		film1.getGenres().add(genre);
//		em.persist(film1);
//
//		Acteur act1 = new Acteur("Fr4447", "Acteur numero1", "acteur/gejj.fr", "1m86");
//		act1.setDateNaissance(addDate);
//		act1.setLieu(lieuNaissance);
//		em.persist(act1);
//
//		Role role1 = new Role("Cassandra", film1, act1);
//		role1.setPrincipal(true);
//		em.persist(role1);
//
//		transaction.commit();

//		em.close();

	}

}
