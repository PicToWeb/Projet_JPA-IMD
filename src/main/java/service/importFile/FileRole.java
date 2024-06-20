package service.importFile;

import java.util.List;

import dao.RoleDao;
import entity.Role;
import parseCsv.RoleReaderCsv;
import service.connection.DaoLink;

public abstract class FileRole {

	public static final RoleDao roleDao = DaoLink.roleDao();

	public static List<Role> link(String url, String urlDep) {
		return RoleReaderCsv.readFile(url, urlDep);
	}

	public static void addCsvToDataBase(List<Role> roleList) {
		roleDao.allInsert(roleList);	
	}

}
