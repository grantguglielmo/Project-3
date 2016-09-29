package assignment3;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class Test1 {

	@Test
	public void test() {
		ArrayList<String> ladder = Main.getWordLadderDFS("SMART", "MONEY");
		Main.printLadder(ladder);
	}

}
