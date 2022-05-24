import java.util.*;

public class Graph<T> {

    private int adjMatrix[][];
    private ArrayList<T> vertexes;
    private List<Edge<T>> edges;
    private int v;

    public Graph(List<Edge<T>> edges) {
        // TODO: Przekształcenie krawędzi na macierz sąsiedztwa, odwzorowanie wierzchołka na indeks, itp.
        this.edges = edges;
        vertexes = new ArrayList<>();
        Edge<T> tmp;

        for (int i = 0; i < edges.size(); i++) {
            tmp = edges.get(i);
            T source = tmp.getSource();
            T destination = tmp.getDestination();

            if (!vertexes.contains(source))
                vertexes.add(source);

            if (!vertexes.contains(destination))
                vertexes.add(destination);
        }

        this.v = vertexes.size();

        adjMatrix = new int[v][v];
        for (int i = 0; i < v; i++) {
            for (int j = 0; j < v; j++) {
                if (i == j)
                    adjMatrix[i][j] = 0;
                else
                    adjMatrix[i][j] = -1;
            }
        }


        for (int k = 0; k < edges.size(); k++) {
            tmp = edges.get(k);
            T source = tmp.getSource();
            T destination = tmp.getDestination();
            int i = vertexes.indexOf(source);
            int j = vertexes.indexOf(destination);
            int weight = tmp.getWeight();
            adjMatrix[i][j] = weight;
        }

    }

    public String depthFirst(T startNode) throws NoSuchElementException {
        // TODO: Przejście przez graf metodą najpierw-wgłąb od podanego wierzchołka
        if (!vertexes.contains(startNode))
            throw new NoSuchElementException();

        boolean[] visited = new boolean[v];
        String s = depthFirstRec(startNode, visited);
        return s.substring(0, s.length()-2);
    }

    public String depthFirstRec(T startNode, boolean[] visited){
        String result = String.valueOf(startNode)+", ";
        int idx = vertexes.indexOf(startNode);
        visited[idx] = true;
        for (int i = 0; i < v; i++) {
            if (adjMatrix[idx][i] > 0 && (!visited[i])){
                result += depthFirstRec(vertexes.get(i), visited);
            }
        }
        return result;
    }

    public String breadthFirst(T startNode) throws NoSuchElementException {
        // TODO: Przejście przez graf metodą najpierw-wszerz od podanego wierzchołka
        if (!vertexes.contains(startNode))
            throw new NoSuchElementException();
        boolean[] visited = new boolean[v];
        ArrayList<T> q = new ArrayList<>();
        String result = "";

        q.add(startNode);
        visited[vertexes.indexOf(startNode)] = true;

        T vis;
        while (!q.isEmpty()){
            vis = q.remove(0);
            int idx = vertexes.indexOf(vis);
            result += String.valueOf(vis)+", ";

            for (int i = 0; i < v; i++) {
                int x = adjMatrix[idx][i];
                if ( x > 0 && (!visited[i])){
                    q.add(vertexes.get(i));
                    visited[i] = true;
                }
            }
        }
        result = result.substring(0, result.length()-2);

        return result;
    }

    public int connectedComponents() throws ItemOutOfRangeException {
        // TODO: Liczba składowych spójnych grafu
        DisjointSetForest dsf = new DisjointSetForest(v);

        for (int i = 0; i < edges.size(); i++) {
            T source = edges.get(i).getSource();
            T destination = edges.get(i).getDestination();
            int idxS = vertexes.indexOf(source);
            int idxD = vertexes.indexOf(destination);

            if (dsf.findSet(idxS) != dsf.findSet(idxD))
                dsf.union(idxS, idxD);
        }

        Hashtable<T, Integer> tab = new Hashtable<T, Integer>();

        return (int) dsf.countDistinctSets(dsf);
    }
}
