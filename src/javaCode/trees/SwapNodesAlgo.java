package javaCode.trees;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class SwapNodesAlgo {

    //https://www.hackerrank.com/challenges/swap-nodes-algo

    public static class Node {
        int number;
        Node father;
        Node leftChild;
        Node rightChild;

        public Node(int number, Node leftChild, Node rightChild, Node father) {
            this.number = number;
            this.leftChild = leftChild;
            this.rightChild = rightChild;
            this.father = father;
        }

        public void setRightAndLeftNodes(Node left, Node right) {
            this.rightChild = right;
            this.leftChild = left;
        }

        void swapNodes() {
            Node currentNode = this.leftChild; // be aware of clone!
            this.leftChild = this.rightChild;
            this.rightChild = currentNode;
        }

        List<Integer> printNode() {
            List<Integer> nodes = new ArrayList<>();

            // print leftTree
            if (this.leftChild != null) nodes.addAll(this.leftChild.printNode());

            // print node
            nodes.add(this.number);

            // print rightTree
            if (this.rightChild != null) nodes.addAll(this.rightChild.printNode());


            return nodes;
        }
    }

    static int[][] swapNodes(int[][] indexes, int[] queries) {

        HashMap<Integer, List<Node>> nodesPerLayer = buildTree(indexes);
        List<List<Integer>> results = new ArrayList<>();

        for (int depthOfSwap : queries) {

            List<Node> nodesToBeSwapped = nodesPerLayer.get(depthOfSwap);

            int i = 2;
            while (nodesToBeSwapped != null) {
                nodesToBeSwapped.forEach(Node::swapNodes);
                int multiple = depthOfSwap * i;
                nodesToBeSwapped = nodesPerLayer.get(multiple);
                i++;
            }

            //add result
            results.add(nodesPerLayer.get(1).get(0).printNode());
        }

        int[][] arrayResults = new int[results.size()][];
        for (int i = 0; i < results.size(); i++) {
            List<Integer> result = results.get(i);
            arrayResults[i] = result.stream().mapToInt(number -> number).toArray();
        }

        return arrayResults;
    }

    private static HashMap<Integer, List<Node>> buildTree(int[][] indexes) {
        HashMap<Integer, List<int[]>> instructionsByLayer = mapInstructionsByLayer(indexes);
        HashMap<Integer, List<Node>> nodesPerLayer = emptyRootNode();

        for (int currentLayer : instructionsByLayer.keySet()) {
            List<int[]> instructionsOnThisLayer = instructionsByLayer.get(currentLayer);
            List<Node> nodesOnThisLayer = nodesPerLayer.get(currentLayer);

            Iterator<Node> it1 = nodesOnThisLayer.iterator();
            Iterator<int[]> it2 = instructionsOnThisLayer.iterator();

            while (it1.hasNext() && it2.hasNext()) {
                Node currentNode = it1.next();
                int[] instruction = it2.next();

                int leftNumber = instruction[0];
                int rightNumber = instruction[1];
                Node leftNode = leftNumber != -1 ? new Node(leftNumber, null, null, currentNode) : null;
                Node rightNode = rightNumber != -1 ? new Node(rightNumber, null, null, currentNode) : null;
                currentNode.setRightAndLeftNodes(leftNode, rightNode);

                // set left and right nodes to futureLayer
                List<Node> nodesToAdd = new ArrayList<>();
                if (leftNode != null) nodesToAdd.add(leftNode);
                if (rightNode != null) nodesToAdd.add(rightNode);

                int futureLayer = currentLayer + 1;
                List<Node> futureNodes = nodesPerLayer.get(futureLayer);
                if (futureNodes == null) {
                    futureNodes = new ArrayList<>(nodesToAdd);
                } else {
                    futureNodes.addAll(nodesToAdd);
                }
                if (!futureNodes.isEmpty()) nodesPerLayer.put(futureLayer, futureNodes);

            }
        }

        return nodesPerLayer;
    }

    private static HashMap<Integer, List<Node>> emptyRootNode() {
        HashMap<Integer, List<Node>> nodesPerLayer = new HashMap<>();
        Node root = new Node(1, null, null, null);
        nodesPerLayer.put(1, Collections.singletonList(root));
        return nodesPerLayer;
    }

    static HashMap<Integer, List<int[]>> mapInstructionsByLayer(int[][] indexes) {
        int nodesToAdd = 1;
        int countedInstructions = 0;
        int currentLayer = 1;
        int nodesAdded = 0;

        HashMap<Integer, List<int[]>> instructionsPerLayer = new HashMap<>();

        for (int[] instruction : indexes) {
            // TODO 6 -1 depth 2 is wrong!
            if (nodesToAdd > countedInstructions) {

                // add this instruction to this layer
                List<int[]> instructionsOnThisLayer = instructionsPerLayer.get(currentLayer);
                if (instructionsOnThisLayer == null) {
                    List<int[]> instructions = new ArrayList<>();
                    instructions.add(instruction);
                    instructionsPerLayer.put(currentLayer, instructions);
                } else {
                    instructionsOnThisLayer.add(instruction);
                }

                // count nodes on this layer
                if (instruction[0] != -1) nodesAdded++;
                if (instruction[1] != -1) nodesAdded++;

                // adds 1 to the counted instructions
                countedInstructions++;
            } else {
                // new layer
                currentLayer++;
                // one new counted instruction, this one!
                countedInstructions = 1;
                nodesToAdd = nodesAdded;
                nodesAdded = 0;

                // countNodes on this instruction
                if (instruction[0] != -1) nodesAdded++;
                if (instruction[1] != -1) nodesAdded++;

                List<int[]> instructions = new ArrayList<>();
                instructions.add(instruction);
                instructionsPerLayer.put(currentLayer, instructions);
            }
        }
        return instructionsPerLayer;
    }

    public static void main(String[] args) {

        // 11 nodes, 2 numbers to swap (check depth multiples!)
        int[][] indexes = new int[][]{
                {2, 3},
                {4, -1},
                {5, -1},
                {6, -1},
                {7, 8},
                {-1, 9},
                {-1, -1},
                {10, 11},
                {-1, -1},
                {-1, -1},
                {-1, -1}
        };
        int[] queries = new int[]{
                2, 4
        };

        System.out.println(Arrays.deepToString(swapNodes(indexes, queries)));

    }
}
