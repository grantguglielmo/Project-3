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
    
    
}
