import java.util.ArrayList;
import java.util.LinkedList;

public class Spider {
	ArrayList<String> traversedURL = new ArrayList<>();
	LinkedList<String> remainingURL = new LinkedList<>();

	public Spider(String initial) {
		remainingURL.add(initial);
	}

	public synchronized String get() throws InterruptedException {
		if (remainingURL.isEmpty()) {
			wait();
		}
		return remainingURL.removeFirst();

	}

	public synchronized void add(String url) {
		if (!traversedURL.contains(url)) {
			remainingURL.addLast(url);
			notifyAll();
		}
	}

	public synchronized void addExplored(String url) {
		traversedURL.add(url);
	}

	public synchronized ArrayList<String> returnExplored() {
		return traversedURL;
	}

	public synchronized int getSize() {
		return traversedURL.size();
	}

}
