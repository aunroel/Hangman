public class CommandOpts {
	public static final int DEFAULT_MAX_GUESSES = 10;
	public static final int DEFAULT_MAX_HINTS = 2;
	public static final int MIN_ARGS_AMOUNT = 2;

	private int maxGuesses;
	private int maxHints;

    private String fileWithCustomWords;

    public CommandOpts(final String[] args) {
		maxGuesses = DEFAULT_MAX_GUESSES;
		maxHints = DEFAULT_MAX_HINTS;
		fileWithCustomWords = "";

		if (args.length >= MIN_ARGS_AMOUNT) {
			parseArgs(args);
		}
	}

	private void parseArgs(final String[] args) {
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
