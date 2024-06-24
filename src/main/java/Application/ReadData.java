package Application;

import java.util.Scanner;
import service.searchData.SearchData;

/**
 * ReadData is an abstract class that provides methods for reading data.
 * It contains a menu-driven program to interact with different data search options.
 *
 * @author Antoine Picron
 */
public abstract class ReadData {
	
	/**
     * The main method that displays the menu and handles user input.
     *
     * @param args Command line arguments (not used in this case)
     */
	public static void main(String[] args) {
		
		int userSelected;
		boolean exit =false;
		
		while(!exit) {
			userSelected=menuChoice();
			
			switch(userSelected) {
			case 1 :
				SearchData.movieOfActor();
				break;
			case 2 : 
				SearchData.movieCasting();
				break;
			case 3 :
				SearchData.movieBetweenTwoYear();
				break;
			case 4 :
				SearchData.communMoviesOfTwoActors();
				break;
			case 5 :
				SearchData.communActorsOfTwoMovies();
				break;
			case 6 :
				SearchData.movieBetweenTwoYearFromActor();
				break;
			case 7 : 
				break;
			case 8 : 
				exit = true;
				SearchData.input.close();
				break;
			}
		}
		
	}
	
	 /**
     * Displays the menu options and returns the user's choice.
     *
     * @return The selected menu option
     */
	public static int menuChoice()
	{
		Scanner input = SearchData.input;
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
