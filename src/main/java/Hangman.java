
import java.util.InputMismatchException;
import java.util.Scanner;

public class Hangman {

	private static Words wordsObj;
	private static GameState game;
	private static CommandOpts opts;
	private static Scanner sc;

	public static void main(String[] args) {
		sc = new Scanner(System.in, "UTF-8");
		wordsObj = new Words();
		opts = new CommandOpts(args);
		boolean correct;

		parseUserInput();

		while(!game.won() && !game.lost()) {
			game.showWord();
			
			System.out.println("Guesses remaining: " + game.guessesLeft);

			correct = game.guessLetter();
			
			if (correct)
				System.out.println("Good guess!");
			if (!correct)
				System.out.println("Wrong guess!");
		}
		
		if (game.won()) {
			System.out.println("'" + game.wordToGuess + "', Well done!");
			System.out.println("You took " + game.guessesMade + " guesses");
			sc.close();
		} else {
			System.out.println("You lost! The word was " + game.wordToGuess);
			sc.close();
		}
	}

	private static void parseUserInput() {
		if (!opts.getFileWithCustomWords().equals("")) {
			game = new GameState(wordsObj.randomWord(opts.getFileWithCustomWords()), opts.getMaxGuesses(), opts.getMaxHints());
		} else {
			System.out.println(" 1. Counties");
			System.out.println(" 2. Countries");
			System.out.println(" 3. Cities");
			System.out.print("Pick a number: ");

			String input;
			int category;

			do {
				try {
					input = sc.nextLine();
					category = Integer.parseInt(input);
					if (category > 0 && category < 4) {
						break;
					} else {
						System.out.println("Choose one of 3 categories (enter a number): ");
						continue;
					}
				} catch (InputMismatchException e) {
					System.out.println("You need to pick a number: ");
				} catch (NumberFormatException e) {
					System.out.println("Invalid option made, try again: ");
				}
			}while (true);

			game = new GameState(wordsObj.randomWord(category), opts.getMaxGuesses(), opts.getMaxHints());
		}
	}

}
