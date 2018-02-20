
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class GameState {
	public String wordToGuess;
	public int guessesMade;
	public int guessesLeft;
	public int hintsLeft;
	
	ArrayList<Character> guessedChars;
	ArrayList<Character> charsToGuess;
	ArrayList<Character> charsForHints;
	
	public Scanner sc = new Scanner(System.in, "UTF-8").useDelimiter("\n");
	
	public GameState(String wordToGuess, int maxGuesses, int maxHints) {
		this.wordToGuess = wordToGuess;
		charsToGuess = new ArrayList<>();
        guessedChars = new ArrayList<>();

		
		for(int i = 0; i < wordToGuess.length(); ++i) {
			if (!charsToGuess.contains(wordToGuess.charAt(i)))
				charsToGuess.add(wordToGuess.charAt(i));
		}
		charsForHints = new ArrayList<>(charsToGuess);

		this.guessesMade = 0;
		guessesLeft = maxGuesses;
		this.hintsLeft = maxHints;
	}
	
	void showWord() {
		for (int i = 0; i < wordToGuess.length(); ++i) {
			if (guessedChars.contains(wordToGuess.charAt(i))) {
				System.out.print(wordToGuess.charAt(i));
			} else {
				System.out.print("-");
			}
		}
		System.out.println("");
	}
	
	boolean guessLetter() {
		char letter;
		
		System.out.print("Guess a letter or word (? for a hint): ");
		
		String str = sc.nextLine().toLowerCase();

		if (str.equals("")) {
			System.out.println("Enter a valid letter/word");
			return guessLetter();
		}

		if (str.length() > 1) {
			if (str.equals(wordToGuess.toLowerCase())) {
				charsToGuess.clear();
				charsForHints.clear();
				return true;
			} else {
				guessesMade++;
				guessesLeft--;
				return false;
			}
		}

		letter = str.charAt(0);

		if (letter == '?') {
			hint();
			return guessLetter();
		}

		for(int i = 0; i < charsToGuess.size(); i++) { // Loop over the charsToGuess guessedChars
			if (charsToGuess.get(i) == letter) {
				charsToGuess.remove(i);
				guessedChars.add(letter);
				guessesMade++;

				if (charsForHints.contains(letter)) {
					charsForHints.remove(letter);
				}

				return true;
			}
		}

		guessesMade++; // One more guess
		guessesLeft--;
		return false;
	}
	
	boolean won() {
        return charsToGuess.size() == 0;
	}

	boolean lost() {
		return (charsToGuess.size() > 0 && guessesLeft == 0);
	}

	private void hint() {
	    Random rand = new Random();
		if (hintsLeft == 0) {
			System.out.println("No more hints allowed");
			return;
		}

        if (hintsLeft > 0){
			hintsLeft--;
		}
        System.out.print("Try: ");
        Character hint = charsForHints.get(rand.nextInt(charsForHints.size()));
        System.out.print(charToLowerCase(hint));
        charsForHints.remove(hint);
        System.out.println(" (hints left: " + hintsLeft + ")");
    }

    private char charToLowerCase(char ch) {
		return Character.toLowerCase(ch);
	}
}
