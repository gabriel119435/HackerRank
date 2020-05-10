package hackerrank.dataStructures.trees;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class SwapNodesAlgorithm {

    //https://www.hackerrank.com/challenges/swap-nodes-algo

    private static class Node {
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
            Node currentNode = this.leftChild;
            this.leftChild = this.rightChild;
            this.rightChild = currentNode;
        }

        List<Integer> printNode() {
            List<Integer> nodes = new ArrayList<>();
            if (this.leftChild != null) nodes.addAll(this.leftChild.printNode());
            nodes.add(this.number);
            if (this.rightChild != null) nodes.addAll(this.rightChild.printNode());
            return nodes;
        }
    }

    static int[][] swapNodes(int[][] indexes, int[] queries) {

        HashMap<Integer, List<Node>> nodesPerLayer = buildTreePerLayer(indexes);
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

            //print tree from root (layer = 1)
            results.add(nodesPerLayer.get(1).get(0).printNode());
        }

        int[][] arrayResults = new int[results.size()][];
        for (int i = 0; i < results.size(); i++) {
            List<Integer> result = results.get(i);
            arrayResults[i] = result.stream().mapToInt(n -> n).toArray();
        }

        return arrayResults;
    }

    private static HashMap<Integer, List<Node>> buildTreePerLayer(int[][] indexes) {
        HashMap<Integer, List<Node>> nodesPerLayer = emptyTreePerLayer();
        HashMap<Integer, List<int[]>> indexesByLayer = mapIndexesByLayer(indexes);

        for (int currLayer : indexesByLayer.keySet()) {
            List<Node> nodesOnThisLayer = nodesPerLayer.get(currLayer);
            List<int[]> instructionsOnThisLayer = indexesByLayer.get(currLayer);

            Iterator<Node> it1 = nodesOnThisLayer.iterator();
            Iterator<int[]> it2 = instructionsOnThisLayer.iterator();

            while (it1.hasNext() && it2.hasNext()) {
                Node currentNode = it1.next();
                int[] index = it2.next();
                int leftNumber = index[0];
                int rightNumber = index[1];

                Node leftNode = leftNumber != -1 ? new Node(leftNumber, null, null, currentNode) : null;
                Node rightNode = rightNumber != -1 ? new Node(rightNumber, null, null, currentNode) : null;
                currentNode.setRightAndLeftNodes(leftNode, rightNode);

                // prepare next layer
                int nextLayer = currLayer + 1;
                List<Node> newNodes = new ArrayList<>();
                if (leftNode != null) newNodes.add(leftNode);
                if (rightNode != null) newNodes.add(rightNode);

                List<Node> nextNodes = nodesPerLayer.get(nextLayer);
                if (nextNodes == null) {
                    nextNodes = new ArrayList<>(newNodes);
                } else {
                    nextNodes.addAll(newNodes);
                }
                nodesPerLayer.put(nextLayer, nextNodes);
            }
        }

        return nodesPerLayer;
    }

    private static HashMap<Integer, List<Node>> emptyTreePerLayer() {
        HashMap<Integer, List<Node>> map = new HashMap<>();
        map.put(1, Collections.singletonList(new Node(1, null, null, null)));
        return map;
    }

    static HashMap<Integer, List<int[]>> mapIndexesByLayer(int[][] indexes) {
        int nodesToAddOnCurrLayer = 1;
        int nodesAdded = 0;
        int currLayer = 1;
        int futureNodesToAdd = 0;

        HashMap<Integer, List<int[]>> indexesByLayer = new HashMap<>();

        for (int[] index : indexes) {
            if (nodesToAddOnCurrLayer > nodesAdded) {
                // current layer
                addIndexToLayer(indexesByLayer, currLayer, index);

                if (index[0] != -1) futureNodesToAdd++;
                if (index[1] != -1) futureNodesToAdd++;
                nodesAdded++;
            } else {
                // new layer
                currLayer++;
                nodesToAddOnCurrLayer = futureNodesToAdd;
                futureNodesToAdd = 0;
                addIndexToLayer(indexesByLayer, currLayer, index);

                if (index[0] != -1) futureNodesToAdd++;
                if (index[1] != -1) futureNodesToAdd++;
                nodesAdded = 1;
            }
        }
        return indexesByLayer;
    }

    private static void addIndexToLayer(HashMap<Integer, List<int[]>> indexesByLayer, int layer, int[] indexToAdd) {
        List<int[]> indexes = indexesByLayer.get(layer);
        if (indexes != null) {
            indexes.add(indexToAdd);
        } else {
            List<int[]> newIndexes = new ArrayList<>();
            newIndexes.add(indexToAdd);
            indexesByLayer.put(layer, newIndexes);
        }
    }


    public static void main(String[] args) {

     /*
                         k=2                        k=4

                 1                  1                    1
                / \                / \                  / \
               /   \              /   \                /   \
              2     3    [s]     2     3              2     3
             /      /             \     \              \     \
            /      /               \     \              \     \
           4      5         ->      4     5         ->   4     5
          /      / \               /     / \            /     / \
         /      /   \             /     /   \          /     /   \
        6      7     8   [s]     6     7     8   [s]  6     7     8
         \          / \         /           / \        \         / \
          \        /   \       /           /   \        \       /   \
           9      10   11     9           11   10        9     10   11
     */

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
        int[] queries = new int[]{2, 4};

        System.out.println(Arrays.deepToString(swapNodes(indexes, queries)));

    }
}
