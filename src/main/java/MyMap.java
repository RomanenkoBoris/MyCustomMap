public interface MyMap<K, V> {
    int size();

    boolean contains(K key);

    void put(K key, V value);

    V get(K key);

    V remove(K key);
}
