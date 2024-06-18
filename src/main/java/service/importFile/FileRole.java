package service.importFile;

import java.io.IOException;
import java.util.List;

import dao.RoleDao;
import entity.Role;
import parseCsv.RoleReaderCsv;
import utils.FileSource;
import utils.JpaConnection;

public abstract class FileRole {
	
	public static final RoleDao roleDao = JpaConnection.roleDao();
	
	public static List<Role> link(String url) throws IOException {
		return RoleReaderCsv.readFileToList(FileSource.nom(url));
	}

	public static void addCsvToDataBase(List<Role> roleList) {
		roleList.stream().forEach(roleDao::insert);
	}

}
