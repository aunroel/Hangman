import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class WordsTest {

    @Test
    public void randomDefaultWordTest() {
        Words words = new Words();
        String word = words.randomWord(Words.getCOUNTRIES());
        assertTrue(Arrays.asList(words.getCountriesArray()).contains(word));
    }

    @Test
    public void randomExternalWordTest() {
        Words words = new Words();
        String country = words.randomWord("words.txt");
        assertTrue(Arrays.asList(words.getWordListFromFile()).contains(country));
    }

    @Test
    public void noFileToReadTest() {
        Words words = new Words();
        String country = words.randomWord("dfgh.txt");
        assertNotEquals("", country);
    }

    @Test
    public void emptyFileTest() {
        Words words = new Words();
        String country = words.randomWord("empty.txt");
        assertNotEquals("", country);
    }
}
