

class ListNode<V> {
	public final V data;
	public final ListNode<V> next;

	public ListNode(V v) {
		data = v;
		next = null;
	}

	public ListNode(V v, ListNode<V> n) {
		data = v;
		next = n;
	}

	public static <W> ListNode<W> prepend(ListNode<W> list, W v) {
		return new ListNode<W>(v, list);
	}

	public static <W> int size(ListNode<W> list) {
		// return (list == null) ? 0 : (1+ size(list.next));
		int res = 0;
		for (ListNode<W> node = list; node != null; node = node.next, ++res) {
		}
		return res;
	}

	public static <W> W[] toArray(ListNode<W> list, W[] array) {
		int s = size(list);
		// return toArray(list, (array.length >= s) ? array : Arrays.copyOf(array, s),
		// 0);
		W[] res = (array.length >= s) ? array : (W[]) new Object[s];
		int i = 0;
		for (ListNode<W> node = list; node != null; node = node.next, ++i) {
			res[i] = node.data;
		}
		return res;
	}

	private static <W> W[] toArray(ListNode<W> list, W[] array, int i) {
		if (list != null) {
			array[i] = list.data;
			toArray(list.next, array, i + 1);
		}
		return array;
	}

	public static <W> W[] reversedToArray(ListNode<W> list, W[] array) {
		int s = size(list);
		return reversedToArray(list, (array.length >= s) ? array : (W[]) new Object[s], s - 1);
	}

	private static <W> W[] reversedToArray(ListNode<W> list, W[] array, int i) {
		if (i >= 0) {
			array[i] = list.data;
			reversedToArray(list.next, array, i - 1);
		}
		return array;
	}

	public static <W> W get(ListNode<W> list, int i) {
		if (list == null) {
			throw new IllegalArgumentException("index exceed SingleLinkedList size by " + (i + 1));
		}
		return (i == 0) ? list.data : get(list.next, i - 1);
	}

	public static <W> boolean contains(ListNode<W> list, W v) {
		// return (list == null) ? false : ((list.data == v) ? true :
		// contains(list.next, v));
		boolean res = false;
		for (ListNode<W> node = list; !res && (node != null); node = node.next) {
			res |= (node.data == v);
		}
		return res;
	}

	public static <W> String toString(ListNode<W> list) {
		return "" + ((list == null) ? "" : ("" + list.data + ", " + toString(list.next)));
	}
}
