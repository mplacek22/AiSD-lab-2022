public class DisjointSetLinkedList implements IDisjointSetStructure {

    private int[] repr, next, last, length;
    private int size;

    public DisjointSetLinkedList(int size) {
        this.size = size;
        repr = new int[size];
        next = new int[size];
        last = new int[size];
        length = new int[size];

        for (int i = 0; i < size; i++) {
            repr[i] = i;
            next[i] = -1;
            last[i] = i;
            length[i] = 1;
        }
    }

    @Override
    public int findSet(int item) throws ItemOutOfRangeException {
        if (item >= size || item < 0)
            throw new ItemOutOfRangeException();

        return repr[item];
    }

    @Override
    public void union(int item1, int item2) throws ItemOutOfRangeException {
        int repr1 = findSet(item1);
        int last1 = last[repr1];
        int repr2 = findSet(item2);
        int last2 = last[repr2];

        if (length[repr1]>=length[repr2]){
            //insert repr2 after last1
            next[last1] = repr2;

            //update new last item
            last[repr1] = last2;

            //update the new representative for the second set
            int current = last1;
            while (current != -1){
                repr[current] = repr1;
                current = next[current];
            }

            length[repr1] = length[repr1] + length[repr2];
        }
        else {//second set is longer
            //insert repr1 after last2
            next[last2] = repr1;

            //update new last item
            last[repr2] = last1;

            //update the new representative for the first set
            int current = last2;
            while (current != -1) {
                repr[current] = repr2;
                current = next[current];
            }

            length[repr2] = length[repr2] + length[repr1];

        }

    }
}
