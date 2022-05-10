public class Pair <TKey, TValue>{
    TKey key;
    TValue value;
    Pair<TKey, TValue> previous;
    Pair<TKey, TValue> next;

    public Pair(TKey key, TValue value) {
        this.key = key;
        this.value = value;
    }

    public Pair(TKey key, TValue value, Pair<TKey, TValue> previous, Pair<TKey, TValue> next) {
        this.key = key;
        this.value = value;
        this.previous = previous;
        this.next = next;
    }

}
