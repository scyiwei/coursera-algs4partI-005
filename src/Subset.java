public class Subset {

	public static void main(String[] args) {
		if (args.length == 1) {
			RandomizedQueue<String> queue = new RandomizedQueue<String>();
			int k = 0;
			try {
				k = Integer.parseInt(args[0]);
				while (StdIn.hasNextLine() && !StdIn.isEmpty()) {
					String str = StdIn.readString();
					if (queue.size() >= k) {
						if (StdRandom.uniform() >= 0.5 && k > 0) {
							StdOut.println(queue.dequeue());
							k--;
						} else {
							queue.dequeue();
						}
						queue.enqueue(str);
					} else {
						queue.enqueue(str);
					}
				}
				for (int i = 0; i < k; i++) {
					StdOut.println(queue.dequeue());
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		} else {
			StdOut.println("java " + Subset.class.getName() + " times");
		}

	}

}
