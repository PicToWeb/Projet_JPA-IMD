package Application;

import java.util.Scanner;

import dao.MovieDao;
import dao.RoleDao;
import service.connection.DaoLink;

public abstract class ReadData {
	
	public static final MovieDao MOVIE_DAO = DaoLink.movieDao();
	public static final RoleDao ROLE_DAO = DaoLink.roleDao();
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int userSelected;
		do {
			userSelected=menuChoice(input);
			switch(userSelected) {
			case 1 :
				System.out.println("Witch actor ? ");
				MOVIE_DAO.findMovieOfActor(input.nextLine()).forEach(System.out::println);
				break;
			case 2 : 
				System.out.println("Witch Movie ? ");
				ROLE_DAO.findCasting(input.nextLine()).forEach(System.out::println);
				break;
			case 3 :
				System.out.println("Year 1 ? ");
				int year1 = input.nextInt();
				System.out.println("Year 2 ? ");
				int year2 = input.nextInt();
				MOVIE_DAO.findMovieBetweenDate(year1,year2).forEach(System.out::println);
				break;
			case 4 :
				break;
			case 5 :
				break;
			case 6 :
				break;
			case 7 : 
				break;
			default : 
				break;
			}
		}
		while(userSelected > 7);
		
	}
	
	
	public static int menuChoice(Scanner input)
	{
			System.out.println("Select you option: ");
			System.out.println("---------------------");
			System.out.println("1 - Affichage de la filmographie d’un acteur donné");
			System.out.println("2 - Affichage du casting d’un film donné");
			System.out.println("3 - Affichage des films sortis entre 2 années données");
			System.out.println("4 - Affichage des films communs à 2 acteurs/actrices donnés");
			System.out.println("5 - Affichage des acteurs communs à 2 films donnés");
			System.out.println("6 - Affichage des films sortis entre 2 années données et qui ont un acteur/actrice donné au casting");
			System.out.println("7 - Trouver le plus court chemin entre 2 acteurs");
			System.out.println("8 - Quitter");
		return Integer.parseInt(input.nextLine());
	}

}
