package hackerrank.dataStructures.trees;

import hackerrank.dataStructures.trees.utils.Node;

import static hackerrank.dataStructures.trees.utils.Node.scanTree;

public class TreeHeightBinaryTree {

    //https://www.hackerrank.com/challenges/tree-height-of-a-binary-tree

    public static int height(Node root) {
        if (root.left == null && root.right == null)
            return 0;
        else {
            return Math.max(
                    root.left == null ? 0 : height(root.left) + 1,
                    root.right == null ? 0 : height(root.right) + 1
            );
        }
    }

    public static void main(String[] args) {
        Node root = scanTree();
        assert root != null;
        int height = height(root);
        System.out.println(height);
    }
}
