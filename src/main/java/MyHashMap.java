public class MyHashMap<K, V> implements MyMap<K, V> {
    private int size = 0;
    private static final int INITIAL_CAPACITY = 4;
    private Pair<K, V>[] source = new Pair[INITIAL_CAPACITY];
    private static final double LOAD_FACTOR = 0.75;


    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean contains(K key) {
        throwNullPointerException(key);
        return frindPair(key) != null;
    }


    @Override
    public void put(K key, V value) {
        throwNullPointerException(key);
        if (size() > LOAD_FACTOR * source.length)
            rebalance();
        if (contains(key)) {
            frindPair(key).value = value;
            return;
        }
        if (source[findBucket(key)] == null) {
            source[findBucket(key)] = new Pair<>(key, value, null);
        } else {
            source[findBucket(key)] = new Pair<>(key, value, source[findBucket(key)]);
        }
        size++;
    }

    @Override
    public V get(K key) {
        throwNullPointerException(key);
        if (frindPair(key) != null)
            return frindPair(key).value;
        return null;
    }

    @Override
    public V remove(K key) {
        throwNullPointerException(key);
        if (!contains(key))
            return null;
        Pair<K, V> pair = source[findBucket(key)];
        if (pair.key.equals(key)) {
            source[findBucket(key)] = pair.next;
            size--;
            return pair.value;
        }
        while (pair.next != null) {
            if (pair.next.key.equals(key)) {
                Pair<K, V> pairToDelete = pair.next;
                pair.next = pairToDelete.next;
                size--;
                return pairToDelete.value;
            }
            pair = pair.next;
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("[");
        int counter = size();
        for (Pair<K, V> pair : source) {
            while (pair != null) {
                builder.append(pair);
                if (--counter > 0)
                    builder.append(", ");
                pair = pair.next;
            }
        }
        return builder.append("]").toString();
    }

    private int findBucket(K key) {
        throwNullPointerException(key);
        return Math.abs(key.hashCode()) % source.length;
    }

    private Pair<K, V> frindPair(K key) {
        throwNullPointerException(key);
        Pair<K, V> pair = source[findBucket(key)];
        while (pair != null) {
            if (pair.key.equals(key))
                return pair;
            pair = pair.next;
        }
        return null;
    }

    private void rebalance() {
        Pair<K, V>[] tmpSource = new Pair[source.length * 2];
        for (Pair<K, V> pair : source) {
            while (pair != null) {
                Pair<K, V> tmpPair = new Pair<>(pair.key, pair.value, pair.next);
                int bucket = Math.abs(tmpPair.key.hashCode()) % tmpSource.length;
                if (tmpSource[bucket] == null) {
                    tmpSource[bucket] = tmpPair;
                    tmpPair.next = null;
                } else {
                    tmpPair.next = tmpSource[bucket];
                    tmpSource[bucket] = tmpPair;
                }
                pair = pair.next;
            }
        }
        source = tmpSource;
    }

    private void throwNullPointerException(K key) {
        if (key == null)
            throw new NullPointerException("Please, enter a correct key");
    }


    private static class Pair<K, V> {
        K key;
        V value;
        Pair next;

        public Pair(K k, V v, Pair next) {
            this.key = k;
            this.value = v;
            this.next = next;
        }


        @Override
        public String toString() {
            return "Pair{" +
                    "Key=" + key +
                    ", Value=" + value +
                    '}';
        }
    }
}
