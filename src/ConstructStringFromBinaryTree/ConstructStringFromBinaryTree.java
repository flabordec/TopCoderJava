package ConstructStringFromBinaryTree;

import java.util.Stack;

class Solution {

    //      Definition for a binary tree node.
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public String tree2str(TreeNode root) {
        StringBuilder builder = new StringBuilder();
        tree2strHelper(root, builder);
        return builder.toString();
    }

    private void tree2strHelper(TreeNode curr, StringBuilder builder) {
        builder.append(curr.val);

        if (curr.left != null) {
            builder.append("(");
            tree2strHelper(curr.left, builder);
            builder.append(")");
        }
        if (curr.right != null) {
            if (curr.left == null) {
                builder.append("()");
            }
            builder.append("(");
            tree2strHelper(curr.right, builder);
            builder.append(")");
        }
    }
}