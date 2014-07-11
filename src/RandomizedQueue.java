import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

	private int N; // number of elements
	private Item[] items;
	private int capacity; // How many items it can store;

	@SuppressWarnings("unchecked")
	public RandomizedQueue() {
		N = 0;
		capacity = 1;
		items = (Item[]) new Object[capacity];
	}

	@SuppressWarnings("unchecked")
	private void resize(int length) {
		Item[] newItems = (Item[]) new Object[length];
		for (int i = 0; i < N; i++) {
			newItems[i] = items[i];
		}
		items = newItems;
	}

	public int size() { // return the number of items on the queue
		return N;
	}

	public boolean isEmpty() { // is the queue empty?
		return (N == 0);
	}

	public void enqueue(Item item) { // add the item
		checkItem(item);
		if (N == capacity) {
			capacity = 2 * capacity;
			resize(capacity);
		}
		items[N++] = item;
	}

	private int getRandomIndex() {
		checkSize();
		return StdRandom.uniform(N);
	}

	public Item dequeue() { // delete and return a random item
		int index = getRandomIndex();
		Item item = items[index];
		if (index < N - 1) {
			items[index] = items[N - 1];
		}
		items[--N] = null;
		if (capacity / 4 > N) {
			capacity = capacity / 2;
			resize(capacity);
		}
		return item;
	}

	public Item sample() { // return (but do not delete) a random item
		int index = getRandomIndex();
		Item item = items[index];
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
		return new RandomizedQueueIterator();
	}

	private class RandomizedQueueIterator implements Iterator<Item> {

		private int index;

		public RandomizedQueueIterator() {
			index = 0;
		}

		@Override
		public boolean hasNext() {
			return index < N;
		}

		@Override
		public Item next() {
			if (!hasNext()) {
				throw new NoSuchElementException("No element in the Iterator");
			} else {
				return items[index++];
			}
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException(
					"Canot remove elment in Iterator");
		}

	}

	public static void main(String[] args) {
		RandomizedQueue<String> deque = new RandomizedQueue<String>();
		try {
			deque.enqueue(null);
		} catch (Exception e) {
			StdOut.println("Add null to Queue\n" + e);
		}
		try {
			deque.dequeue();
		} catch (Exception e) {
			StdOut.println("remove Element from empty queue.\n" + e);
		}
		try {
			deque.sample();
		} catch (Exception e) {
			StdOut.println("Get Element from empty queue.\n" + e);
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
		deque.enqueue("Hello");
		StdOut.println("Remove Element:" + deque.dequeue() + "\nSize:"
				+ deque.size());
		deque.enqueue("World");
		StdOut.println("Remove Element:" + deque.dequeue() + "\nIsEmpty:"
				+ deque.isEmpty());

		deque.enqueue("Hello");
		deque.enqueue("World");
		deque.dequeue();
		deque.enqueue("Welcome");
		deque.enqueue("You");
		deque.enqueue("Remove");
		deque.dequeue();
		deque.enqueue("No");
		deque.dequeue();
		deque.dequeue();

		for (String item : deque) {
			StdOut.println(item);
		}
	}

}
