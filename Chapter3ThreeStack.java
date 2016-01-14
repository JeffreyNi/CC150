/********************************************************
 * This class implements problem 3.1 in CC150.
 *
 * Describe how you could use a single array to implement
 * three stack.
 * 
 * This implementation divide the array into three equal parts 
 * and allow the individual stack to grow in that limited space.
 * 
 ********************************************************/

import java.util.*;

public class Chapter3ThreeStack {

    private final int STACK_SIZE = 300;
    private int[] stack ;
    private int[] stackPointer ;

    public Chapter3ThreeStack () {
	this.stack = new int[3 * STACK_SIZE];
	this.stackPointer = new int[3];
    }

    /**
     * Push the value into the stack specified by stackNum.
     * @param stackNum the No. of the stack is in range {0, 1, 2}
     * @param val the value to be pushed into stack
     */
    public void push(int stackNum, int val) {
	int index = stackNum * STACK_SIZE + stackPointer[stackNum];
	stack[index] = val;
	stackPointer[stackNum]++;
    }

    public int pop(int stackNum) {
	stackPointer[stackNum]--;
	int index = stackNum * STACK_SIZE + stackPointer[stackNum];
	int res = stack[index];
	stack[index] = 0;
	return res;
    }

    public int peek(int stackNum) {
	int index = stackNum * STACK_SIZE + stackPointer[stackNum] - 1;
	return this.stack[index];
    }
    
    public boolean isEmpty(int stackNum) {
	return this.stackPointer[stackNum] == 0;
    }
}