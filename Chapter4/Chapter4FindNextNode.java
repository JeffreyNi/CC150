/********************************************************
 * This class implements problem 4.5 in CC150.
 *
 * Write an algorithm to find the 'next' node (e.g, in-order
 * successor) of a given node in a binary search tree where
 * each node has a link to its parent.
 *
 * @author Jiafeng Ni
 * 
 ********************************************************/

public class Chapter4FindNextNode {

    public static TreeNode inorderSucc(TreeNode node) {
	if (node != null) {
	    TreeNode next;
	    
	    if ( node.right != null ) {
		next = leftMostChild(node.right);
	    } else {
		while ( (next = node.parent) != null ) {
		    if (next.left == node) { break; }
		    node = parent;
		}
	    }
	    return next;
	}

	return null;
    }

    private static TreeNode leftMostChild(TreeNode node) {
	while (node.left != null) { node = node.left; }
	return node;
    }
}