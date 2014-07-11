import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

	private int N; // number of elements
	private Node first; // beginning of Deque
	private Node last; // end of Deque

	// helper linked list class
	private class Node {
		private Item item;
		private Node next;
		private Node prev;
	}

	public Deque() {
		N = 0;
		first = null;
		last = null;
	}

	public int size() { // return the number of items on the deque
		return N;
	}

	public boolean isEmpty() { // is the deque empty?
		return (N == 0);
	}

	public void addFirst(Item item) { // insert the item at the front
		checkItem(item);
		Node newFirst = new Node();
		newFirst.item = item;
		newFirst.next = first;
		if (++N == 1) {
			last = newFirst;
		} else {
			first.prev = newFirst;
		}
		first = newFirst;
	}

	public void addLast(Item item) { // insert the item at the end
		checkItem(item);
		Node newLast = new Node();
		newLast.item = item;
		newLast.prev = last;
		if (++N == 1) {
			first = newLast;
		} else {
			last.next = newLast;
		}
		last = newLast;
	}

	public Item removeFirst() { // delete and return the item at the front
		checkSize();
		Item item = first.item;
		if (--N == 0) {
			first = null;
			last = null;
		} else {
			first = first.next;
			first.prev = null;
		}
		return item;
	}

	public Item removeLast() { // delete and return the item at the end
		checkSize();
		Item item = last.item;
		if (--N == 0) {
			first = null;
			last = null;
		} else {
			last = last.prev;
			last.next = null;
		}
		return item;
	}

	private void checkItem(Item item) {
		if (item == null) {
			throw new NullPointerException("Cannot add null item in the Deque");
		}
	}

	private void checkSize() {
		if (N == 0) {
			throw new NoSuchElementException(
					"Cannot remove element from an empty deque");
		}
	}

	@Override
	public Iterator<Item> iterator() {
		// TODO Auto-generated method stub
		return new DequeIterator();
	}

	private class DequeIterator implements Iterator<Item> {

		private Node node;

		public DequeIterator() {
			node = first;
		}

		@Override
		public boolean hasNext() {
			return node != null;
		}

		@Override
		public Item next() {
			if (!hasNext()) {
				throw new NoSuchElementException("No element in the Iterator");
			} else {
				Item item = node.item;
				node = node.next;
				return item;
			}
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException(
					"Canot remove elment in Iterator");
		}

	}

	public static void main(String[] args) {
		Deque<String> deque = new Deque<String>();
		try {
			deque.addFirst(null);
		} catch (Exception e) {
			StdOut.println("Add null to First\n" + e);
		}
		try {
			deque.addLast(null);
		} catch (Exception e) {
			StdOut.println("Add null to Last\n" + e);
		}
		try {
			deque.removeFirst();
		} catch (Exception e) {
			StdOut.println("remove First Element from empty Deque.\n" + e);
		}
		try {
			deque.removeLast();
		} catch (Exception e) {
			StdOut.println("remove Last Element from empty Deque.\n" + e);
		}
		Iterator<String> iterator = deque.iterator();
		try {
			iterator.remove();
		} catch (Exception e) {
			StdOut.println("remove Element from Iterator.\n" + e);
		}
		try {
			iterator.next();
		} catch (Exception e) {
			StdOut.println("Get next Element from Iterator.\n" + e);
		}
		deque.addFirst("Hello");
		StdOut.println("Remove Element:" + deque.removeLast() + "\nSize:"
				+ deque.size());
		deque.addLast("World");
		StdOut.println("Remove Element:" + deque.removeFirst() + "\nIsEmpty:"
				+ deque.isEmpty());

		deque.addFirst("Hello");
		deque.addLast("World");
		deque.addFirst("Welcome");
		deque.addLast("You");
		deque.addFirst("Remove");
		deque.removeLast();
		deque.addFirst("No");
		deque.removeFirst();
		deque.removeLast();
		deque.removeLast();
		deque.removeFirst();

		for (String item : deque) {
			StdOut.println(item);
		}
	}

}
