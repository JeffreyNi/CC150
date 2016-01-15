/********************************************************
 * This class implements problem 3.2 in CC150.
 *
 * How would you design a stack which, in addition to push
 * and pop, also has a function min which returns the minimum
 * element? Push, pop and min should all operate in O(1) time.
 * 
 * This implementation uses a seperate stack to keep track of
 * the mins. The reason why this would be efficient is that when 
 * many elements have the same local min, then we're keeping a 
 * lot of duplicate data. 
 *
 * @author Jiafeng Ni
 * 
 ********************************************************/

import java.util.*;

public class Chapter3MinStack2 extends Stack<Integer>{

    private Stack<Integer> stack;

    public Chapter3MinStack2() {
	this.stack = new Stack<Integer>();
    }

    public void push(int value) {
	if (value <= min()) {this.stack.push(value);}
	super.push(value);
    }

    public Integer pop() {
	int value = super.pop();
	if (value == min()) {stack.pop();}
	return value;
    }

    public int min() {
	if (stack.isEmpty()) { return Integer.MAX_VALUE; }
	else                 { return stack.peek(); }
    }
    
}