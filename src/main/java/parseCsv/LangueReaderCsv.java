package parseCsv;

import dao.LangueDao;
import entity.Langue;
import utils.JpaConnection;

public abstract class LangueReaderCsv {

	public static final LangueDao langueDao = JpaConnection.langueDao();

	public static Langue langueExistOrAdded(String langueReceive) {

		Langue langue = langueDao.findByName(langueReceive.trim());
		if (langue == null) {
			langue = new Langue(langueReceive.trim());
			langueDao.insert(langue);
		}

		return langue;
	}

}
