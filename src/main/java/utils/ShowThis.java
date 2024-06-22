package utils;

/**
 * 
 */
public abstract class ShowThis {

	
	/** Méthode qui concatène des informations de différentes natures (String, double, etc.) pour générer une String
	 * @param objets informations à concaténer
	 * @return String
	 */
	public static String toString(String separateur,Object... objets) {

		StringBuilder builder = new StringBuilder();
		for (Object obj : objets) {
			builder.append(obj).append(separateur);
		}
		return builder.toString();
	}
}
