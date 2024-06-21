package service.importFile;

import java.util.List;

import dao.RoleDao;
import entity.Role;
import parseCsv.RoleReaderCsv;
import service.connection.DaoLink;
import utils.ShowThis;

public abstract class FileRole {

	public static final RoleDao roleDao = DaoLink.roleDao();

	public static List<Role> link(String url, String urlDep) {
		return RoleReaderCsv.readFile(url, urlDep);
	}

	public static void addCsvToDataBase(List<Role> roleList) {
		roleDao.allInsert(roleList);	
		System.out.println("Role insert : DONE");
	}

}
