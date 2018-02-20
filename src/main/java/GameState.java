
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class GameState {
	public String word;
	public int guessesAmount;
	public int wrong;
	public int hintsLeft;
	
	ArrayList<Character> guessedCharsFromWord;
	ArrayList<Character> charsToBeGuessed;
	
	public Scanner sc = new Scanner(System.in, "UTF-8").useDelimiter("\n");
	
	public GameState(String target, int guessesAmount, int hintsLeft) {
		this.word = target;
		charsToBeGuessed = new ArrayList<>();
        guessedCharsFromWord = new ArrayList<>();
		
		for(int i = 0; i < target.length(); ++i) {
			if (!charsToBeGuessed.contains(Character.toLowerCase(target.charAt(i))))
			charsToBeGuessed.add(Character.toLowerCase(target.charAt(i)));
		}
		//System.out.println(missing);
		
		this.guessesAmount = 0;
		wrong = guessesAmount;
		this.hintsLeft = hintsLeft;
	}
	
	void showWord() {
		for (int i = 0; i < word.length(); ++i) {
			if (guessedCharsFromWord.contains(word.charAt(i))) {
				System.out.print(word.charAt(i));
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
		
		System.out.print("Guess a letter or word (? for a hint): ");
		
		String str = sc.next().toLowerCase();
		
		if (str.length() > 1) {
			if (str.equals(word)) {
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
				guessesAmount++;
				return true;
			}
		}

		guessesAmount++; // One more guess
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
		}
		
		System.out.print("Try: ");
		System.out.println(charsToBeGuessed.get(rand.nextInt(charsToBeGuessed.size())));
	}
}
