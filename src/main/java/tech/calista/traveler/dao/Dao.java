package tech.calista.traveler.dao;

public interface Dao<K, V> {
    void create(K key, V value);

    V read(K key);

    void update(K key, V value);

    void delete(K key);

}
