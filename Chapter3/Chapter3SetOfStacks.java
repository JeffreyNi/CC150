/********************************************************
 * This class implements problem 3.3 in CC150.
 *
 * Imagin a (literal) stack of plates. If the stack gets too high,
 * it might topple. Therefore, in real life, we would likely start
 * a new stack when the previous stack exceeds some threshold.
 * Implement a data structure SetOfStacks that mimics this. 
 * SetOfStacks should be composed of several stacks, and should 
 * create a new stack once the previous one exceeds capacity. 
 * SetOfStacks.push() and SetOfStacks.pop() should behave identically
 * to a single stack (that is, pop() should return the same values as
 * it would if there were just a single stack).
 *
 * FOLLOW UP:
 * Implement a function popAt(int index) which performs a pop operation
 * on a specific sub-stack.
 *
 * @author Jiafeng Ni
 * 
 ********************************************************/

import java.util.*;

public class Chapter3SetOfStacks {
    
    private int capacity;
    private ArrayList<Stack<Integer>> stacks;
    
    public Chapter3SetOfStacks(int cap) {
	this.stacks = new ArrayList<Stack<Integer>>();
	this.capacity = cap;
    }

    private Stack<Integer> getLast () {
	if (this.stacks.size() == 0) { return null; }
	else                         { return stacks.get(stacks.size() - 1); }
    }

    private boolean isFull(Stack stack) {
	return stack.size() == this.capacity;
    }

    public void push(int val) {
	Stack<Integer> last = getLast();
	if (last != null && !isFull(last)) {
	    last.push(val);
	} else {
	    Stack stack = new Stack<Integer>();
	    stack.push(val);
	    stacks.add(stack);
	}
    }

    public int pop() {
	Stack<Integer> last = getLast();
	int res = last.pop();
	if (last.empty()) { stacks.remove(stacks.size() - 1); }
	return res;
    }

    public int leftShift(int index, boolean removeTop) {
	Stack<Integer> stack = stacks.get(index);
	int removedItem;
	if (removeTop) { removedItem = stack.pop(); }
	else           { removedItem = stack.removeBottom(); } // here we actually need to write our own Stack class or using linked list
	
	if (stack.empty()) {
	    // if this stack is empty, just remove it
	    stacks.remove(index); 
	} else if (stacks.size() > index + 1) {
	    // if this stack is not empty, left shift elemnts in the stacks after this stack
	    int next = leftShift(index + 1, false);
	    stack.push(next);
	}

	return removedItem;
    }
}