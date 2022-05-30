import java.util.*;

public class Graph<T> {
    private final HashMap<T, Integer> dictionary;
    private int[][] adjMatrix;
    private int V;

    public Graph(List<Edge<T>> edges) {
        V = 0;
        adjMatrix = new int[8][8];
        dictionary = new HashMap<>();
        for (Edge<T> edge : edges) {
            T node1 = edge.getNode1();
            T node2 = edge.getNode2();
            addNode(node1);
            addNode(node2);
            if (V > adjMatrix.length)
                doubleMatrix();
            adjMatrix[getIndex(node1)][getIndex(node2)] = edge.getDistance();
            adjMatrix[getIndex(node2)][getIndex(node1)] = edge.getDistance();
        }
    }

    private void doubleMatrix() {
        int length = adjMatrix.length * 2;
        int[][] newMatrix = new int[length][length];
        for (int i = 0; i < adjMatrix.length; i++) {
            System.arraycopy(adjMatrix[i], 0, newMatrix[i], 0, adjMatrix.length);
        }
        adjMatrix = newMatrix;
    }


    private void addNode(T node){
        if (!dictionary.containsKey(node)){
            dictionary.put(node, V);
            V++;
        }
    }

    private int getIndex(T node){
        if (dictionary.containsKey(node))
            return dictionary.get(node);
        return -1;
    }

    private int getDistance (T node, HashMap<T, Integer> shortestPathMap){
        if (shortestPathMap.containsKey(node))
            return shortestPathMap.get(node);
        return Integer.MAX_VALUE;
    }

    public Map<T, Integer> calculateShortestPaths(T startNode) throws NoSuchElementException {
        // TODO: Wylicz najkrótsze ścieżki do każdego wierzchołka w grafie (Dijkstra)
        if (!dictionary.containsKey(startNode))
            throw new NoSuchElementException();
        HashMap<T, Integer> shortestPathMap = new HashMap<T, Integer>();
        Boolean evaluated[] = new Boolean[V];

        for (T node: dictionary.keySet()) {
            shortestPathMap.put(node, Integer.MAX_VALUE);
            evaluated[getIndex(node)] = false;
        }

        shortestPathMap.put(startNode, 0);

        T u;
        int idxU;
        int distanceU;


        for (int i = 0; i < V - 1; i++) {
            u = minDistance(shortestPathMap, evaluated);
            idxU = getIndex(u);
            distanceU = getDistance(u, shortestPathMap);

            evaluated[idxU] = true;

            for (T node: dictionary.keySet()) {
                int idxV  = getIndex(node);
                int distanceV = getDistance(node, shortestPathMap);
                Boolean visited = evaluated[idxV];

                if (!visited && adjMatrix[idxU][idxV] != 0 && distanceU != Integer.MAX_VALUE &&  distanceU+adjMatrix[idxU][idxV] < distanceV)
                    shortestPathMap.replace(node, distanceU + adjMatrix[idxU][idxV]);
            }
        }

        shortestPathMap.remove(startNode);

        return shortestPathMap;
    }

    private T minDistance(HashMap<T, Integer> shortestPathMap, Boolean sptSet[]) {
        int min = Integer.MAX_VALUE;
        T minDistNode = null;
        for (T node: shortestPathMap.keySet()) {
            int distance = shortestPathMap.get(node);
            if (sptSet[getIndex(node)] == false && distance <= min){
                min = distance;
                minDistNode = node;
            }
        }
        return minDistNode;
    }

    public Integer calculateShortestPath(T startNode, T endNode) throws NoSuchElementException {
        // TODO: Wylicz najkrótszą ścieżkę pomiędzy wierzchołkami w grafie

        if (!dictionary.containsKey(startNode) || !dictionary.containsKey(endNode))
            throw new NoSuchElementException();
        HashMap<T, Integer> shortestPathMap = new HashMap<T, Integer>();
        Boolean evaluated[] = new Boolean[V];

        for (T node: dictionary.keySet()) {
            shortestPathMap.put(node, Integer.MAX_VALUE);
            evaluated[getIndex(node)] = false;
        }

        shortestPathMap.put(startNode, 0);
        T u = minDistance(shortestPathMap, evaluated);
        int idxU;
        int distanceU;

        for (int i = 1; i < V - 1 && !u.equals(endNode); i++) {
            u = minDistance(shortestPathMap, evaluated);
            idxU = getIndex(u);
            distanceU = getDistance(u, shortestPathMap);

            evaluated[idxU] = true;

            for (T node: dictionary.keySet()) {
                int idxV  = getIndex(node);
                int distanceV = getDistance(node, shortestPathMap);
                Boolean visited = evaluated[idxV];

                if (!visited && adjMatrix[idxU][idxV] != 0 && distanceU != Integer.MAX_VALUE &&  distanceU+adjMatrix[idxU][idxV] < distanceV)
                    shortestPathMap.replace(node, distanceU + adjMatrix[idxU][idxV]);
            }
        }

        return getDistance(endNode, shortestPathMap);
    }
}
