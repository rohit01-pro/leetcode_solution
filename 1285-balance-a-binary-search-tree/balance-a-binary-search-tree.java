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
    public TreeNode balanceBST(TreeNode root) {
        List<TreeNode> nodes = new ArrayList<>();
        inorder(root, nodes);
        return buildBalancedTree(nodes, 0, nodes.size() - 1);
    }
    private void inorder(TreeNode node, List<TreeNode> nodes) {
        if (node == null) return;
        inorder(node.left, nodes);
        nodes.add(node);
        inorder(node.right, nodes);
    }
    private TreeNode buildBalancedTree(List<TreeNode> nodes, int start, int end) {
        if (start > end) return null;
        
        int mid = start + (end - start) / 2;
        TreeNode root = nodes.get(mid);
        
        root.left = buildBalancedTree(nodes, start, mid - 1);
        root.right = buildBalancedTree(nodes, mid + 1, end);
        
        return root;
    }
}