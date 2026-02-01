/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
import java.util.ArrayList;
import java.util.List;

class Solution {
    private List<Long> subtreeSums = new ArrayList<>();
    public int maxProduct(TreeNode root) {
        long totalSum = calculateSubtreeSums(root);
        long maxProd = 0;
        for (long s : subtreeSums) {
            long currentProd = s * (totalSum - s);
            if (currentProd > maxProd) {
                maxProd = currentProd;
            }
        }
        return (int) (maxProd % 1000000007);
    }
    private long calculateSubtreeSums(TreeNode node) {
        if (node == null) return 0;
        long leftSum = calculateSubtreeSums(node.right);
        long rightSum = calculateSubtreeSums(node.left);
        long currentSum = leftSum + rightSum + node.val;
        subtreeSums.add(currentSum);
        return currentSum;
    }
}