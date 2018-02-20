
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class GameState {
	public String wordToGuess;
	public int guessesMade;
	public int wrong;
	public int hintsLeft;
	
	ArrayList<Character> guessedCharsFromWord;
	ArrayList<Character> charsToBeGuessed;
	
	public Scanner sc = new Scanner(System.in, "UTF-8").useDelimiter("\n");
	
	public GameState(String wordToGuess, int maxGuesses, int maxHints) {
		this.wordToGuess = wordToGuess;
		charsToBeGuessed = new ArrayList<>();
        guessedCharsFromWord = new ArrayList<>();
		
		for(int i = 0; i < wordToGuess.length(); ++i) {
			if (!charsToBeGuessed.contains(Character.toLowerCase(wordToGuess.charAt(i))))
			charsToBeGuessed.add(Character.toLowerCase(wordToGuess.charAt(i)));
		}
		
		this.guessesMade = 0;
		wrong = maxGuesses;
		this.hintsLeft = maxHints;
	}
	
	void showWord() {
		for (int i = 0; i < wordToGuess.length(); ++i) {
			if (guessedCharsFromWord.contains(wordToGuess.charAt(i))) {
				System.out.print(wordToGuess.charAt(i));
			} else {
				System.out.print("-");
			}
		}
		System.out.println("");
		// System.out.println(missing);
	}
	
	boolean guessLetter() {
		int i;
		char letter;
		
		System.out.print("Guess a letter or wordToGuess (? for a hint): ");
		
		String str = sc.next().toLowerCase();
		
		if (str.length() > 1) {
			if (str.equals(wordToGuess)) {
				charsToBeGuessed.clear();
				return true;
			} else return false;
		}
		
		letter = str.charAt(0);
		
		if (letter == '?') {
			hint();
			return false;
		}
		
		for(i = 0; i < charsToBeGuessed.size(); ++i) { // Loop over the charsToBeGuessed guessedCharsFromWord
			if (charsToBeGuessed.get(i) == letter) {
				charsToBeGuessed.remove(i);
				guessedCharsFromWord.add(letter);
				guessesMade++;
				return true;
			}
		}

		guessesMade++; // One more guess
		wrong--;
		return false;
	}
	
	boolean won() {
        return charsToBeGuessed.size() == 0;
	}

	boolean lost() {
		return (charsToBeGuessed.size() > 0 && wrong == 0);
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
        System.out.print(charsToBeGuessed.get(rand.nextInt(charsToBeGuessed.size())));
        System.out.println(" (hints left: " + hintsLeft + ")");
    }
}
