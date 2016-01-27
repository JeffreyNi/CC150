/********************************************************
 * This class implements problem 4.6 in CC150.
 *
 * Design an algorithm and write code to find the first 
 * common ancestor of two nodes in a binary tree. Avoid
 * stroring additional nodes in a data structure.
 * NOTE: This is not necessarily a binary search tree.
 *
 * SOL1:
 * If each node has a link to its parent, we could trace p
 * and q's paths up until they intersect
 *
 * SOL2:
 * case1: if root == null || root == p || root == q, then
 * the root must be the lca.
 * case2: root != p && root != q, p and q are on the same 
 * side of root, i.e. either left or right. So if we call
 * the function on left subtree or right subtree, there 
 * must be one null and another not null. Return the one 
 * that is not null.
 * case3: p and q are on different sides of root, then 
 * both left and right would not be null. Return the root.
 *
 * @author Jiafeng Ni
 * 
 ********************************************************/

public class Chapter4LowestCommonAncestor {

    /**
     * Finds the lowest common ancestor of two nodes in a binary tree.
     * @param root the root of the binary tree
     * @param p the first node in the tree
     * @param q the second node in the tree
     * @return the lca of two nodes in the given binary tree
     */
    public static TreeNode getLCA(TreeNode root, TreeNode p, TreeNode q) {
	if (root == null || root == p || root == q) { return root; }

	TreeNode left = getLCA(root.left, p, q);
	TreeNode right = getLCA(root.right, p, q);

	if (left == null)  { return right; }
	if (right == null) { return left; }
	return root;
    }

}