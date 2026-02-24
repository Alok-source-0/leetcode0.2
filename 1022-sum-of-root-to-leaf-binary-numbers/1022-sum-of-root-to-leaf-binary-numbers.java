/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode() {}
 * TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) {
 * this.val = val;
 * this.left = left;
 * this.right = right;
 * }
 * }
 */
class Solution {
    public int sumRootToLeaf(TreeNode root) {
        return dfs(root, 0);
    }

    private int dfs(TreeNode node, int currentSum) {
        if (node == null) return 0;

        // Shift left and add current node value
        // Equivalent to (currentSum << 1) | node.val
        currentSum = (currentSum * 2) + node.val;

        // If it's a leaf node, return the path value
        if (node.left == null && node.right == null) {
            return currentSum;
        }

        // Otherwise, continue down both paths and sum the results
        return dfs(node.left, currentSum) + dfs(node.right, currentSum);
    }
}