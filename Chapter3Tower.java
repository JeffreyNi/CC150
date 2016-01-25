/********************************************************
 * This class implements problem 3.4 in CC150.
 *
 * In the classic problem of the Towers of Hanoi, you have 3
 * rods and N disks of different sizes which can slide onto 
 * any tower. The puzzle starts with disks sorted in ascending
 * order of size from top to bottom (e.g., each disk sits on 
 * top of an even larger one). You have the following constrains:
 * (A) Only one disk can be moved at a time
 * (B) A disk is slid off the top of one rod onto the next rod.
 * (C) A disk can only be placed on top of a larger disk
 * Write a program to move the disks from the first rod to the 
 * last using Stacks.
 *
 * @author Jiafeng Ni
 * 
 ********************************************************/

import java.util.*;

public class Chapter3Tower {

    private Stack<Integer> disks; // disks are stored in a Stack
    private int index;            // index of the rod (Tower)
    
    public Chapter3Tower(int idx) {
	this.disks = new Stack<Integer>();
	this.index = idx;
    }

    /**
     * Returns the index of the tower.
     * @return index of the tower
     */
    public int getIndex() { return this.index; }


    /**
     * Add one disk onto the tower. The disk should be smaller than
     * the top disk on the tower, or the tower should be empty.
     * @param val the value of the new disk to be added
     */
    public void add(int val) {
	if (!disks.empty() && disks.peek() <= val) { System.out.println("Error placing disk " + val); }
	else                                       { this.disks.push(val); }
    }


    /**
     * Move the top disk onto another tower.
     * @param other the other tower where the top disk would be moved to
     */
    public void moveTopTo(Chapter3Tower other) {
	assert !this.disks.empty();
	
	int top = this.disks.pop();
	other.add(top);
	System.out.println("Move disk " + top + " from " + this.getIndex() + " to " + other.getIndex());
    }

    /**
     * Print out all disks in the tower from the top to the bottom.
     */
    public void print() {
	System.out.println("Contents of Tower " + this.getIndex());
	for (int i = this.diskas.size() - 1; i >= 0; i--) {
	    System.out.println("     " + disks.get(i));
	}
    }


    /**
     * Move n disks from current tower to the destination tower with the
     * help of buffer tower.
     * @param n number of disks to be moved
     * @param destination the destination tower where disks would be moved to
     * @param buffer the buffer tower where we will put disks temporarily
     */
    public void moveDisks(int n, Chapter3Tower destination, Chapter3Tower buffer) {
	if (n <= 0) { return; }

	moveDisks(n - 1, buffer, destination);
	moveTopTo(destination);
	buffer.moveDisks(n - 1, destination, this);
    }

}