/********************************************************
 * This class implements problem 4.4 in CC150.
 *
 * Given a binary search tree, design an algorithm which
 * creates a linked list of all the nodes at each depth
 * (e.g., if you have a tree with depth D, you'll have 
 * D linked list).
 *
 * @author Jiafeng Ni
 * 
 ********************************************************/

import java.util.*;

public class Chapter4LevelLinkedListInTree {

    public ArrayList<LinkedList<Integer>> getLevelLinkedList(TreeNode root) {
	ArrayList<LinkedList<Integer>> result = new ArrayList<LinkedList<Integer>>();
	if (root == null) { return result; }

	Queue<TreeNode> q = new LinkedList<TreeNode>();
	q.add(root);
	TreeNode node;

	while (!q.isEmpty()) {
	    int size = q.size();
	    LinkedList<Integer> list = new LinkedList<Integer>();

	    for (int i = 0; i < size; i++) {
		node = q.poll();
		list.add(node.getVal());

		if (node.left != null) { q.add(node.left); }
		if (node.right != null) { q.add(node.right); }
	    }
	    
	    result.add(list);
	}

	return result;
    }

}