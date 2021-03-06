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
    public static Set<String> visitedWords;
    public static Set<String> dict;
    public static Set<String> visitedDFS;
    public static HashMap<String, HashSet<String>> graph;
    public static boolean noLadder = false;
    public static Stack<String> stack;
    public static String endWord;
    public static boolean flag;
    public static String input1;
    public static String input2;

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
        input1 = input.get(0);
        input2 = input.get(1);
        ArrayList<String> dfsTest = getWordLadderDFS(input.get(0), input.get(1));
        printLadder(dfsTest);
        ArrayList<String> myladder = getWordLadderBFS(input.get(0), input.get(1));
        printLadder(myladder);
    }

    /**
     * initialize all of our static variables
     */
    public static void initialize() {
        // initialize your static variables or constants here.
        // We will call this method before running our JUNIT tests. So call it
        // only once at the start of main.
        noLadder = false;
        root = new Node<String>();
        dict = makeDictionary();
        visitedWords = new HashSet<String>(0);
        queue = new ArrayList<Queue>(0);
        stack = new Stack<String>();
    }

    /**
     * This method makes an adjacency list out of the 5 word dictionary.
     * It is a HashMap that takes Strings and returns a set of all words one letter off.
     *
     * @return the adjacency list HashMap<String, HashSet<String>> graph()
     */
    public static HashMap<String, HashSet<String>> graph() {
        HashMap<String, HashSet<String>> adjacencyList = new HashMap<String, HashSet<String>>();
        for (String s : dict) {
            char[] c = s.toCharArray();
            for (int j = 0; j < 5; j++) {
                for (int i = 0; i < 26; i++) {
                    if (c[j] == 'Z') {
                        c[j] = 'A';
                    } else {
                        c[j]++;
                    }
                    String temp = String.valueOf(c);
                    if (dict.contains(temp) && !temp.equals(s)) {
                        if (!adjacencyList.containsKey(s)) {
                            adjacencyList.put(s, new HashSet<String>());
                        }
                        adjacencyList.get(s).add(temp);
                    }
                }
            }
        }
        return adjacencyList;
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
			System.exit(0);
		}
		inputList.add(input);
		inputList.add(keyboard.next());
		inputList.set(0, inputList.get(0).toUpperCase());
		inputList.set(1, inputList.get(1).toUpperCase());
		return inputList;
	}

    /**
     * Implements the depth first search algorithm to find a word ladder
     * between two different words.
     *
     * @param start is the first word you start with
     * @param end   is the word you want to end the ladder with
     * @return an ArrayList of the Ladder if there is one, null otherwise
     */
    public static ArrayList<String> getWordLadderDFS(String start, String end) {
        initialize();
        graph = graph();
        visitedDFS = new HashSet<String>();
        endWord = end;
        flag = false;
        boolean bool = find(start);
        if (flag) {
            endWord = start;
            flag = false;
            bool = find(end);
            if (bool) {
                ArrayList<String> returnThis = new ArrayList<String>(stack);
                Collections.reverse(returnThis);
                return returnThis;
            }
        }
        if (!bool) {
            noLadder = true;
            return null;
        }
        ArrayList<String> returnThis = new ArrayList<String>(stack);
        return returnThis;
    }

    /**
     * This is the recursive function called by the dfs handler
     *
     * @param start the word you want to find
     * @return true or false depending on if there is a tree
     */
    public static boolean find(String start) {
        try {
            if (start == null) {
                stack.pop();
                return false;
            }
            visitedDFS.add(start);
            stack.push(start);
            if (start.equals(endWord)) {
                return true;
            } else if (graph.get(start) != null) {
                if (graph.get(start).contains(endWord)) {
                    stack.push(endWord);
                    return true;
                }
                String best = bestWord(start);
                if (best != null && !visitedDFS.contains(best)) {
                    // recursive call here
                    boolean found = find(best);
                    if (found) {
                        return true;
                    }
                }
                for (String s : graph.get(start)) {
                    if (!visitedDFS.contains(s)) {
                        // recursive call here
                        boolean found = find(s);
                        if (found) {
                            return true;
                        }
                    }
                }
                stack.pop();
                return false;
            } else {
                return false;
            }
        } catch (StackOverflowError e) {
            flag = true;
            return false;
        }
    }

    /**
     * Some optimization for the DFS
     * @param start the word we are trying to optimize the next guess for
     * @return the best word
     */
    public static String bestWord(String start) {
        char[] b = endWord.toCharArray();
        int count;
        for (String s : graph.get(start)) {
            count = 0;
            char[] a = s.toCharArray();
            for (int i = 0; i < 5; i++) {
                if (a[i] == b[i]) {
                    count++;
                }
            }
            if (count == 3) {
                return s;
            }
        }
        return null;
    }

    /**
     * find a word ladder between start and end, using breadth first search
     * method
     *
     * @param start starting word
     * @param end   ending word
     * @return word ladder between start-end
     */
    public static ArrayList<String> getWordLadderBFS(String start, String end) {
        if (start.equals(end)) {// trivial case
            ArrayList<String> ladder = new ArrayList<String>(0);
            String rung = start.toLowerCase();
            ladder.add(rung);
            rung = end.toLowerCase();
            ladder.add(rung);
            return ladder;
        }
        initialize();
        root.data = start;
        Queue block = new Queue(start, root);
        queue.add(block);
        for (int i = 0; i < queue.size(); i++) {// continue loop while queue has
            // elements unchecked
            block = queue.get(i);// get next word
            if (block.word.equals(end)) {// end if end word found
                ArrayList<String> ladder = buildLadder(block.node);
                Collections.reverse(ladder);
                return ladder;
            }
            nextWords(block.node);// continue adding mutated words to queue
        }
        noLadder = true;
        return null;
    }

    /**
     * build the word ladder in an array for a given node
     *
     * @param node ending word to build ladder from
     * @return word ladder
     */
    public static ArrayList<String> buildLadder(Node<String> node) {
        ArrayList<String> ladder = new ArrayList<String>(0);
        while (node != null) {
            String rung = node.data.toLowerCase();
            ladder.add(rung);
            node = node.parent;
        }
        return ladder;
    }

    /**
     * add onto the queue all the legal perumutations of the given word
     *
     * @param root given words node
     * @return true if >0 perumtations exist
     */
    public static boolean nextWords(Node<String> root) {
        char[] word = root.data.toCharArray();
        visitedWords.add(root.data);
        int len = 0;
        for (int i = 0; i < root.data.length(); i++) {// go through all the
            // letters of the word
            for (int j = 0; j < 26; j++) {// and try every other letter of the
                // alphabet
                if (word[i] == 'Z') {// to see if any mutations of the word
                    // exist
                    word[i] = 'A';
                } else {
                    word[i]++;
                }
                String chkword = String.valueOf(word);
                if (dict.contains(chkword) && !visitedWords.contains(chkword)) {// check
                    // if
                    // newly
                    // created
                    // word
                    // is
                    // in
                    // the
                    // dictionary
                    // or
                    // not
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

    /**
     * print the given word ladder
     *
     * @param ladder array of strings that is the ladder to be printed
     */
    public static void printLadder(ArrayList<String> ladder) {
        if (ladder == null) {// static variable set to true if no ladder was found
            System.out.println("no word ladder can be found between " + input1.toLowerCase() + " and "
                    + input2.toLowerCase() + ".");
            return;
        }
        System.out.println("a " + (ladder.size() - 2) + "-rung word ladder exists between "
                + ladder.get(0).toLowerCase() + " and " + ladder.get(ladder.size() - 1).toLowerCase() + ".");
        for (int i = 0; i < ladder.size(); i++) {// print out words, they are
            // stored in reverse order
            System.out.println(ladder.get(i).toLowerCase());
        }
    }
}
