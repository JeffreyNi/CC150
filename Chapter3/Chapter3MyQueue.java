/********************************************************
 * This class implements problem 3.5 in CC150.
 *
 * Implement a MyQueue class which implements a queue using
 * two stacks.
 *
 * @author Jiafeng Ni
 * 
 ********************************************************/

import java.util.*;

public class Chapter3MyQueue<T> {

    private Stack<T> inputStack;
    private Stack<T> outputStack;

    public Chapter3MyQueue() {
	this.inputStack = new Stack<T>();
	this.outputStack = new Stack<T>();
    }

    /**
     * Returns true if the queue is empty. Return false otherwise.
     * @return true if queue is empty.
     */
    public boolean isEmpty() {
	return this.inputStack.empty() && this.outputStack.empty();
    }


    /**
     * Add a new element into the queue.
     * @param t the new element to be added into the queue
     */
    public void add(T t) {
	this.inputStack.push(t);
    }


    /**
     * Returns the front element in the queue.
     * @return the front element in the queue
     */
    public T peek() {
	if ( outputStack.empty() ) {
	    while ( !inputStack.empty() ) {
		outputStack.push( inputStack.pop() );
	    }
	} 

	return outputStack.peek();
    }

    /**
     * Removes the front element of the queue and returns it.
     * @return the front element being removed
     */
    public T remove() {
	if ( outputStack.empty() ) {
	    while ( !inputStack.empty() ) {
		outputStack.push( inputStack.pop() );
	    }
	}

	return outputStack.pop();
    }
}