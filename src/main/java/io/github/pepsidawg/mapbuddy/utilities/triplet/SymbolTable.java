package io.github.pepsidawg.mapbuddy.utilities.triplet;

import java.util.Map;
import java.util.Set;

public interface SymbolTable<K, S, V> {
    void put(K key, S secondary, V value);
    void remove(K key);
    void remove(K key, S secondary);
    boolean containsKey(K key);
    boolean containsSecondary(S secondary);
    boolean contains(K key, S secondary);
    Map<S,V> get(K key);
    V get(K key, S secondary);
    Map<K,S> uniqueKeySet();
    Set<K> keySet();
    Set<S> secondaryKeySet();
    Set<V> values();
}
