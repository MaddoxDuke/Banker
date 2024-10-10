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
	public long closeAcct(long acctNum) {
		
		long count = 0; 
		Client current = head;
		Client previous = current;
		
		while (current != null) {
			if (current.getAcctnum() == acctNum) {		
				count++;
				previous.setNext(current.getNext());
				current = current.getNext();	
			}
			else {	
				previous = current;
				current = current.getNext();
			}
		}
		return count;
	}
public void print() {
		
		Client current = head;
		
		while (current != null) {
			
			System.out.println(current.getFirstName() + " " + current.getLastName());
			current = current.getNext();
		}
	}
}
