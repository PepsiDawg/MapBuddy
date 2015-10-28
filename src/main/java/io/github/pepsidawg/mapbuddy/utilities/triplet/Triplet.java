package io.github.pepsidawg.mapbuddy.utilities.triplet;

import java.util.*;
import java.util.stream.Collectors;

public class Triplet<K, S, V> implements SymbolTable<K, S, V> {
    private List<TripletContainer> triplets;

    public Triplet() {
        triplets = new ArrayList<>();
    }

    @Override
    public void put(K key, S secondary, V value) {
        if(contains(key, secondary))
            remove(key, secondary);
        triplets.add(new TripletContainer(key, secondary, value));
    }

    @Override
    public void remove(K key) {
        triplets.removeAll(triplets.stream()
                                   .filter(t -> key.equals(t.k))
                                   .collect(Collectors.toList()));
    }

    @Override
    public void remove(K key, S secondary) {
        triplets.remove(triplets.stream()
                                .filter(t -> key.equals(t.k) && secondary.equals(t.s))
                                .findFirst().get());
    }

    @Override
    public boolean containsKey(K key) {
        return triplets.stream()
                       .filter(t -> key.equals(t.k))
                       .count() > 0;
    }

    @Override
    public boolean containsSecondary(S secondary) {
        return triplets.stream()
                       .filter(t -> secondary.equals(t.s))
                       .count() > 0;
    }

    @Override
    public boolean contains(K key, S secondary) {
        return triplets.stream()
                       .filter(t -> key.equals(t.k) && secondary.equals(t.s))
                       .count() > 0;
    }

    @Override
    public Map<S, V> get(K key) {
        Map<S, V> result = new HashMap<>();
        triplets.stream()
                .filter(t -> key.equals(t.k))
                .forEach(t -> result.put(t.s,t.v));
        return result;
    }

    @Override
    public V get(K key, S secondary) {
        return triplets.stream()
                       .filter(t -> key.equals(t.k) && secondary.equals(t.s))
                       .findFirst()
                       .get().v;
    }

    @Override
    public Map<K, S> uniqueKeySet() {
        Map<K, S> result = new HashMap<>();
        triplets.stream()
                .forEach(t -> result.put(t.k,t.s));
        return result;
    }

    @Override
    public Set<K> keySet() {
        Set<K> result = new HashSet<>();
        triplets.stream()
                .forEach(t -> result.add(t.k));
        return result;
    }

    @Override
    public Set<S> secondaryKeySet() {
        Set<S> result = new HashSet<>();
        triplets.stream()
                .forEach(t -> result.add(t.s));
        return  result;
    }

    @Override
    public Set<V> values() {
        Set<V> result = new HashSet<>();
        triplets.stream()
                .forEach(t -> result.add(t.v));
        return result;
    }

    private class TripletContainer {
        K k;
        S s;
        V v;

        public TripletContainer(K key, S secondaryKey, V value) {
            k = key;
            s = secondaryKey;
            v = value;
        }
    }
}
