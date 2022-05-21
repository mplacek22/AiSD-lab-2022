public class DisjointSetLinkedList_nodes implements IDisjointSetStructure {

    private class Node{
        Node next, last;
        int head, length;

        public Node(int head) {
            this.next = null;
            this.head = head;
            this.last = this;
            this.length = 1;
        }
    }
    private int size;
    private Node[] set;

    public DisjointSetLinkedList_nodes(int size) {
        this.size = size;
        set= new Node[size];
        for (int i = 0; i < size; i++) {
            set[i] = new Node(i);
        }
    }

    @Override
    public int findSet(int item) throws ItemOutOfRangeException {
        if (item >= size || item < 0)
            throw new ItemOutOfRangeException();
        return set[item].head;
    }

    @Override
    public void union(int item1, int item2) throws ItemOutOfRangeException {
        int repr1 = findSet(item1);
        int repr2 = findSet(item2);

        Node head1 = set[repr1];
        Node head2 = set[repr2];

        Node newHead, oldHead;

        if (head1.length >= head2.length){
            newHead = head1;
            oldHead = head2;
        }
        else {
            newHead = head2;
            oldHead = head1;
        }

        newHead.last.next = oldHead;

        Node current = oldHead;
        while (current != null){
            current.head = newHead.head;
            current = current.next;
        }

        newHead.last = oldHead.last;

        newHead.length = newHead.length + oldHead.length;
    }
}
