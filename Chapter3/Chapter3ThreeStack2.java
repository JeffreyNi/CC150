/********************************************************
 * This class implements problem 3.1 in CC150.
 *
 * Describe how you could use a single array to implement
 * three stack.
 * 
 * This implementation, we sequentially allocate space to 
 * the stakcs and we link new blocks to the previous block. 
 * This means any new element in a stack keeps a pointer to
 * the previous top element of that particular stack.
 *
 * In this implemetation, we face a problem of unused space. 
 * For example, if a stack deletes some of its elements, the 
 * deleted elements may not necessarily appear at the end of
 * the array. So, in that case, we would not be able to use
 * those newly freed spaces.
 * 
 * To overcome this deficiency, we can maintain a free list 
 * and the whole array space would be given initially to the
 * free list. For every insertion, we would delete an entry 
 * from the free list. In case of deletion, we would simply
 * add the index of the free cell to the free list.
 *
 * In this implementation, we would be able to have flexibility
 * in terms of variable space utilization but we would need to 
 * increase the space complexity.
 *
 * This implementation is somewhat like what we would do in
 * the implementation of kernel memory space.
 *
 * @author Jiafeng Ni
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