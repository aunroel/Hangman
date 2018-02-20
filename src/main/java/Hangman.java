
import java.util.Scanner;

public class Hangman {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in, "UTF-8");
		Words wordsObj = new Words();
		GameState game;
		CommandOpts opts;

		boolean correct;
		
		opts = new CommandOpts(args);
		
		if (opts.getFileWithCustomWords().equals("")) {
		
			System.out.println("  1. Counties");
			System.out.println("  2. Countries");
			System.out.println("  3. Cities");

			System.out.print("Pick a category: ");

		 	game = new GameState(wordsObj.randomWord(sc.nextInt()), opts.getMaxGuesses(), opts.getMaxHints());
		}else {
			game = new GameState(wordsObj.randomWord(opts.getFileWithCustomWords()), opts.getMaxGuesses(), opts.getMaxHints());
		}
		
		while(!game.won() && !game.lost()) {
			game.showWord();
			
			System.out.println("Guesses remaining: " + game.wrong);
			
			correct = game.guessLetter();
			
			if (correct)
				System.out.println("Good guess!");
			if (!correct)
				System.out.println("Wrong guess!");
		}
		
		if (game.won()) {
			System.out.println("Well done!");
			System.out.println("You took " + game.guessesMade + " guesses");
		} else {
			System.out.println("You lost! The wordToGuess was " + game.wordToGuess);
		}
	}

}
