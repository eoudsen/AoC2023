package days.day22;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class ListMap<K, V> extends HashMap<K, List<V>> {

    public List<V> addTo(final K key, final V value) {
        final List<V> l;
        if (super.containsKey(key)) {
            l = super.get(key);
        }
        else {
            l = new ArrayList<>();
        }
        l.add(value);
        return super.put(key, l);
    }

    public List<V> addTo(final K key, final Collection<V> value) {
        final List<V> l;
        if (super.containsKey(key)) {
            l = super.get(key);
        }
        else {
            l = new ArrayList<>();
        }
        l.addAll(value);
        return super.put(key, l);
    }

    public ListMap<K, V> mergeWith(final ListMap<K, V> map) {
        map.forEach(this::addTo);
        return this;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<V> get(final Object key) {
        if (!super.containsKey(key)) {
            super.put((K) key, new ArrayList<>());
        }
        return super.get(key);
    }

    public static <T, K, U> Collector<T, ?, ListMap<K, U>> toListMap(final Function<? super T, ? extends K> keyMapper, final Function<? super T, ? extends U> valueMapper) {
        final Supplier<ListMap<K, U>> supplier = ListMap::new;
        final BiConsumer<ListMap<K, U>, T> accumulator = (a, b) -> a.addTo(keyMapper.apply(b), valueMapper.apply(b));
        final BinaryOperator<ListMap<K, U>> combiner = ListMap::mergeWith;
        final Function<ListMap<K, U>, ListMap<K, U>> finisher = Function.identity();
        return Collector.of(supplier, accumulator, combiner, finisher);
    }
}