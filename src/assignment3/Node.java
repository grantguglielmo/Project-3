/* WORD LADDER Node.java
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

import java.util.ArrayList;
/**
 * tree class to trace a word back to its parents
 * @author Grant
 *
 */
public class Node<T> {
    public Node<T> parent;//nodes parent
    public T data;//data for node
    public ArrayList<Node<T>> children;//list of all nodes children
    /**
     * constructor for root
     * @param data stored in data for node
     */
    public Node(T data){
    	parent = null;
    	this.data = data;
    	children = new ArrayList<Node<T>>(0);
    }
    /**
     * basic constructor for root
     */
    public Node(){
    	parent = null;
    	children = new ArrayList<Node<T>>(0);
    }
    /**
     * add new node as child to passed node
     * @param newData parent of new node
     * @return added node
     */
    public Node<T> add(T newData){
    	Node<T> child = new Node<T>(newData);
    	this.children.add(child);
    	child.parent = this;
    	return child;
    }
    /**
     * check if data contained in child of node
     * @param check data to check
     * @return true if there false otherwise
     */
    public boolean childContain(T check){
    	for(int i = 0; i < this.children.size(); i++){
    		if(this.data == this.children.get(i).data){
    			return true;
    		}
    	}
    	return false;
    }
    
    
}
