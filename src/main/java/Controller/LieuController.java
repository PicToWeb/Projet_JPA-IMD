package Controller;

import dao.LieuDao;
import entity.Lieu;
import entity.Pays;
import utils.JpaConnection;

public abstract class LieuController {
	
public static final LieuDao lieuDao = JpaConnection.lieuDao();
	
	public static Lieu checkIfLieuExist(Lieu lieu) {
		 
		
		//if(lieu==null) return new Lieu("","","",new Pays("",""));
		
		
		if(lieuDao.findByName(lieu)==null)
		{
			//lieuDao.insert(new Lieu (lieu.getRue(),lieu.getVille(),lieu.getEtat(),lieu.getPays()));
			
		}
			
		
		return lieuDao.findByName(lieu);
	}

}
