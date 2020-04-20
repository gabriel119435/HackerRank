package javaCode.crackingTheCodingInterview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;


public class BFSShortestReachInAGraph {

    static class GraphNode {
        int data;
        Set<GraphNode> nodesConnected;
        boolean distanceCalculated;

        public GraphNode(int data) {
            this.data = data;
            nodesConnected = new HashSet<>();
        }
    }

    private static void countDistances(
            int nodesQuantity,
            List<List<Integer>> vertices,
            int startingNodeNumber
    ) {
        Map<Integer, GraphNode> nodesByNumber = new HashMap<>();
        int nodesBuilt = nodesQuantity;
        while (nodesBuilt > 0) {
            nodesByNumber.put(nodesBuilt, new GraphNode(nodesBuilt));
            nodesBuilt--;
        }
        vertices.forEach(vertice -> {
            GraphNode n1 = nodesByNumber.get(vertice.get(0));
            GraphNode n2 = nodesByNumber.get(vertice.get(1));
            n1.nodesConnected.add(n2);
            n2.nodesConnected.add(n1);
        });
        // graph built

        buildDistanceArray(nodesQuantity, startingNodeNumber, nodesByNumber);

    }

    private static void buildDistanceArray(int nodesQuantity, int startingNodeNumber, Map<Integer, GraphNode> nodesByNumber) {
        int distancesToFill = nodesQuantity;
        List<Integer> distanceList = new ArrayList<>(distancesToFill);
        for (; distancesToFill > 0; distancesToFill--) {
            distanceList.add(-1);
        }

        nodesByNumber.get(startingNodeNumber).distanceCalculated = true;
        List<GraphNode> nodesToAnalise = new ArrayList<>(nodesByNumber.get(startingNodeNumber).nodesConnected);

        int currDistance = 6;
        while (!nodesToAnalise.isEmpty()) {
            List<GraphNode> nextNodesToAnalise = new ArrayList<>();
            int finalCurrDistance = currDistance;

            nodesToAnalise.forEach(node -> {
                //add distance if shorter
                if (distanceList.get(node.data - 1) == -1 || distanceList.get(node.data - 1) > finalCurrDistance) {
                    distanceList.set(node.data - 1, finalCurrDistance);
                }
                node.distanceCalculated = true;

                // add nextNodes to analise
                node.nodesConnected.stream().filter(n -> !n.distanceCalculated).forEach(nextNodesToAnalise::add);
            });
            nodesToAnalise = nextNodesToAnalise;
            currDistance += 6;
        }

        distanceList.remove(startingNodeNumber - 1);
        distanceList.forEach(n -> System.out.print(n + " "));
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();

        while (n-- > 0) {
            int nodesQuantity = scan.nextInt();
            int verticesQuantity = scan.nextInt();
            List<List<Integer>> vertices = new ArrayList<>();
            int verticesRead = verticesQuantity;

            while (verticesRead-- > 0) {
                int node1 = scan.nextInt();
                int node2 = scan.nextInt();
                vertices.add(Arrays.asList(node1, node2));
            }
            int startingNode = scan.nextInt();

            countDistances(nodesQuantity, vertices, startingNode);
        }

    }
}
