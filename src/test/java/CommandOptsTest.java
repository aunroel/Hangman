
import static org.junit.Assert.*;

import org.junit.Test;

public class CommandOptsTest {

	@Test
	public void noArgsTest(){
		CommandOpts opts = new CommandOpts(new String[]{});
		assertEquals(10, opts.getMaxGuesses());
		assertEquals(2, opts.getMaxHints());
		assertEquals("", opts.getFileWithCustomWords());
	}

	@Test
	public void optionsTest() {
		String[] args = { "--guesses", "2", "--hints", "4", "words.txt" };
		CommandOpts opts = new CommandOpts(args);
		assertEquals(2, opts.getMaxGuesses());
		assertEquals(4, opts.getMaxHints());
		assertEquals("words.txt", opts.getFileWithCustomWords());
	}

}
