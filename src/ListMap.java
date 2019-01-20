class ListMap<K, V> extends UnimplementedMap<K, V> {
	SingleLinkedList<KeyValue<K, V>> impl = new SingleLinkedList<KeyValue<K, V>>();

	public V get(Object key) {
		ListNode<KeyValue<K, V>> n;
		for (n = impl.impl; (n != null) && (!n.data.first.equals(key)); n = n.next) {
		}
		return (n != null) ? n.data.second : null;
	}

	public V put(K key, V value) {
		V res = null;
		ListNode<KeyValue<K, V>> n;
		for (n = impl.impl; (n != null) && (!n.data.first.equals(key)); n = n.next) {
		}
		if (n != null) {
			res = n.data.second;
			n.data.second = value;
		} else {
			impl.add(new KeyValue<K, V>(key, value));

		}
		return res;
	}

	public int size() {
		return impl.size();
	}
}
