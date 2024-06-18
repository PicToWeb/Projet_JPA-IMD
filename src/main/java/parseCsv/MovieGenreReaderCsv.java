package parseCsv;

import java.util.HashSet;
import java.util.Set;

import dao.MovieGenreDao;
import entity.MovieGenre;
import service.connection.DaoLink;

/**
 * Abstract Class for Movie genre used to parse and create a Set of MovieGenre
 */
public abstract class MovieGenreReaderCsv {

	/** movieGenreDao */
	public static final MovieGenreDao movieGenreDao = DaoLink.movieGenreDao();

	/** Static Method used to parse Genre String and insert in Database if Genre is not exist
	 * @param genreLine from MovieReaderCsv
	 * @return
	 */
	public static Set<MovieGenre> genreExistOrAdded(String genreLine) {

		Set<MovieGenre> genreList = new HashSet<>();

		String[] column = genreLine.split(",");

		for (int i = 0; i < column.length; i++) {
			
			String genreName = column[i].trim();
			MovieGenre movieGenre = movieGenreDao.findByName(genreName);
			
			if (movieGenre == null) {
				movieGenre = new MovieGenre(genreName);
				movieGenreDao.insert(movieGenre);
			}
			genreList.add(movieGenre);
		}
		return genreList;
	}

}
