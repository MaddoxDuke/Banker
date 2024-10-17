import java.io.BufferedReader;
import java.util.Scanner;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Banker {
	
	public static Scanner s = new Scanner(System.in);
	public static String fileName = "clients.csv";

	public static void main(String[] args) {
		
		LinkedList masterClientList = new LinkedList();
		Queue lobbyQueue = new Queue();
		
		init(fileName, masterClientList);
		// masterClientList.print();
		mainMenu(lobbyQueue, masterClientList);
		
		s.close();
	}
	
	public static void lobbyMenu(Queue lobbyQ) {
		Client c = new Client();
		
		
		System.out.println("\n***************************");
		System.out.print("\nAccount Number > ");
		c.setAcctNum(s.nextLong());
		System.out.print("\nFirst Name     > ");
		c.setFirstName(s.next());
		System.out.print("\nLast Name      > ");
		c.setLastName(s.next());
		
		lobbyQ.add(c);
		
	}
	
	public static void tellerMenu(Client c, LinkedList masterClient) {
		int choice = 0;
		
		while (choice != 5 && choice != 6) {
			
			System.out.println("\n\n***************************");
			System.out.println("******* Teller Menu *******");
			System.out.println("Serving: " + c.getFirstName() + " " + c.getLastName());

			System.out.println("***************************");
			System.out.println("Options: ");
			System.out.println("1. Check Balance");
			System.out.println("2. Deposit");
			System.out.println("3. Withdrawl");
			System.out.println("4. Close Account");
			System.out.println("5. Client Complete");
			System.out.println("6. Exit");
			System.out.println("***************************");
			System.out.print("      Choice: ");
			
			choice = s.nextInt();
			
			switch (choice) {
				case 1:
					balance(c, masterClient);
					break;
				case 2:
					deposit(c, masterClient);
					break;
				case 3:
					Double withdrawal = 0.0;
					System.out.println("Withdrawal amount: ");
					withdrawal = s.nextDouble();
					withdrawal(c, masterClient, withdrawal);
					break;
				case 4:
					closeAcct(c, masterClient);
					break;
				case 5:
					
					break;
				case 6:
					break;
					default:
						System.out.println("Not a valid choice\n\n");
						break;
			}
		}
	}
		
		private static void closeAcct(Client c, LinkedList masterClient) {
			Client fromList = masterClient.find(c.getAcctnum());
			
			if (fromList == null) return;

			double withdrawal = fromList.getAcctBalance();
			int answer = -1;
		
			System.out.println("\nAre you sure you want to delete account: " + c.getAcctnum() +"?");
			System.out.println("Yes (0) : No (1)");
			
			answer = s.nextInt();
			
			if (answer == 0) {
				// System.out.println("\nWithdrew all funds of " + c.getAcctBalance());
				
				withdrawal(c, masterClient, withdrawal);
				
				masterClient.closeAcct(c.getAcctnum());
				
				System.out.println("Account closed.");
			
			}
			else {
				System.out.println("Returned to teller menu.\n");
			}
				
	}

		private static void withdrawal(Client c, LinkedList masterClient, Double withdrawal) {
			Client fromList = masterClient.find(c.getAcctnum());
			
			
			if (fromList != null) {
				
				if (c.getFirstName().equalsIgnoreCase(fromList.getFirstName()) && 
					c.getLastName().equalsIgnoreCase(fromList.getLastName())) {
					
				
					
					System.out.println("Withdrawl Amount: " + withdrawal);
					
					if(withdrawal <= fromList.getAcctBalance()) {
						
						fromList.setAcctBalance(fromList.getAcctBalance() - withdrawal);
						
						
				 } else {
						System.out.println("Insufficent Funds");
					}
					
					System.out.println("\n\nCurrent Balance: $" + fromList.getAcctBalance());
				} else {
					System.out.println("\n\n*** Identity does not match account *** \n\n");
				}
			}
			else {
				System.out.println("\n\n ** Account number not found *** \n\n");
			}
	}

		private static void deposit(Client c, LinkedList masterClient) {
			Client fromList = masterClient.find(c.getAcctnum());
			double deposit = 0;
			
			
			if (fromList != null) {
				
				if (c.getFirstName().equalsIgnoreCase(fromList.getFirstName()) && 
					c.getLastName().equalsIgnoreCase(fromList.getLastName())) {
					
					System.out.println("Deposit Amount: ");
					deposit = s.nextLong();
					fromList.setAcctBalance(fromList.getAcctBalance() + deposit);
					
					
					System.out.println("\n\nCurrent Balance: $" + fromList.getAcctBalance());
				}
				else {
					System.out.println("\n\n*** Identity does not match account *** \n\n");
				}
			}
			else {
				System.out.println("\n\n ** Account number not found *** \n\n");
			}
	}

		private static void balance(Client c, LinkedList masterClient) {
			Client fromList = masterClient.find(c.getAcctnum());
			
			if (fromList != null) {
				
				if (c.getFirstName().equalsIgnoreCase(fromList.getFirstName()) && 
					c.getLastName().equalsIgnoreCase(fromList.getLastName())) {
					System.out.println("\n\nCurrent Balance: $" + fromList.getAcctBalance());
				}
				else {
					System.out.println("\n\n*** Identity does not match account *** \n\n");
				}
			}
			else {
				System.out.println("\n\n ** Account number not found *** \n\n");
			}
	}

		public static void mainMenu(Queue lobbyQ, LinkedList masterClient) {
			int choice = 0;
			
			while (choice != 3) {
				System.out.println("***********************");
				System.out.println("****** Main Menu ******");
				System.out.println("***********************");
				System.out.println("Options: ");
				System.out.println("1. Add Client to Queue");
				System.out.println("2. Serve Client");
				System.out.println("3. Close Bank");
				System.out.println("***************************");
				System.out.print("      Choice: ");
				
				choice = s.nextInt();
				
				switch (choice) {
				case 1:
					lobbyMenu(lobbyQ);
					break;
				case 2:
					if (lobbyQ.peek() != null) {
						Client c = lobbyQ.serve();
						tellerMenu(c, masterClient);
					}
					else {
						System.out.println("Queue empty");
					}
					break;
				case 3:
					
					if (lobbyQ.peek() != null) {
						System.out.println("Error, " + lobbyQ.count() + " clients still remain in queue.");
						System.out.println("Select another option:\n\n");
					} 
					else {
						try {
							masterClient.output();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}

					break;
					default:
						System.out.println("Not a valid choice");
						break;
						
				}
			}
	}

	public static void init(String fileName, LinkedList masterClientList) {
		
		String line = "";
		String splitBy = ",";
		
		try {
		
		FileReader fr = new FileReader(fileName);
		BufferedReader br = new BufferedReader(fr);
				
		int count = 0;
		
		while ((line = br.readLine()) != null) {
			
			String[] data = line.split(splitBy);
			long acctNum = Long.parseLong(data[0]);
			String firstName = data[1];
			String lastName = data[2];
			Double acctBalance = Double.parseDouble(data[3]);
			
			
			Client c = new Client(acctNum, firstName, lastName, acctBalance);
			
			masterClientList.add(c);
			count++;
			
			
		}
		System.out.println(count + " records read");
		
		br.close();
		}
		catch (IOException e) {
			System.out.println("File Error: " + fileName);
		}
		
	}
}
