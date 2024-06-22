package service.importFile;

import java.util.List;


import dao.RoleDao;
import entity.Role;
import parseCsv.RoleReaderCsv;
import service.connection.DaoLink;

public abstract class FileRole {

	/** roleDao */
	public static final RoleDao roleDao = DaoLink.roleDao();

	/**
	 * @param url
	 * @param urlDep
	 * @return
	 */
	public static List<Role> link(String url, String urlDep) {
		return RoleReaderCsv.readFile(url, urlDep);
	}

	/**
	 * @param roleList
	 */
	public static void addCsvToDataBase(List<Role> roleList) {
		roleDao.allInsert(roleList);	
		System.out.println("Role insert : DONE");
	}

}
