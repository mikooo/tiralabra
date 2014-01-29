package fi.miko.tiralabra.datastructures;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * LinkedList implements a simple generic type linked list.
 */
public class LinkedList<E> implements List<E> {
	private class LinkedListIterator implements Iterator {
		private Node node;

		public LinkedListIterator(Node head) {
			this.node = head;
		}

		@Override
		public boolean hasNext() {
			return node.next != null;
		}

		@Override
		public E next() {
			node = node.next;
			return node.data;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}

	private class Node {
		E data = null;
		Node next = null;

		public Node() {
		}

		public Node(E data) {
			this.data = data;
		}
	}

	private int size;
	private Node head, tail;

	public LinkedList() {
		head = new Node();
		tail = head;
		size = 0;
	}

	@Override
	public boolean add(E e) {
		Node n = new Node(e);
		tail.next = n;
		tail = n;
		size++;

		return true;
	}

	@Override
	public void clear() {
		head.next = null;
		tail = head;
		size = 0;
	}

	@Override
	public boolean contains(Object o) {
		Node n = head.next;

		while (n != null) {
			if (n.data == o) {
				return true;
			}

			n = n.next;
		}

		return false;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public Iterator<E> iterator() {
		return new LinkedListIterator(head);
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("[");

		Node n = head.next;

		while (n != null) {
			sb.append(n.data.toString());

			if (n.next == null) {
				break;
			}

			sb.append(", ");
			n = n.next;
		}

		sb.append("]");

		return sb.toString();
	}

	/*
	 * Unimplemented stuff below this.
	 */

	@Override
	public void add(int index, E element) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean addAll(int index, Collection<? extends E> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public E get(int index) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int indexOf(Object o) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int lastIndexOf(Object o) {
		throw new UnsupportedOperationException();
	}

	@Override
	public ListIterator<E> listIterator() {
		throw new UnsupportedOperationException();
	}

	@Override
	public ListIterator<E> listIterator(int index) {
		throw new UnsupportedOperationException();
	}

	@Override
	public E remove(int index) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean remove(Object o) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public E set(int index, E element) {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<E> subList(int fromIndex, int toIndex) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Object[] toArray() {
		throw new UnsupportedOperationException();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		throw new UnsupportedOperationException();
	}

}
