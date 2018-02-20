
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

	@Test
	public void invalidCommandTest() {
		String[] args = {"gbj13ds"};
		CommandOpts opts = new CommandOpts(args);
		assertEquals("", opts.getFileWithCustomWords());
	}

	@Test
	public void invalidArgumentsTest() {
		String[] args = {"gbj13ds", "asd", "asd2123/./!"};
		CommandOpts opts = new CommandOpts(args);
		assertEquals("", opts.getFileWithCustomWords());
	}

	@Test
	public void invalidArgAfterValidCommandTest() {
		String[] args = { "--guesses", "asd", "--hints", "4.asd"};
		CommandOpts opts = new CommandOpts(args);
		assertEquals(10, opts.getMaxGuesses());
		assertEquals(2, opts.getMaxHints());
		assertEquals("", opts.getFileWithCustomWords());
	}

	//TODO fix number parsing for arguments

}
