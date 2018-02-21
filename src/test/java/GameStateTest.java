
import static org.junit.Assert.*;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

import java.util.Scanner;

public class GameStateTest {

    @Rule
    public final TextFromStandardInputStream systemInMock = emptyStandardInputStream();

    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

    @Test
    public void wordShowTest() {
        CommandOpts opts = new CommandOpts(new String[]{});
        Words words = new Words();
        GameState gameState = new GameState("England", 10, 2);
        gameState.guessedChars.add('E');
        gameState.guessedChars.add('a');
        gameState.showWord();
        assertEquals("E---a--\n", systemOutRule.getLog());
    }

   @Test
    public void noHintsLeftTest() {
       CommandOpts opts = new CommandOpts(new String[]{});
       Words words = new Words();
       GameState gameState = new GameState("England", 10, 0);
       systemInMock.provideLines("No more hints allowed\n");
       gameState.hint();
       assertEquals("No more hints allowed\n", systemOutRule.getLog());
   }

   @Test
    public void gameWonTest() {
       CommandOpts opts = new CommandOpts(new String[]{});
       Words words = new Words();
       GameState gameState = new GameState("England", 10, 0);
       gameState.charsToGuess.clear();
       assertTrue(gameState.won());
   }

   @Test
    public void gameLostTest() {
       CommandOpts opts = new CommandOpts(new String[]{});
       Words words = new Words();
       GameState gameState = new GameState("England", 10, 0);
       gameState.guessesLeft = 0;
       assertTrue(gameState.lost());
   }



}
