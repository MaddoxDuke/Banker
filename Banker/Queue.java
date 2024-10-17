public class Queue {
	private Client head = null;
	private Client tail = null;
	
	public void add(Client element) {
		
		if (head == null) {
			
			head = element;
			tail = element;
		}
		else {
			tail.setNext(element);
			tail = tail.getNext();
		}
	}
	public Client serve() {
		
		Client current = head;
		head = head.getNext();
		
		return current;
	}
	
	public Client peek() {
		return head;
	}
	public int count() {
		Client current = head;
		int count = 0;
		
		while (current != null) {
			current = current.getNext();
			count++;
		}
		return count;
	}
}
