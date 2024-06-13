package Application;

import fileReader.ActeurLecteurFile;
import fileReader.PaysReader;

import utils.FileSource;

public class WriteData {

	public static void main(String[] args) {

		
		//ActeurLecteurFile.lire(FileSource.nom("acteurs.csv"));
		
		PaysReader.lire(FileSource.nom("pays.csv"));
		
//		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpa_IMD");
//		EntityManager em = entityManagerFactory.createEntityManager();
//
//		EntityTransaction transaction = em.getTransaction();
//
//		transaction.begin();
//
//		Pays pays = new Pays("Belgique", "http://belgique.be");
//		//em.persist(pays);
//
//		Lieu lieuNaissance = new Lieu("rue 05", "Ville lieu", "Etat lieu", pays);
//		//em.persist(lieuNaissance);
//
//		Realisateur real = new Realisateur("Fn1458789", "Dupont Jean", "fzejflfzjf/flefle.com", "1.85m");
//		LocalDate addDate = LocalDate.of(1992, 03, 23);
//		real.setLieu(lieuNaissance);
//		real.setDateNaissance(addDate);
//		//em.persist(real);
//
//		Langue lgFr = new Langue("Français");
//		//em.persist(lgFr);
//
//		Genre genre = new Genre("Drame");
//		//em.persist(genre);
//
//		Film film1 = new Film("fe87978", "TitreFilm", 1952, 8.5, "monfilm.be", lgFr, lieuNaissance, pays);
//		film1.getRealisateurs().add(real);
//		film1.getGenres().add(genre);
//		//em.persist(film1);
//
//		Acteur act1 = new Acteur("Fr4447", "Acteur numero1", "acteur/gejj.fr", "1m86");
//		act1.setDateNaissance(addDate);
//		act1.setLieu(lieuNaissance);
//		//em.persist(act1);
//
//		Role role1 = new Role("Cassandra", film1, act1);
//		role1.setPrincipal(true);
//		//em.persist(role1);
//
//		transaction.commit();
//
//		em.close();

	}

}
