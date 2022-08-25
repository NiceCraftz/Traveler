package tech.calista.traveler.repository;

import java.util.Optional;

public interface Repository<K, V> {

    void create(K key, V value);

    Optional<V> read(K key);

    void update(K key, V value);

    V delete(K key);
}
