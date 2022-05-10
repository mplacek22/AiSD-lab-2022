import java.util.ArrayList;
import java.util.List;

public class BinarySearchTreeSorter {
    public static <T extends Comparable<T>> void sort(List<T> list) throws DuplicateElementException {
        // TODO: Posortuj listę używając klasy BinarySearchTree.

        BinarySearchTree<T> bts= new BinarySearchTree<>();

        for (int i = 0; i < list.size(); i++) {
            bts.add(list.get(i));
        }

        ArrayList<T> sorted_arr = bts.inOrderList();
        list.clear();
        list.addAll(sorted_arr);
    }
}
