/* WORD LADDER Queue.java
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
/**
 * queue class with the name of the word and its node
 * @author Grant
 *
 */
public class Queue {
	public String word;
	public Node<String> node; 
	/**
	 * constructor given word and its node
	 * @param word name of word
	 * @param node of word
	 */
	public Queue(String word, Node<String> node){
		this.word = word;
		this.node = node;
	}
	
}
