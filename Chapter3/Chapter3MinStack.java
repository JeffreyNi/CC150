/********************************************************
 * This class implements problem 3.2 in CC150.
 *
 * How would you design a stack which, in addition to push
 * and pop, also has a function min which returns the minimum
 * element? Push, pop and min should all operate in O(1) time.
 * 
 * We can implement this by having each node in the stack keep
 * track of the minimum beneath itself. Then, to find the min, 
 * we just look at what the top element thinks is the min.
 * 
 * There's one issue with this implementation. It waste a lot
 * of space recording the min values when the stack is really
 * large. And the NodeWithMin class has public elements.
 * 
 * Another implementation using less space please see 
 * Chapter3MinStack2.java.
 *
 * @author Jiafeng Ni
 * 
 ********************************************************/

import java.util.*;

public class Chapter3MinStack extends Stack<NodeWithMin>{

    public void push (int value) {
	int newMin = Math.min(value, min());
	super.push(new NodeWithMin(value, newMin));
    }


    public int min() {
	if (this.isEmpty()) { return Integer.MAX_VALUE; }
	else                { return peek().min; }
    }


}