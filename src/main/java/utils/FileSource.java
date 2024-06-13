package utils;



public class FileSource {
	
	public static String nom(String source) {
		
		String filePath = ClassLoader.getSystemClassLoader().getResource(source).getFile();
		
		return filePath;
	}
	
	

}
