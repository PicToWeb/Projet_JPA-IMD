package service.importFile;

import java.util.List;

import dao.RoleDao;
import entity.Role;
import parseCsv.RoleReaderCsv;
import service.connection.DaoLink;

/**
 * Abstract class used to manage role file
 */
public abstract class FileRole {

	/** DAO instance for managing roles */
	public static final RoleDao ROLE_DAO = DaoLink.roleDao();

	/**
	 * Reads and parses a CSV file from the specified URL and its dependent URL.
	 *
	 * @param url    The URL of the main CSV file.
	 * @param urlDep The URL of the dependent CSV file.
	 * @return A list of Role objects parsed from the CSV.
	 */
		public static List<Role> link(String url, String urlDep) {
		return RoleReaderCsv.readFile(url, urlDep);
	}

	/**
	 * Adds the parsed role data to the database.
	 *
	 * @param roleList The list of Role objects to add.
	 */
	public static void addCsvToDataBase(List<Role> roleList) {
		ROLE_DAO.allInsert(roleList);
		System.out.println("Role insert : DONE");
	}

}
