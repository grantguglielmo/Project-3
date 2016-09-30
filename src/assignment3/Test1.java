/* WORD LADDER Test1.java
 * EE422C Project 3 submission by
 * Grant Guglielmo
 * gg25488
 * Stephen Ma
 * szm99
 * Slip days used: 0
 * Git URL: https://github.com/grantguglielmo/Project-3/
 * Fall 2016
 */
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
