/********************************************************
 * This class implements problem 3.6 in CC150.
 *
 * Write a program to sort a stack in ascending order. You 
 * should not make any assumptions about how the stack is
 * implemented. The following are the only functions that 
 * should be used to write this program: push | pop | peek
 * | isEmpty.
 *
 * @author Jiafeng Ni
 * 
 ********************************************************/

import java.util.*;

public class Chapter3SortStack {

    public static Stack<Integer> (Stack<Integer> stack) {
	Stack<Integer> res = new Stack<Integer>();

	while (!stack.empty()) {
	    int top = stack.pop();

	    while (!res.empty() && res.peek() > top) {
		stack.push(res.pop());
	    }
	    
	    res.push(top);
	}

	return res;
    }

}