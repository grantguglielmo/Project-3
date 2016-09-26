/* WORD LADDER Main.java
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

import java.util.*;
import java.io.*;

public class Main {

	// static variables and constants only here.
	public static Node<String> root;
	public static ArrayList<Queue> queue;
	public static ArrayList<String> visitedWords;
	public static Set<String> dict;
	public static boolean noLadder = false;

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

		ArrayList<String> myladder = getWordLadderBFS(input.get(0), input.get(1));
		printLadder(myladder);
	}

	public static void initialize() {
		// initialize your static variables or constants here.
		// We will call this method before running our JUNIT tests. So call it
		// only once at the start of main.
		noLadder = false;
		root = new Node<String>();
		dict = makeDictionary();
		visitedWords = new ArrayList<String>(0);
		queue = new ArrayList<Queue>(0);
	}

	/**
	 * @param keyboard
	 *            Scanner connected to System.in
	 * @return ArrayList of 2 Strings containing start word and end word. If
	 *         command is /quit, return empty ArrayList.
	 */
	public static ArrayList<String> parse(Scanner keyboard) {
		ArrayList<String> inputList = new ArrayList<String>(0);
		String input = keyboard.next();
		if (input.equals("/quit")) {
			return inputList;
		}
		inputList.add(input);
		inputList.add(keyboard.next());
		inputList.set(0, inputList.get(0).toUpperCase());
		inputList.set(1, inputList.get(1).toUpperCase());
		return inputList;
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
		if (start.equals(end)) {
			ArrayList<String> ladder = new ArrayList<String>(0);
			String rung = start.toLowerCase();
			ladder.add(rung);
			rung = end.toLowerCase();
			ladder.add(rung);
			return ladder;
		}
		root.data = start;
		Queue block = new Queue(start, root);
		queue.add(block);
		for (int i = 0; i < queue.size(); i++) {
			block = queue.get(i);
			if (block.word.equals(end)) {
				ArrayList<String> ladder = buildLadder(block.node);
				return ladder;
			}
			nextWords(block.node);
		}
		ArrayList<String> ladder = new ArrayList<String>(0);
		String lower = start.toLowerCase();
		ladder.add(lower);
		lower = end.toLowerCase();
		ladder.add(lower);
		noLadder = true;
		return ladder;
	}

	public static ArrayList<String> buildLadder(Node<String> node) {
		ArrayList<String> ladder = new ArrayList<String>(0);
		while (node != null) {
			String rung = node.data.toLowerCase();
			ladder.add(rung);
			node = node.parent;
		}
		return ladder;
	}

	public static boolean nextWords(Node<String> root) {
		char[] word = root.data.toCharArray();
		visitedWords.add(root.data);
		int len = 0;
		for (int i = 0; i < root.data.length(); i++) {
			for (int j = 0; j < 26; j++) {
				if (word[i] == 'Z') {
					word[i] = 'A';
				} else {
					word[i]++;
				}
				String chkword = String.valueOf(word);
				if (dict.contains(chkword) && !visitedWords.contains(chkword)) {
					Node<String> newnode = root.add(chkword);
					visitedWords.add(chkword);
					Queue block = new Queue(chkword, newnode);
					queue.add(block);
					len++;
				}
			}
		}
		if (len == 0) {
			return false;
		}
		return true;
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
		if (noLadder) {
			System.out.println("no word ladder can be found between " + ladder.get(0) + " and "
					+ ladder.get(1) + ".");
			return;
		}
		System.out.println("a " + (ladder.size() - 2) + "-rung word ladder exists between " + ladder.get(ladder.size() - 1) + " and "
				+ ladder.get(0) + ".");
		for (int i = ladder.size() - 1; i >= 0; i--) {
			System.out.println(ladder.get(i));
		}
	}
}
