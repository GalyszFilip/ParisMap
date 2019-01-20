

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class UnimplementedList<V> implements List<V> {
	public boolean add(V v) {
		throw new UnsupportedOperationException();
	}

	public void add(int index, V v) {
		throw new UnsupportedOperationException();
	}

	public boolean addAll(Collection<? extends V> c) {
		throw new UnsupportedOperationException();
	}

	public boolean addAll(int index, Collection<? extends V> c) {
		throw new UnsupportedOperationException();
	}

	public void clear() {
		throw new UnsupportedOperationException();
	}

	public boolean contains(Object o) {
		throw new UnsupportedOperationException();
	}

	public boolean containsAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	public V get(int index) {
		throw new UnsupportedOperationException();
	}

	public int indexOf(Object o) {
		throw new UnsupportedOperationException();
	}

	public boolean isEmpty() {

		throw new UnsupportedOperationException();
	}

	public Iterator<V> iterator() {
		throw new UnsupportedOperationException();
	}

	public int lastIndexOf(Object o) {
		throw new UnsupportedOperationException();
	}

	public ListIterator<V> listIterator() {
		throw new UnsupportedOperationException();
	}

	public ListIterator<V> listIterator(int index) {
		throw new UnsupportedOperationException();
	}

	public V remove(int index) {
		throw new UnsupportedOperationException();
	}

	public boolean remove(Object o) {
		throw new UnsupportedOperationException();
	}

	public boolean removeAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	public boolean retainAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	public V set(int index, V element) {
		throw new UnsupportedOperationException();
	}

	public int size() {
		throw new UnsupportedOperationException();
	}

	public List<V> subList(int fromIndex, int toIndex) {
		throw new UnsupportedOperationException();
	}

	public Object[] toArray() {
		throw new UnsupportedOperationException();
	}

	public <W> W[] toArray(W[] a) {
		throw new UnsupportedOperationException();
	}
}
