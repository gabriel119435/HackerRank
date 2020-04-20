package javaCode.trees;

import java.util.Scanner;

// https://www.hackerrank.com/challenges/tree-height-of-a-binary-tree

class Node {
    Node left;
    Node right;
    int data;

    Node(int data) {
        this.data = data;
        left = null;
        right = null;
    }
}

public class TreeHeightBinaryTree {

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
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();
        Node root = null;
        while (t-- > 0) {
            int data = scan.nextInt();
            root = insert(root, data);
        }
        scan.close();
        assert root != null;
        int height = height(root);
        System.out.println(height);
    }
}
