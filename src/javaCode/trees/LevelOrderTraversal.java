package javaCode.trees;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LevelOrderTraversal {

    // https://www.hackerrank.com/challenges/tree-level-order-traversal/

    public static void levelOrder(Node root) {

        List<Node> nodeListToBePrintedNextIteration = new ArrayList<>();
        nodeListToBePrintedNextIteration.add(root);

        while (!nodeListToBePrintedNextIteration.isEmpty()) {

            nodeListToBePrintedNextIteration.forEach(n -> System.out.print(n.data + " "));

            List<Node> futureNodes = new ArrayList<>();
            nodeListToBePrintedNextIteration.forEach(
                    node -> {
                        if (node.left != null) futureNodes.add(node.left);
                        if (node.right != null) futureNodes.add(node.right);
                    }
            );

            nodeListToBePrintedNextIteration = futureNodes;
        }
    }

    public static Node insert(Node root, int data) {
        if (root == null) {
            return new Node(data);
        } else {
            Node cur;
            if (data <= root.data) {
                cur = insert(root.left, data);
                root.left = cur;
            } else {
                cur = insert(root.right, data);
                root.right = cur;
            }
            return root;
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();
        Node root = null;
        while (t-- > 0) {
            int data = scan.nextInt();
            root = insert(root, data);
        }
        scan.close();
        levelOrder(root);
    }
}
