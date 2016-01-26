/********************************************************
 * This class implements problem 4.1 in CC150.
 *
 * Implement a function to check if a tree is balanced. For
 * the purpose of this question, a balanced tree is defined
 * to be a tree such that no two leaf nodes differ in distance
 * from the root by more than one.
 *
 * @author Jiafeng Ni
 * 
 ********************************************************/

public class Chapter4BalancedTree{

    public class TreeNode {
	private int val;
	private TreeNode left;
	private TreeNode right;

	public TreeNode (int val) {
	    this.val = val;
	}
    }

    /**
     * Returns true if the given tree is balanced. Here a 
     * balanced tree is defined to be a tree such that no
     * two leaf nodes differ in distance from the root by 
     * more than one.
     * @param root the given tree
     * @return true if the given tree is balanced.
     */
    public static boolean isBalanced(TreeNode root) {
	return getHeight(root) != -1;
    }


    /**
     * Return the depth of the tree. If the tree is not 
     * balanced, return -1.
     * @param root the given tree
     * @return the depth of the tree
     */
    private static int getHeight(TreeNode root) {
	if (root == null) {return 0;}

	int leftHeight = getHeight(root.left);
	int rightHeight = getHeight(root.right);

	if (leftHeight == -1 || rightHeight == -1) { return -1; }
	if (Math.abs(leftHeight - rightHeight) > 1) { return -1; }
	
	return 1 + Math.max(leftHeight, rightHeight);
    }
}