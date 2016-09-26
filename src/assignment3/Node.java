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

public class Node<T> {
    public Node<T> parent;
    public T data;
    public ArrayList<Node<T>> children;
    
    public Node(T data){
    	parent = null;
    	this.data = data;
    	children = new ArrayList<Node<T>>(0);
    }
    
    public Node(){
    	parent = null;
    	children = new ArrayList<Node<T>>(0);
    }
    
    public Node<T> add(T newData){
    	Node<T> child = new Node<T>(newData);
    	this.children.add(child);
    	child.parent = this;
    	return child;
    }
    
    public boolean childContain(T check){
    	for(int i = 0; i < this.children.size(); i++){
    		if(this.data == this.children.get(i).data){
    			return true;
    		}
    	}
    	return false;
    }
    
    
}
