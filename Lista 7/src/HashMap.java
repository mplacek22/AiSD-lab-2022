import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.function.Function;

public class HashMap<TKey, TValue> {
    Pair<TKey, TValue>[] arr;
    double loadFactor;
    int elements;
    int size;
    Function<TKey, Integer> hash;

    public HashMap(int initialSize, double loadFactor, Function<TKey, Integer> hashFunction) {
        // TODO: Zainicjuj nową instancję klasy HashMap według podanych parametrów.
        //    InitialSize - początkowy rozmiar HashMap
        //    LoadFactor - stosunek elementów do rozmiaru HashMap po przekroczeniu którego należy podwoić rozmiar HashMap.
        //    HashFunction - funkcja, według której liczony jest hash klucza.
        //       Przykład użycia:   int hash = hashFunction.apply(key);

        arr = new Pair[initialSize];
        createEmptyArray(arr);
        this.loadFactor = loadFactor;
        hash = hashFunction;
        size = initialSize;
    }

    public void add(TKey key, TValue value) throws DuplicateKeyException {
        // TODO: Dodaj nową parę klucz-wartość. Rzuć wyjątek DuplicateKeyException, jeżeli dany klucz już istnieje w HashMap.
        if (containsKey(key))
            throw new DuplicateKeyException();

        Pair<TKey, TValue> node = arr[findIndex(key)];
        Pair<TKey, TValue> newNode = new Pair<>(key, value, node.previous, null);
        node.previous.next = newNode;
        node.previous = newNode;

        elements++;

        if ((double) elements/size >= loadFactor)
            double_array();
    }

    public void clear() {
        // TODO: Wyczyść zawartość HashMap.
        createEmptyArray(arr);
        elements=0;
    }

    public boolean containsKey(TKey key) {
        // TODO: Sprawdź, czy HashMap zawiera już dany klucz.
        Pair<TKey, TValue> head = arr[findIndex(key)].next;

        while(head != null)
        {
            if(head.key.equals(key))
            {
                return true;
            }

            head = head.next;
        }

        return false;
    }

    public boolean containsValue(TValue value) {
        // TODO: Sprawdź, czy HashMap zawiera już daną wartość.
        for(Pair<TKey, TValue> pair: arr)
        {
            Pair<TKey, TValue> head = pair.next;

            while (head != null)
            {
                if (head.value.equals(value))
                    return true;
                head = head.next;
            }
        }

        return false;
    }

    public int elements() {
        // TODO: Zwróć liczbę par klucz-wartość przechowywaną w HashMap.
        return elements;
    }

    public TValue get(TKey key) throws NoSuchElementException {
        // TODO: Pobierz wartość powiązaną z danym kluczem. Rzuć wyjątek NoSuchElementException, jeżeli dany klucz nie istnieje.
        if (!containsKey(key))
            throw new NoSuchElementException();
        Pair<TKey, TValue> pair = getPairByKey(key);

        return pair.value;
    }

    public Pair<TKey, TValue> getPairByKey(TKey key)
    {
        Pair<TKey, TValue> current = arr[findIndex(key)].next;

        while(current != null)
        {
            if(current.key.equals(key))
            {
                return current;
            }

            current = current.next;
        }

        return null;
    }



    public void put(TKey key, TValue value) throws DuplicateKeyException {
        // TODO: Przypisz daną wartość do danego klucza.
        //   Jeżeli dany klucz już istnieje, nadpisz przypisaną do niego wartość.
        //   Jeżeli dany klucz nie istnieje, dodaj nową parę klucz-wartość.
        if (!containsKey(key)){
            add(key,value);
            return;
        }

        Pair<TKey, TValue> head = arr[findIndex(key)].next;

        while(head != null)
        {
            if(head.key.equals(key))
            {
                head.value = value;
                return;
            }

            head = head.next;
        }

    }

    public TValue remove(TKey key) {
        // TODO: Usuń parę klucz-wartość, której klucz jest równy podanej wartości.
       if (!containsKey(key))
           return null;

       Pair<TKey, TValue> pair = getPairByKey(key);
        TValue value = pair.value;
        pair.previous.next = pair.next;

        if(pair.next != null)
        {
            pair.next.previous = pair.previous;
        }

        elements--;
        return value;
    }

    public int size() {
        // TODO: Zwróć obecny rozmiar HashMap.
        return size;
    }

    private void double_array() throws DuplicateKeyException {
        size = size * 2;
        Pair<TKey, TValue>[] tmp = arr;
        arr = new Pair[size];
        createEmptyArray(arr);

        for(Pair<TKey, TValue> pair: tmp)
        {
            if(pair.next != null)
            {
                Pair<TKey, TValue> pair1 = arr[findIndex(pair.next.key)];
                pair1.next = pair.next;
                pair1.previous = pair.previous;
            }
        }
    }

    private int findIndex(TKey key){
        return hash.apply(key);
    }

    private void createEmptyArray(Pair<TKey, TValue>[] arr){
        for(int i = 0; i < arr.length; i++)
        {
            Pair<TKey, TValue> head = new Pair<>(null, null, null, null);
            head.previous = head;
            arr[i] = head;
        }
    }

    public void rehash(Function<TKey, Integer> newHashFunction) throws DuplicateKeyException {
        // TODO: Zmień obecną funkcję hashującą na nową (wymaga przeliczenia dla wszystkich par klucz-wartość).
    }
}
