public class CommandOpts {

	public static final int DEFAULT_MAX_GUESSES = 10;
	public static final int DEFAULT_MAX_HINTS = 2;

	private String fileWithCustomWords;
	private int maxGuesses;
	private int maxHints;

    public CommandOpts(final String[] args) {
		maxGuesses = DEFAULT_MAX_GUESSES;
		maxHints = DEFAULT_MAX_HINTS;
		fileWithCustomWords = "";

		if (args.length > 0) {
			parseArgs(args);
		}
	}

	private void parseArgs(final String[] args) {
    	int numericArg;
		for (int i = 0; i < args.length; ++i) {
			if (args[i].equals("--guesses") && (i + 1) < args.length) {
				numericArg = validateInput(maxGuesses, args[i + 1]);
				maxGuesses = Math.abs(numericArg);
				i++;
			} else if (args[i].equals("--hints") && (i + 1) < args.length) {
				numericArg = validateInput(maxHints, args[i + 1]);
				maxHints = Math.abs(numericArg);
				i++;
			} else if (args[i].endsWith(".txt")) {
				fileWithCustomWords = args[i];
			}
		}
	}

	private int validateInput(final int varToChange, final String line) {
    	int temp = varToChange;
    	try {
			temp = Integer.parseInt(line);
		} catch (NumberFormatException e) {
			System.err.println("Invalid number passed");
		}
		return temp;
	}

    public final int getMaxGuesses() {
        return maxGuesses;
    }

    public final int getMaxHints() {
        return maxHints;
    }

    public final String getFileWithCustomWords() {
        return fileWithCustomWords;
    }

}
