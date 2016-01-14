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

public class Chapter3ThreeStack2{

    private static final int STACK_SIZE = 300;
    int indexUsed;
    int[] stackPointer;
    StackNode[] stack;

    public Chapter3ThreeStack2() {
	this.indexUsed = 0;
	this.stack = new StackNode[3 * STACK_SIZE];
	this.stackPointer = new int[3];
	Arrays.fill(stackPointer, -1);
    }

    public void push (int stackNum, int value) {
	int lastIndex = stackPointer[stackNum];
	stackPointer[stackNum] = indexUsed;
	stack[indexUsed] = new StackNode(lastIndex, value);
	indexUsed++;
    }

    public int pop(int stackNum) {
	int lastIndex = stackPointer[stackNum];
	int value = stack[lastIndex].value;
	stackPointer[stackNum] = stack[lastIndex].previous;
	stack[lastIndex] = null;
	indexUsed--;
	return value;
    }

    public int peek(int stackNum) {
	return stack[stackPointer[stackNum]].value;
    }

    public boolean isEmpty(int stackNum) {
	return stackPointer[stackNum] == -1;
    }


    class StackNode {
	private int previous;
	private int value;
	
	public StackNode(int p, int v) {
	    this.previous = p;
	    this.value = v;
	}
    }
}