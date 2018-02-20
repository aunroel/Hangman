import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Random;

public class Words {

	private static String[] regionsArray = { "Argyll and Bute", "Caithness",  "Kingdom of Fife",
			            "East Lothian", "Highland", "Dumfries and Galloway",
			            "Renfrewshire", "Scottish Borders", "Perth and Kinross" };

	private static String[] countriesArray = { "Scotland", "England", "Wales", "Northern Ireland", "Ireland",
			            "France", "Germany", "Netherlands", "Spain", "Portugal",
			            "Belgium", "Luxembourg", "Switzerland", "Italy", "Greece" };

	private static String[] citiesArray = { "St Andrews", "Edinburgh", "Glasgow", "Kirkcaldy", "Perth",
			            "Dundee", "Stirling", "Inverness", "Aberdeen", "Falkirk" };
			
	private static ArrayList<String> wordListFromFile;
	
	public static String randomWord(int category) {
		Random rand = new Random();
		switch (category) {
			case 1:	return regionsArray[rand.nextInt(regionsArray.length)];
			case 2:	return countriesArray[rand.nextInt(countriesArray.length)];
			case 3:	return citiesArray[rand.nextInt(citiesArray.length)];

			default: return null;
		}
	}
	
	public static String randomWord(String wordsource) {
		String line;
		InputStreamReader file = null;
		BufferedReader reader = null;
		wordListFromFile = new ArrayList<>();

		try {
			file = new InputStreamReader(new FileInputStream(wordsource), "UTF-8");
			reader = new BufferedReader(file);

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
		} finally {
			if (file != null) {
				try {
					file.close();
				} catch (IOException e) {
					System.out.println("IO file closing error");
				}
			}
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					System.out.println("IO reader closing error");
				}
			}
			System.out.println("File successfully closed");
		}
	}
}
