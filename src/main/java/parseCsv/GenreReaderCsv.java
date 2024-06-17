package parseCsv;

import java.util.HashSet;
import java.util.Set;

import dao.GenreDao;
import entity.Genre;
import utils.JpaConnection;

public abstract class GenreReaderCsv {
	
	public static final GenreDao genreDao = JpaConnection.genreDao();
	
	public static Set<Genre> genreExistOrAdded(String genreLine) {
		
		Set<Genre> genreList = new HashSet<>();
		
		String[] column = genreLine.split(",");
		
		for(int i =0; i<column.length;i++) {
	  		String genreName = column[i].trim();
	  		Genre movieGenre = genreDao.findByName(genreName);
	  		if(movieGenre == null) {
	  			movieGenre = new Genre(genreName);
	  			genreDao.insert(movieGenre);
	  		} 
	  		genreList.add(movieGenre);
	  	}
		  return genreList;
	}
			
	}

	

