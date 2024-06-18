package parseCsv;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.FileUtils;

import dao.RealisateurDao;
import entity.Film;
import entity.Realisateur;
import utils.JpaConnection;

public abstract class MovieRealReaderCsv {

//	public static final RealisateurDao realisateurDao = JpaConnection.realisateurDao();
//	
//	public static HashMap<String,String> readFileToMap(String urlFile) throws IOException {
//		
//		HashMap<String, String> producerMoviesMap = new HashMap<>();
//		List<String> linesProducerList = null;
//		
//		File fileProdMov = new File(urlFile);
//		linesProducerList = FileUtils.readLines(fileProdMov, "UTF-8");
//		linesProducerList.remove(0);
//		
//		for (String prodMovieData : linesProducerList) {
//			String[] column = prodMovieData.split(";");
//			producerMoviesMap.put(column[0], column[1]);
//		}
//		
//		return producerMoviesMap;
//	}
//	
//	public static Film findProducerForMovie(HashMap<String,String> producerMoviesMap,Film film) {
//		
//		Realisateur realisateur = realisateurDao.findById(producerMoviesMap.values().iterator().next());
//
//		Iterator<String> keyReal = producerMoviesMap.keySet().iterator();
//		while(keyReal.hasNext()) {
//			String cleJoin = keyReal.next();
//			
//			if(film.getId().equals(cleJoin) && realisateur != null) {
//			
//				film.addProducer(realisateur);
//			}
//		}
//		return film;
//		
//	}
}
