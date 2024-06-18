package parseCsv;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

import dao.ActorDao;
import dao.LieuDao;
import dao.MovieDao;
import dao.RealisateurDao;
import entity.Acteur;
import entity.Film;
import entity.Role;
import utils.FileSource;
import utils.JpaConnection;

public abstract class RoleReaderCsv {
	public static final LieuDao lieuDao = JpaConnection.lieuDao();
	public static final RealisateurDao realisateurDao = JpaConnection.realisateurDao();
	public static final ActorDao actorDao = JpaConnection.actorDao();
	public static final MovieDao movieDao = JpaConnection.movieDao();

	public static List<Role> readFileToList(String urlFile) throws IOException {

		List<Role> roleList = new ArrayList<>();
		List<String> linesFileList = null;

		List<Role> mainCastingList = readFileToLinkMainCasting(FileSource.nom("castingPrincipal.csv"));

		File file = new File(urlFile);
		linesFileList = FileUtils.readLines(file, "UTF-8");
		linesFileList.remove(0);

		for (String line : linesFileList) {
			Role role = parseStringBeforeAdd(line);
			Boolean isPrincipal = findMainRole(mainCastingList, role);
			
			role.setPrincipal(isPrincipal);
			roleList.add(role);

		}

		return roleList;

	}

	public static Role parseStringBeforeAdd(String line) {

		String[] column = line.split(";");

		// if (column.length > 9) return new Film();

		Film movieId = movieDao.findMovieById(column[0]);
		Acteur actorId = actorDao.findActorById(column[1]);
		String person = "";
		
		if (column.length > 2) {
			person = column[2];
		}

		Role role = new Role(person, movieId, actorId);

		return role;

	}

	public static List<Role> readFileToLinkMainCasting(String urlFile) throws IOException {

		List<Role> mainCastingList = new ArrayList<>();
		Role role = new Role();

		List<String> linesCasting = null;
		File fileProdMov = new File(urlFile);
		linesCasting = FileUtils.readLines(fileProdMov, "UTF-8");
		linesCasting.remove(0);

		for (String c : linesCasting) {
			String[] column = c.split(";");
			Film movie = movieDao.findMovieById(column[0]);
			Acteur actor = actorDao.findActorById(column[1]);
			role.setActeur(actor);
			role.setFilm(movie);
			mainCastingList.add(role);
		}
		return mainCastingList;
	}

	public static Boolean findMainRole(List<Role> mainCastingList, Role role) {
		
		return mainCastingList.stream()
				.anyMatch(r -> r.getActeur().getId().equals(role.getActeur().getId()) && r.getFilm().getId().equals(role.getFilm().getId()));
	}

}
