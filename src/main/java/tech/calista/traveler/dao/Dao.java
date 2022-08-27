package tech.calista.traveler.dao;

public interface Dao<K, V> {
    void create(K key, V value);

    V read(K key);

    void update(V value);

    void delete(K key);

}
