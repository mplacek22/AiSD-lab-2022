import java.util.ArrayList;
import java.util.NoSuchElementException;

public class BinarySearchTree<T extends Comparable<T>> {

    private class Node{
        Node parent;
        T value;
        Node left;
        Node right;

        public Node (T key, Node parent){
            this.value = key;
            this.parent = parent;
            left = null;
            right = null;
        }

        public Node(Node parent){
            this.parent = parent;
            left = null;
            right = null;
        }
    }

    Node root = null;

    public void add(T value) throws DuplicateElementException {
        // TODO: Dodawanie nowej wartości do drzewa. Rzuć DuplicateElementException, jeśli element już istnieje.
        if (contains(value))
            throw new DuplicateElementException();

        Node y = null;
        Node x = root;

        while (x != null){
            y = x;
            if (value.compareTo(x.value)<0)
                x = x.left;
            else
                x = x.right;
        }

        Node z = new Node(value, y);

        if (y==null)
            root=z;
        else if (z.value.compareTo(y.value)<0)
            y.left = z;
        else
            y.right = z;
    }

    public boolean contains(T value) {
        // TODO: Sprawdzenie, czy drzewo zawiera podaną wartość.
        if (search(root, value) != null)
            return true;
        else
            return false;
    }

    private Node search(Node root, T value){
        if (root == null || root.value.compareTo(value)==0)
            return root;
        if (root.value.compareTo(value)>0)
            return search(root.left, value);
        return search(root.right, value);
    }

    public void delete(T value) {
        // TODO: Usunięcie wskazanej wartości z drzewa.
        if (!contains(value))
            return;

        Node deleteElem = search(root, value);


        if (deleteElem.left == null && deleteElem.right == null)
            ensureParent(deleteElem, null);

        else if (deleteElem.left != null && deleteElem.right == null)
            ensureParent(deleteElem, deleteElem.left);

        else if (deleteElem.left == null && deleteElem.right != null)
            ensureParent(deleteElem, deleteElem.right);

        else {
            Node succesor = getSuccessor(deleteElem);
            succesor.left = deleteElem.left;

            if (!succesor.parent.equals(deleteElem)){
                succesor.parent.left = succesor.right;
                succesor.right = deleteElem.right;
            }

            ensureParent(deleteElem, succesor);
        }

    }

    private void ensureParent(Node deleteElement, Node n) {
        Node parent = deleteElement.parent;

        if (parent == null) {
            root = n;
        } else {
            if (deleteElement == parent.left) {
                parent.left = n;
            } else {
                parent.right = n;
            }
        }

    }

    public Node getSuccessor (Node x){
        if (x.right != null)
            return getMin(x.right);

        Node y = x.parent;
        while (y != null && x.equals(y.right)){
            x = y;
            y = y.parent;
        }
        return y;
    }

    public Node getMin (Node x){
        while (x.left != null)
            x = x.left;
        return x;
    }

    public String toStringPreOrder() {
        // TODO: Zwróć wartość String reprezentującą drzewo po przejściu metodą pre-order.
        if (root == null)
            return "";
        String result = preOrder(root);
        return result.substring(0, result.length()-2);
    }

    private String preOrder(Node root){
        String result = "";
        if (root != null){
            result += root.value.toString()+", ";
            result += preOrder(root.left);
            result += preOrder(root.right);
        }
        return result;
    }

    public String toStringInOrder() {
        // TODO: Zwróć wartość String reprezentującą drzewo po przejściu metodą in-order.
        if (root == null)
            return "";
        String result = inOrder(root);
        return result.substring(0, result.length()-2);
    }

    private String inOrder(Node root){
        String result = "";
        if (root != null){
            result += inOrder(root.left);
            result += root.value.toString()+", ";
            result += inOrder(root.right);
        }
        return result;
    }

    public String toStringPostOrder() {
        // TODO: Zwróć wartość String reprezentującą drzewo po przejściu metodą in-order.
        if (root == null)
            return "";
        String result = postOrder(root);
        return result.substring(0, result.length()-2);
    }

    private String postOrder(Node root){
        String result = "";
        if (root != null){
            result += postOrder(root.left);
            result += postOrder(root.right);
            result += root.value.toString()+", ";
        }
        return result;
    }

    private ArrayList<T> inOrderListRec (Node node){
        ArrayList<T> result = new ArrayList<>();
        if (node != null){
            result.addAll(inOrderListRec(node.left));
            result.add(node.value);
            result.addAll(inOrderListRec(node.right));
        }
        return result;
    }

    public ArrayList<T> inOrderList (){
        ArrayList<T> result = inOrderListRec(root);
        return result;
    }
}
