class KeyValue<K, V> {
	public final K first;
	public V second; // we want to be able to change the value

	KeyValue(K first, V second) {
		this.first = first;
		this.second = second;
	}
}
