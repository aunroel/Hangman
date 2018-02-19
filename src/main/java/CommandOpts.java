public class CommandOpts {
	public static final int DEFAULT_MAX_GUESSES = 10;
	public static final int DEFAULT_MAX_HINTS = 2;

	public int maxGuesses;
	public int maxHints;
	
	String fileWithCustomWords;
	
	CommandOpts(String[] args) {
		maxGuesses = DEFAULT_MAX_GUESSES;
		maxHints = DEFAULT_MAX_HINTS;
		fileWithCustomWords = "";

		for (int i = 0; i < args.length; ++i) {
			if (args[i].equals("--guesses")) {
				maxGuesses = Integer.parseInt(args[i + 1]);
				i++;
			} else if (args[i].equals("--hints")) {
				maxHints = Integer.parseInt(args[i + 1]);
				i++;
			} else {
				fileWithCustomWords = args[i];
			}
		}
	}
}
