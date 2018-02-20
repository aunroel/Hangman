import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.util.Random;

public final class Words {

	private static final int REGIONS = 1;
	private static final int COUNTRIES = 2;
	private static final int CITIES = 3;

	private final String[] regionsArray = {"Argyll and Bute", "Caithness",  "Kingdom of Fife",
			            "East Lothian", "Highland", "Dumfries and Galloway",
			            "Renfrewshire", "Scottish Borders", "Perth and Kinross"};

	private final String[] countriesArray = {"Scotland", "England", "Wales", "Northern Ireland", "Ireland",
			            "France", "Germany", "Netherlands", "Spain", "Portugal",
			            "Belgium", "Luxembourg", "Switzerland", "Italy", "Greece"};

	private final String[] citiesArray = {"St Andrews", "Edinburgh", "Glasgow", "Kirkcaldy", "Perth",
			            "Dundee", "Stirling", "Inverness", "Aberdeen", "Falkirk"};
			
	private String[] wordListFromFile;
	
	public String randomWord(final int category) {
		Random rand = new Random();
		switch (category) {
			case REGIONS:	return regionsArray[rand.nextInt(regionsArray.length)];
			case COUNTRIES:	return countriesArray[rand.nextInt(countriesArray.length)];
			case CITIES:	return citiesArray[rand.nextInt(citiesArray.length)];

			default: return null;
		}
	}
	
	public String randomWord(final String wordSource) {
		Random rand = new Random();
		File file;

		try {
			ClassLoader classLoader = getClass().getClassLoader();
			file = new File(classLoader.getResource(wordSource).getFile());

			String fileAsString = new String(Files.readAllBytes(file.toPath()), "UTF-8");
			wordListFromFile = fileAsString.split("\n");

			if (!fileAsString.equals("")) {
				return wordListFromFile[rand.nextInt(wordListFromFile.length)];
			}

		} catch (NullPointerException e) {
			System.out.println("No file found");
		} catch (IOException e) {
			e.printStackTrace();
		}

		// return word from any default category picked randomly
		return randomWord(rand.nextInt(CITIES) + 1);
	}

	public static int getREGIONS() {
		return REGIONS;
	}

	public static int getCOUNTRIES() {
		return COUNTRIES;
	}

	public static int getCITIES() {
		return CITIES;
	}

	public String[] getRegionsArray() {
		return regionsArray.clone();
	}

	public String[] getCountriesArray() {
		return countriesArray.clone();
	}

	public String[] getCitiesArray() {
		return citiesArray.clone();
	}

	public String[] getWordListFromFile() {
		return wordListFromFile.clone();
	}
}
