package hackerrank.dataStructures.trees;

import java.util.ArrayList;
import java.util.List;

import hackerrank.dataStructures.trees.utils.Node;

import static hackerrank.dataStructures.trees.utils.Node.scanTree;

public class LevelOrderTraversal {

    //https://www.hackerrank.com/challenges/tree-level-order-traversal/

    public static void levelOrder(Node root) {
        List<Node> nodes = new ArrayList<>();
        nodes.add(root);

        while (!nodes.isEmpty()) {

            nodes.forEach(n -> System.out.print(n.data + " "));

            List<Node> children = new ArrayList<>();
            nodes.forEach(node -> {
                        if (node.left != null) children.add(node.left);
                        if (node.right != null) children.add(node.right);
                    }
            );
            nodes = children;
        }
    }

    public static void main(String[] args) {
        Node root = scanTree();
        levelOrder(root);
    }
}
