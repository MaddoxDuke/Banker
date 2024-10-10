
public class Client {

	private long acctNum;
	private String firstName;
	private String lastName;
	private Double acctBalance;
	private Client next = null;
	
	public Client(long acctNum, String firstName, String lastName, Double acctBalance){
		
		this.acctNum = acctNum;
		this.firstName = firstName;
		this.lastName = lastName;
		this.acctBalance = acctBalance;
	}
	public Client(){
		
		this.acctNum = acctNum;
		this.firstName = firstName;
		this.lastName = lastName;
		this.acctBalance = acctBalance;
	}
	
	public long getAcctnum() {
		return acctNum;
	}
	
	public void setAcctNum(long a) {
		acctNum = a;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String fName) {
		firstName = fName;
	}
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lName) {
		lastName = lName;
	}
	public Double getAcctBalance() {
		return acctBalance;
	}
	
	public void setAcctBalance(Double bal) {
		acctBalance = bal;
	}
	public Client getNext() {
		return next;
	}
	
	public void setNext(Client n) {
		next = n;
	}
}
