
import java.io.*;
import java.util.ArrayList;

public class Words {

	static String[] countiesArray = { "Argyll and Bute", "Caithness",  "Kingdom of Fife",
			            "East Lothian", "Highland", "Dumfries and Galloway",
			            "Renfrewshire", "Scottish Borders", "Perth and Kinross" };

	static String[] countriesArray = { "Scotland", "England", "Wales", "Northern Ireland", "Ireland",
			            "France", "Germany", "Netherlands", "Spain", "Portugal",
			            "Belgium", "Luxembourg", "Switzerland", "Italy", "Greece" };

	static String[] citiesArray = { "St Andrews", "Edinburgh", "Glasgow", "Kirkcaldy", "Perth",
			            "Dundee", "Stirling", "Inverness", "Aberdeen", "Falkirk" };
			
	static ArrayList<String> wordListFromFile;
	
	public static String randomWord(int category) {
		switch (category) {
			case 1:	return countiesArray[(int)(Math.random()*9)];
			case 2:	return countriesArray[(int)(Math.random()*15)];
			case 3:	return citiesArray[(int)(Math.random()*10)];

			default: return null;
		}
	}
	
	public static String randomWord(String wordsource) {
		String line;
		wordListFromFile = new ArrayList<String>();
		
		try {
			FileReader file = new FileReader(wordsource);
			BufferedReader reader = new BufferedReader(file);

			while((line = reader.readLine()) != null) {
                wordListFromFile.add(line);
            }
			return wordListFromFile.get((int)(Math.random()* wordListFromFile.size()));
		} catch(FileNotFoundException e) {
			System.out.println("File error");
			return "";
		} catch(IOException e) {
			System.out.println("IO error");
			return "";
		}
	}
}
