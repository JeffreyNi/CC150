/********************************************************
 * This class implements problem 4.3 in CC150.
 *
 * Given a sorted (increasing order) array, write an algorithm
 * to create a binary tree with minimal height.
 *
 * @author Jiafeng Ni
 * 
 ********************************************************/

public class Chapter4ArrayToBinaryTree {

    public static class TreeNode {
	private int val;
	private TreeNode left;
	private TreeNode right;

	public TreeNode (int val) {
	    this.val = val;
	}
    }

    /**
     * Given a sorted array, convert it into a binary tree with
     * the minimal height.
     * @param arr the given sorted array
     * @return the root of the binary tree converted from the array
     */
    public static TreeNode convertToTree(int[] arr) {
	return binaryBuildTree(arr, 0, arr.length - 1);
    }

    /**
     * Helper function for convertoToTree. 
     * @param arr the given sorted array
     * @param left the left index of range in the array where tree would be built
     * @param right the right index of range in the array where treee would be built
     * @return the root of the binary tree converted from the given range of the array
     */
    private static TreeNode binaryBuildTree(int[] arr, int left, int right) {
	if (left > right) { return null; }

	int mid = left + (right - left) / 2;
	TreeNode root = new TreeNode(arr[mid]);
	root.left = binaryBuildTree(arr, left, mid - 1);
	root.right = binaryBuildTree(arr, mid + 1, right);

	return root;
    }

}