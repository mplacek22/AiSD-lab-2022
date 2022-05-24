import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.fail;

public class DisjointSetForest implements IDisjointSetStructure {

    private int[] parent, rank;
    private int size;

    public DisjointSetForest(int size) {
        this.size = size;
        parent = new int[size];
        rank = new int[size];
        for (int i = 0; i < size; i++) {
            parent[i] = i;
        }
    }

    public void MakeSet(int item){
        parent[item] = item;
    }

    public int getSize() {
        return size;
    }

    @Override
    public int findSet(int item) throws ItemOutOfRangeException {
        if (item >= size || item < 0)
            throw new ItemOutOfRangeException();
        //path compression
        if (parent[item] != item)
            parent[item] = findSet(parent[item]);
        return parent[item];
    }

    @Override
    public void union(int item1, int item2) throws ItemOutOfRangeException {
        int root1 = findSet(item1);
        int root2 = findSet(item2);

        //same set
        if (root1 == root2)
            return;

        if (rank[root1] < rank[root2])
            parent[root1] = root2;
        else if (rank[root2] < rank[root1])
            parent[root2] = root1;
        else {
            parent[root2] = root1;
            rank[root1] = rank[root1] + 1;
        }
    }

    public long countDistinctSets(IDisjointSetStructure structure) {
        return IntStream.rangeClosed(0, this.size - 1)
                .map(item -> findSet(structure, item))
                .distinct()
                .count();
    }

    private static int findSet(IDisjointSetStructure structure, int item) {
        try {
            return structure.findSet(item);
        } catch (ItemOutOfRangeException exception) {
            fail(exception);
            return -1;
        }
    }
}
