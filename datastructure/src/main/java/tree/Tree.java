package tree;

public interface Tree<K, V> {

    V get(K key);

    V put(K key, V value);

    boolean remove(K key, V value);

    void remove(K key);
}
