/* WORD LADDER Main.java
 * EE422C Project 3 submission by
 * Grant Guglielmo
 * gg25488
 * <Student1 5-digit Unique No.>
 * <Student2 Name>
 * <Student2 EID>
 * <Student2 5-digit Unique No.>
 * Slip days used: <0>
 * Git URL:
 * Fall 2016
 */

package assignment3;

import java.util.*;
import java.io.*;

public class Main {

	// static variables and constants only here.
	public static ArrayList<String> visitedwords;
	public static Set<String> dict;

	public static void main(String[] args) throws Exception {

		Scanner kb; // input Scanner for commands
		PrintStream ps; // output file
		// If arguments are specified, read/write from/to files instead of Std
		// IO.
		if (args.length != 0) {
			kb = new Scanner(new File(args[0]));
			ps = new PrintStream(new File(args[1]));
			System.setOut(ps); // redirect output to ps
		} else {
			kb = new Scanner(System.in);// default from Stdin
			ps = System.out; // default to Stdout
		}
		initialize();
		ArrayList<String> input = parse(kb);
		if (input.size() == 0) {
			return;
		}
		// ArrayList<String> BFSladder =
		// getWordLadderBFS(input.get(0),input.get(1));
	}

	public static void initialize() {
		// initialize your static variables or constants here.
		// We will call this method before running our JUNIT tests. So call it
		// only once at the start of main.
		dict = makeDictionary();
		visitedwords = new ArrayList<String>(0);
	}

	/**
	 * @param keyboard
	 *            Scanner connected to System.in
	 * @return ArrayList of 2 Strings containing start word and end word. If
	 *         command is /quit, return empty ArrayList.
	 */
	public static ArrayList<String> parse(Scanner keyboard) {
		ArrayList<String> inputlist = new ArrayList<String>(0);
		String input = keyboard.next();
		if (input.equals("/quit")) {
			return inputlist;
		}
		inputlist.add(input);
		inputlist.add(keyboard.next());
		inputlist.set(0, inputlist.get(0).toUpperCase());
		inputlist.set(1, inputlist.get(1).toUpperCase());
		return inputlist;
	}

	public static ArrayList<String> getWordLadderDFS(String start, String end) {

		// Returned list should be ordered start to end. Include start and end.
		// Return empty list if no ladder.
		// TODO some code
		Set<String> dict = makeDictionary();
		// TODO more code

		return null; // replace this line later with real return
	}

	public static ArrayList<String> getWordLadderBFS(String start, String end) {
		ArrayList<String> ladder = new ArrayList<String>(0);
		if (start.equals(end)) {
			ladder.add(end);
			return ladder;
		}
		visitedwords.add(start);
		ArrayList<String> words = new ArrayList<String>(0);
		char[] word = start.toCharArray();
		for (int i = 0; i < start.length(); i++) {
			for (int j = 0; j < 26; j++) {
				if (word[i] == 'Z') {
					word[i] = 'A';
				} else {
					word[i]++;
				}
				String chkword = word.toString();
				if (dict.contains(chkword)&&!visitedwords.contains(chkword)) {
					words.add(chkword);
				}
			}
		}
		if(words.size() == 0){
			return null;
		}
		for(int i = 0; i < words.size(); i++){
			
		}

		return null;
	}

	public static Set<String> makeDictionary() {
		Set<String> words = new HashSet<String>();
		Scanner infile = null;
		try {
			infile = new Scanner(new File("five_letter_words.txt"));
		} catch (FileNotFoundException e) {
			System.out.println("Dictionary File not Found!");
			e.printStackTrace();
			System.exit(1);
		}
		while (infile.hasNext()) {
			words.add(infile.next().toUpperCase());
		}
		return words;
	}

	public static void printLadder(ArrayList<String> ladder) {

	}
	// TODO
	// Other private static methods here
}