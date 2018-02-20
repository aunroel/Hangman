
import sun.text.normalizer.UTF16;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class Words {

	static String[] regionsArray = { "Argyll and Bute", "Caithness",  "Kingdom of Fife",
			            "East Lothian", "Highland", "Dumfries and Galloway",
			            "Renfrewshire", "Scottish Borders", "Perth and Kinross" };

	static String[] countriesArray = { "Scotland", "England", "Wales", "Northern Ireland", "Ireland",
			            "France", "Germany", "Netherlands", "Spain", "Portugal",
			            "Belgium", "Luxembourg", "Switzerland", "Italy", "Greece" };

	static String[] citiesArray = { "St Andrews", "Edinburgh", "Glasgow", "Kirkcaldy", "Perth",
			            "Dundee", "Stirling", "Inverness", "Aberdeen", "Falkirk" };
			
	static ArrayList<String> wordListFromFile;
	
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
		FileReader file = null;
		BufferedReader reader = null;
		wordListFromFile = new ArrayList<String>();

		try {
			file = new FileReader(wordsource);
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
					return  "";
				}
			}
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					System.out.println("IO reader closing error");
					return  "";
				}
			}
			System.out.println("File successfully closed");
		}
	}
}
