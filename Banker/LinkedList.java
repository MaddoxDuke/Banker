
public class LinkedList {
	
	private Client head = null;
	private Client tail = null;
	
	public static int maxYears = 25;
	
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
	
	public Client find(long acctNum) {
		Client c = head;
		
		while (c != null && c.getAcctnum() != acctNum) {
			c = c.getNext();
			
		}
		
		
		return c;
	}
public void print() {
		
		Client current = head;
		
		while (current != null) {
			
			System.out.println(current.getFirstName() + " " + current.getLastName());
			current = current.getNext();
			
		}
	}
}
