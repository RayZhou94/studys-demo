package tree;

import lombok.Data;

public class SimpleTree<K, V> extends AbstractTree<K, V> {

    public V get(K key) {
        return null;
    }

    public V put(K key, V value) {
        return null;
    }

    public boolean remove(K key, V value) {
        return false;
    }

    public void remove(K key) {

    }

    @Data
    class Entry<K, V>{
        K parent;
        K left;
        K right;

        K key;
        V value;
        int size;

        Entry(K key, V value){
            this.key = key;
            this.value = value;
            this.size = 0;
        }
    }
}
