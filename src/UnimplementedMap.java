import java.util.Collection;
import java.util.Map;
import java.util.Set;

class UnimplementedMap<K, V> implements Map<K, V> {
	public void clear() {
		throw new UnsupportedOperationException();

	}

	public boolean containsKey(Object key) {
		throw new UnsupportedOperationException();

	}

	public boolean containsValue(Object value) {
		throw new UnsupportedOperationException();

	}

	public Set<Map.Entry<K, V>> entrySet() {
		throw new UnsupportedOperationException();

	}

	public V get(Object key) {
		throw new UnsupportedOperationException();

	}

	public int hashCode() {
		throw new UnsupportedOperationException();

	}

	public boolean isEmpty() {
		throw new UnsupportedOperationException();

	}

	public Set<K> keySet() {
		throw new UnsupportedOperationException();

	}

	public V put(K key, V value) {
		throw new UnsupportedOperationException();

	}

	public void putAll(Map<? extends K, ? extends V> m) {
		throw new UnsupportedOperationException();

	}

	public V remove(Object key) {
		throw new UnsupportedOperationException();
	}

	public int size() {
		throw new UnsupportedOperationException();

	}

	public Collection<V> values() {
		throw new UnsupportedOperationException();

	}
}
