
import java.util.List;

class SingleLinkedList<V> extends UnimplementedList<V> implements List<V> {
	protected ListNode<V> impl;

	public boolean add(V v) {
		impl = new ListNode<V>(v, impl);
		return true;
	}

	public int size() {
		int res = 0;
		for (ListNode<V> n = impl; n != null; n = n.next) {
			++res;
		}
		return res;
	}

	public boolean isEmpty() {
		return impl == null;
	}

	// we have been prepending instead of appending so we need to reverse order !
	public <W> W[] toArray(W[] array) {
		int s = size();
		W[] res = (array.length >= s) ? array : (W[]) new Object[s];
		int i = s - 1;
		for (ListNode<V> node = impl; node != null; node = node.next, --i) {
			res[i] = (W) node.data;
		}
		return res;
	}

	public V get(int i) {
		ListNode<V> n;
		for (n = impl; (n != null) && (i >= 0); n = n.next) {
			--i;
		}
		if (n == null) {
			throw new IllegalArgumentException("index exceed SingleLinkedList size by " + (i + 1));
		}
		return n.data;
	}

	public boolean contains(Object o) {
		boolean res = false;
		for (ListNode<V> node = impl; !res && (node != null); node = node.next) {
			res |= (node.data.equals(o));
		}
		return res;
	}

	public String toString() {
		String res = "";
		for (ListNode<V> n = impl; n != null; n = n.next) {
			res += n.data + ", ";
		}
		return res;
	}
}
